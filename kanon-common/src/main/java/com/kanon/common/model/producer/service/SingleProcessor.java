package com.kanon.common.model.producer.service;

import com.google.gson.Gson;
import com.kanon.common.model.producer.ProcessorList;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 13:48
 */
@Slf4j
public class SingleProcessor<T> implements ProcessorList<T> {

    private BlockingQueue<T> queue;

    public SingleProcessor(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void doProcess(List<T> list) {
        if (list == null)
            return;
        for (T o : list) {
            try {
                queue.put(o);
            } catch (Exception e) {
                log.error(new Gson().toJson(o), e);
            }
        }
    }
}
