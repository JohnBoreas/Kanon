package com.kanon.charlotte.service.strategy;

import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.BaseService;
import com.kanon.charlotte.service.StrategyService;
import com.kanon.charlotte.service.persistence.impl.DbPersistenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author xuhua.jiang
 * @date 2021/6/22 16:57
 */
@Service("persistenceStrategyService")
public class PersistenceStrategyService implements StrategyService<SpiderParam> {

    @Qualifier("dbPersistenceDataService")
    @Autowired
    protected DbPersistenceDataService dbPersistenceDataService;

    @Override
    public BaseService strategy(SpiderParam param) {
        return dbPersistenceDataService;
    }
}
