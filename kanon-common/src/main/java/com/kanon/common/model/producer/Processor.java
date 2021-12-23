package com.kanon.common.model.producer;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 15:41
 */
public interface Processor<T> {

    void doProcess(T data);
}
