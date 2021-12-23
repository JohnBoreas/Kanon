package com.kanon.charlotte.producer;

import com.kanon.charlotte.common.SpiderPageResult;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 16:04
 */
@Slf4j
public class SimpleSpiderProcessor implements Processor<Map<String, Object>> {

    @Autowired
    @Qualifier("spiderSourceDao")
    protected SpiderSourceDao spiderSourceDao;

    @Autowired
    @Qualifier("spiderDataService")
    protected SpiderDataService spiderDataService;

    @Autowired
    @Qualifier("persistenceStrategyService")
    protected StrategyService<SpiderParam> persistenceStrategyService;

    @Override
    public void doProcess(Map<String, Object> map) {
        if (map != null && map.size() > 0) {
            String spiderSource = String.valueOf(map.get("spiderSource"));
            log.info(spiderSource + "Fetch Start");
            Map<String, String> params = new HashMap<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals("spiderSource") && key.equals("id")) {
                    params.put(key, String.valueOf(value));
                }
            }
            SpiderSource sourceDto = spiderSourceDao.selectSourceBySource(spiderSource);
            // 初始化抓取参数
            SpiderParam param = new SpiderParam();
            param.setSpiderSource(spiderSource);
            param.setReqUrl(sourceDto.getReqUrl());
            param.setNeedProxy(sourceDto.needProxy());
            param.setReqMethod(sourceDto.getReqMethod());
            param.setDataType(sourceDto.getDataType());
            param.setReqParam(StructureChangeUtils.jsonToString(params));// 设置参数
            SpiderPersistence spiderPersistence = spiderSourceDao.selectPersistenceBySource(spiderSource);
            // 抓取并获取后的解析数据
            SpiderPageResult<Map<String, String>> spiderPageResult = (SpiderPageResult<Map<String, String>>) spiderDataService.explainContent(param);
            // 数据存储策略
            PersistenceDataService persistenceDataService = (PersistenceDataService) persistenceStrategyService.strategy(param);
            // 数据处理
            PersistenceParam persistenceParam = new PersistenceParam<Map<String, String>>();
            persistenceParam.setSpiderSource(spiderSource);
            persistenceParam.setTableName(spiderPersistence.getTableName());
            persistenceParam.setInsertField(spiderPersistence.getInsertField());
            persistenceParam.setUpdateField(spiderPersistence.getUpdateField());
            persistenceParam.setSpiderPageResult(spiderPageResult);
            persistenceDataService.save(persistenceParam);
            log.info("fetch " + StructureChangeUtils.jsonToString(params));
        }
    }
}
