package com.kanon.charlotte.job;

import com.kanon.charlotte.constants.SpiderConstants;
import com.kanon.charlotte.common.SpiderPageResult;
import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.entity.SpiderPersistence;
import com.kanon.charlotte.entity.SpiderSource;
import com.kanon.charlotte.param.PersistenceParam;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.explain.ExplainStringService;
import com.kanon.charlotte.service.persistence.PersistenceDataService;
import com.kanon.charlotte.util.StructureChangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页抓取Job
 * @author xuhua.jiang
 * @date 2021-06-16
 */
@Component
@Slf4j
public class PaginationFetchJob extends AbstractJob {

    /**
     * 公共变量在同一jobDetail绑定多个triggerKey，但是参数不同时，公共变量是共享的
     * 任务是否正在执行标记 ：false--未执行； true--正在执行； 默认未执行
     */
    private volatile static Map<String, Boolean> isRunMap = new HashMap<>();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        // 任务正在执行，跳过本次执行
        TriggerKey triggerKey = context.getTrigger().getKey();// 获取Trigger的标识信息
        log.info("Trigger's start " + triggerKey.getName() + ":" + triggerKey.getGroup());
        log.info(StructureChangeUtils.jsonToString(context.getJobDetail().getJobDataMap()));
        Boolean isRun = isRunMap.get(triggerKey.getName() + ":" + triggerKey.getGroup());
        if (isRun != null && isRun) {
            log.info(triggerKey.getName() + ":" + triggerKey.getGroup() + ":前一次未执行完,跳过本次任务!");
            return;
        }
        isRunMap.put(triggerKey.getName() + ":" + triggerKey.getGroup(), true);
        String spiderSource = (String) context.getJobDetail().getJobDataMap().get("spiderSource");
        try {
            task(spiderSource);
        } catch (Exception e) {
            log.error(spiderSource, e);
        }
        isRunMap.put(triggerKey.getName() + ":" + triggerKey.getGroup(), false);
        log.info("Trigger's end " + triggerKey.getName() + ":" + triggerKey.getGroup());
    }

    /**
     * 任务执行的主题
     */
    protected void task(String spiderSource) {
        /**
         * 抓取逻辑：
         * 1、先去获取数据源
         * 2、请求数据源
         * 3、获取解析源，解析当前请求数据
         * 4、获取db源，对当前数据入库
         * 分页抓取还是只抓一页，是解析什么类型的字符串
         */
        //获取JobDetail中传递的参数
        log.info(spiderSource + "Fetch Start");
        SpiderSource sourceDto = spiderSourceDao.selectSourceBySource(spiderSource);

        SpiderPersistence spiderPersistence = spiderSourceDao.selectPersistenceBySource(spiderSource);
        // 解析
        List<SpiderExplain> explainStringDtoList = spiderSourceDao.selectExplainBySource(spiderSource);

        Map<String, SpiderExplain> dtoMap = explainStringDtoList.stream().collect(Collectors.toMap(SpiderExplain::getExplainName, Function.identity()));
        // 初始化抓取参数
        SpiderParam param = new SpiderParam();
        param.setSpiderSource(spiderSource);
        param.setReqUrl(sourceDto.getReqUrl());
        param.setPage(sourceDto.isPageList());
        param.setNeedProxy(sourceDto.needProxy());
        // 支持 {offset} {pageNo}
        param.setReqParam(sourceDto.getReqParam());
        param.setReqMethod(sourceDto.getReqMethod());
        param.setDataType(sourceDto.getDataType());
        int page = 1;// 当前抓取页
        boolean hasNext = true;// 是否有下一页
        int tryCount = 0;// 抓取尝试次数
        int total = 1;// 总个数
        int offset = 0;
        int pageCount = 1;// 总页数
        // db配置的参数

        Map<String, String> params = StructureChangeUtils.stringToMap(sourceDto.getReqParam());
        int pageSize = Integer.valueOf(params.get(SpiderConstants.PAGE_SIZE) != null ? params.get(SpiderConstants.PAGE_SIZE) : "1");
        int noDataCount = 0;
        while (hasNext) {
            param.setReqParam(StructureChangeUtils.jsonToString(params));// 设置参数
            try {
                String content = spiderDataService.originalContent(param);
                Thread.sleep(1000);
                ExplainStringService explainStringDao = (ExplainStringService) explainStrategyService.strategy(param);
                PersistenceDataService persistenceDataService = (PersistenceDataService) persistenceStrategyService.strategy(param);
                SpiderPageResult<Map<String, String>> spiderPageResult = explainStringDao.explainPage(dtoMap, content);
                if (spiderPageResult != null && spiderPageResult.getErrorMsg() == null) {
                    total = spiderPageResult.getTotal();
                    // hashNext
                    if (spiderPageResult.getHashNext() != null) {
                        hasNext = spiderPageResult.getHashNext();
                    }
                    // 获取总页数
                    if (total > 1) {
                        pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
                    }
                    if (spiderPageResult.getPageCount() != null) {
                        pageCount = spiderPageResult.getPageCount();
                    }
                    // 记录连续无数据次数
                    if (spiderPageResult.getResults() == null || spiderPageResult.getResults().isEmpty()) {
                        noDataCount ++;
                    } else {
                        noDataCount = 0;
                        // 数据处理
                        PersistenceParam persistenceParam = new PersistenceParam<Map<String, String>>();
                        persistenceParam.setSpiderSource(spiderSource);
                        persistenceParam.setTableName(spiderPersistence.getTableName());
                        persistenceParam.setInsertField(spiderPersistence.getInsertField());
                        persistenceParam.setUpdateField(spiderPersistence.getUpdateField());
                        persistenceParam.setSpiderPageResult(spiderPageResult);
                        persistenceDataService.savePage(persistenceParam);
                    }
                } else {
                    log.error("抓取失败" + spiderSource + ", page :" + page);
                }
                log.info(spiderSource + " Fetch, page : " + page + ", code : " + (spiderPageResult.getErrorMsg() != null ? spiderPageResult.getErrorMsg() : spiderPageResult.getCode()));
            } catch (Exception e) {
                log.error(spiderSource + " Fetch Error, page:" + page, e);
            }
            // 设置下一页及下一页参数
            if (params.containsKey(SpiderConstants.OFFSET)) {// offset
                offset = offset + pageSize;
                params.put(SpiderConstants.OFFSET, String.valueOf(offset));// 设置下一页参数
            }
            if (params.containsKey(SpiderConstants.PAGE_NO)) {// pageSize
                page ++;
                params.put(SpiderConstants.PAGE_NO, String.valueOf(page));// 设置下一页参数
            }
            // 超过页码时候退出
            if (pageCount > 1 && page > pageCount || offset > 0 && offset > total) {
                hasNext = false;
            }
            // 连续无数据次数超过一定次数后退出
            if (noDataCount > SpiderConstants.MAX_NO_DATA_COUNT) {
                hasNext = false;
                log.info(spiderSource + " Fetch, 连续10次无数据, 已退出" );
            }
        }
        log.info(spiderSource + " Fetch End , " + page);
    };
}
