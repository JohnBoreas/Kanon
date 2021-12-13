package com.kanon.charlotte.service;

/**
 * @author xuhua.jiang
 * @date 2021/6/9 14:30
 */
public interface StrategyService<P> {

    /**
     * 决策
     *
     * @param param 参数对象
     * @return 返回最终执行的service
     */
    BaseService strategy(P param);
}
