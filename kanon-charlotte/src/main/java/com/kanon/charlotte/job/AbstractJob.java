package com.kanon.charlotte.job;

import com.kanon.charlotte.dao.SpiderExplainDao;
import com.kanon.charlotte.dao.SpiderPersistenceConfigDao;
import com.kanon.charlotte.dao.SpiderSourceDao;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.StrategyService;
import com.kanon.charlotte.service.spider.SpiderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 抽象job
 */
public abstract class AbstractJob extends QuartzJobBean {

    @Autowired
    @Qualifier("spiderSourceDao")
    protected SpiderSourceDao spiderSourceDao;

    @Autowired
    @Qualifier("spiderExplainDao")
    protected SpiderExplainDao spiderExplainDao;

    @Autowired
    @Qualifier("spiderPersistenceConfigDao")
    protected SpiderPersistenceConfigDao spiderPersistenceConfigDao;

    @Autowired
    @Qualifier("spiderDataService")
    protected SpiderDataService spiderDataService;

    @Autowired
    @Qualifier("explainStrategyService")
    protected StrategyService<SpiderParam> explainStrategyService;

    @Autowired
    @Qualifier("persistenceStrategyService")
    protected StrategyService<SpiderParam> persistenceStrategyService;
}
