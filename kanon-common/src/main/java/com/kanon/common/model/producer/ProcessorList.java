package com.kanon.common.model.producer;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 13:46
 */
public interface ProcessorList<T> {

    void doProcess(List<T> list);
}
