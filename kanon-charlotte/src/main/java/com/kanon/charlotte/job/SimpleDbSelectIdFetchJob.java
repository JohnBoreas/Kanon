package com.kanon.charlotte.job;

import com.kanon.charlotte.producer.SimpleSpiderProcessor;
import com.kanon.charlotte.util.StructureChangeUtils;
import com.kanon.common.model.ProducerConsumerService;
import com.kanon.common.utils.StringUtils;
import com.kanon.common.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读数据库抓取
 */
@Slf4j
@Component
public class SimpleDbSelectIdFetchJob extends AbstractJob {

    @Autowired
    @Qualifier("wealthJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 公共变量在同一jobDetail绑定多个triggerKey，但是参数不同时，公共变量是共享的
     * 任务是否正在执行标记 ：false--未执行； true--正在执行； 默认未执行
     */
    private volatile static Map<String, Boolean> isRunMap = new HashMap<>();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        // 任务正在执行，跳过本次执行
        TriggerKey triggerKey = context.getTrigger().getKey();// 获取Trigger的标识信息
        log.info("Trigger's start " + triggerKey.getName() + ":" + triggerKey.getGroup());
        log.info(StructureChangeUtils.jsonToString(context.getJobDetail().getJobDataMap()));
        Boolean isRun = isRunMap.get(triggerKey.getName() + ":" + triggerKey.getGroup());
        if (isRun != null && isRun) {
            log.info(triggerKey.getName() + ":" + triggerKey.getGroup() + ":前一次未执行完,跳过本次任务!");
            return;
        }
        isRunMap.put(triggerKey.getName() + ":" + triggerKey.getGroup(), true);
        String spiderSource = (String) context.getJobDetail().getJobDataMap().get("spiderSource");
        String selectSql = (String) context.getJobDetail().getJobDataMap().get("selectSql");
        String threadNum = (String) context.getJobDetail().getJobDataMap().get("threadNum");
        if (StringUtils.isEmpty(threadNum) || !StringUtils.isNumeric(threadNum)) {
            threadNum = "1";
        }
        try {
            task(spiderSource, selectSql, threadNum);
        } catch (Exception e) {
            log.error(spiderSource, e);
        }
        isRunMap.put(triggerKey.getName() + ":" + triggerKey.getGroup(), false);
        log.info("Trigger's end " + triggerKey.getName() + ":" + triggerKey.getGroup());
    }

    public void task(String spiderSource, String sql, String threadNum) {
        SimpleSpiderProcessor processor = new SimpleSpiderProcessor(spiderSource, spiderSourceDao, spiderDataService, persistenceStrategyService);
        List<Thread> threads = ProducerConsumerService.processorMultiConsumer(jdbcTemplate, sql, Integer.parseInt(threadNum), processor);
        boolean isRun = true;
        while (isRun) {
            isRun = false;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    isRun = true;
                }
            }
            ThreadUtils.sleep(5000);
        }
    }
}
