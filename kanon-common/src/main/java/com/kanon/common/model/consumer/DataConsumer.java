package com.kanon.common.model.consumer;

import com.kanon.common.model.producer.Processor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 15:15
 */
@Slf4j
public class DataConsumer<T> implements Runnable {

    private BlockingQueue<T> queue;
    private Processor rocessor;
    private Thread queryThread;

    public DataConsumer(BlockingQueue<T> queue, Thread queryThread, Processor rocessor) {
        this.queue = queue;
        this.queryThread = queryThread;
        this.rocessor = rocessor;
    }

    public void run() {
        log.info("DataConsumer run start");
        while (queryThread.isAlive() || !queue.isEmpty()) {
            try {
                T o = queue.poll(5, TimeUnit.SECONDS);
                if (o != null) {
                    rocessor.doProcess(o);
                }
            } catch (Exception e) {
                log.error("DataConsumer", e);
            }
        }
        log.info("DataConsumer run end");
    }
}
