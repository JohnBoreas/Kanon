package com.kanon.charlotte.producer;

import com.kanon.charlotte.common.SpiderResult;
import com.kanon.charlotte.constants.SpiderConstants;
import com.kanon.charlotte.dao.SpiderSourceDao;
import com.kanon.charlotte.entity.SpiderPersistence;
import com.kanon.charlotte.entity.SpiderSource;
import com.kanon.charlotte.param.PersistenceParam;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.StrategyService;
import com.kanon.charlotte.service.persistence.PersistenceDataService;
import com.kanon.charlotte.service.spider.SpiderDataService;
import com.kanon.charlotte.util.StructureChangeUtils;
import com.kanon.common.model.producer.Processor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 16:04
 */
@Slf4j
public class SimpleSpiderProcessor implements Processor<Map<String, Object>> {

    protected SpiderSourceDao spiderSourceDao;

    protected SpiderDataService spiderDataService;

    protected StrategyService<SpiderParam> persistenceStrategyService;

    protected String spiderSource;

    public SimpleSpiderProcessor(String spiderSource, SpiderSourceDao spiderSourceDao, SpiderDataService spiderDataService, StrategyService<SpiderParam> persistenceStrategyService) {
        this.spiderSource = spiderSource;
        this.spiderSourceDao = spiderSourceDao;
        this.spiderDataService = spiderDataService;
        this.persistenceStrategyService = persistenceStrategyService;

    }

    @Override
    public void doProcess(Map<String, Object> map) {
        if (map != null && map.size() > 0) {
            String spiderSource = this.spiderSource;
            log.info(spiderSource + "Fetch Start");

            SpiderSource sourceDto = spiderSourceDao.selectSourceBySource(spiderSource);
            Map<String, String> params = StructureChangeUtils.stringToMap(sourceDto.getReqParam());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String paramKey = SpiderConstants.BIG_LEFT_BRACKETS + key + SpiderConstants.BIG_RIGHT_BRACKETS;
                if (params.containsKey(paramKey)) {
                    params.put(paramKey, String.valueOf(value));
                }
            }
            // 初始化抓取参数
            SpiderParam param = new SpiderParam();
            param.setSpiderSource(spiderSource);
            param.setReqUrl(sourceDto.getReqUrl());
            param.setNeedProxy(sourceDto.needProxy());
            param.setReqMethod(sourceDto.getReqMethod());
            param.setPage(sourceDto.isPageList());
            param.setDataType(sourceDto.getDataType());
            param.setReqParam(StructureChangeUtils.jsonToString(params));// 设置参数
            SpiderPersistence spiderPersistence = spiderSourceDao.selectPersistenceBySource(spiderSource);
            // 抓取并获取后的解析数据
            SpiderResult<Map<String, String>> spiderResult = (SpiderResult<Map<String, String>>) spiderDataService.explainContent(param);
            // 数据存储策略
            PersistenceDataService persistenceDataService = (PersistenceDataService) persistenceStrategyService.strategy(param);
            // 数据处理
            PersistenceParam persistenceParam = new PersistenceParam<Map<String, String>>();
            persistenceParam.setSpiderSource(spiderSource);
            persistenceParam.setTableName(spiderPersistence.getTableName());
            persistenceParam.setInsertField(spiderPersistence.getInsertField());
            persistenceParam.setUpdateField(spiderPersistence.getUpdateField());
            persistenceParam.setSpiderResult(spiderResult);
            persistenceDataService.save(persistenceParam);
            log.info("fetch " + StructureChangeUtils.jsonToString(params));
        }
    }
}
