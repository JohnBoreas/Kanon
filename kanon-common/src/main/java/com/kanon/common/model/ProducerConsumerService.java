package com.kanon.common.model;

import com.kanon.common.model.consumer.DataConsumer;
import com.kanon.common.model.producer.Processor;
import com.kanon.common.model.producer.ProcessorList;
import com.kanon.common.model.producer.SelectDbProducer;
import com.kanon.common.model.producer.service.SingleProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 15:07
 */
public class ProducerConsumerService {

    /**
     * 单消费者，多消费者，db单线程消费
     * @Param jdbcTemplate
     * @param selectSql sql
     * @param threadNum 消费者个数
     * @param processor 处理器
     */
    public List<Thread> processorMultiConsumer(JdbcTemplate jdbcTemplate, String selectSql, int threadNum, Processor<Map<String, Object>> processor) {
        return processorMultiConsumer(jdbcTemplate, selectSql, 0, 10000, threadNum, processor);
    }
    /**
     * 单消费者，多消费者，db单线程消费
     * @Param jdbcTemplate
     * @param selectSql sql
     * @param startId 起始id
     * @param limit 每次查询个数
     * @param threadNum 消费者个数
     * @param processor 处理器
     */
    public List<Thread> processorMultiConsumer(JdbcTemplate jdbcTemplate, String selectSql, int startId, int limit, int threadNum, Processor<Map<String, Object>> processor) {
        LinkedBlockingQueue<Map<String, Object>> queue = new LinkedBlockingQueue<>(limit); // 待抓取商品队列
        ProcessorList<Map<String, Object>> processorList  = new SingleProcessor<>(queue);
        SelectDbProducer dataProducer = new SelectDbProducer(processorList, jdbcTemplate, selectSql, startId, limit);
        Thread thread = new Thread(dataProducer);
        thread.start();
        //消费者线程
        DataConsumer<Map<String, Object>> consumer = new DataConsumer<>(queue, thread, processor);
        List<Thread> conThreads = new ArrayList<>();
        // 启动消费者线程,抓取数据
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(consumer);
            t.setName("consumer_thread_" + i);
            t.start();
            conThreads.add(t);
        }
        return conThreads;
    }
}
