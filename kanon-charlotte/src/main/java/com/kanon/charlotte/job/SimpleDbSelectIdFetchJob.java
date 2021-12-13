package com.kanon.charlotte.job;

import com.kanon.charlotte.util.StructureChangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.TriggerKey;

import java.util.HashMap;
import java.util.Map;

/**
 * 读数据库抓取
 */
@Slf4j
public class SimpleDbSelectIdFetchJob extends AbstractJob {

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
        isRunMap.put(triggerKey.getName() + ":" + triggerKey.getGroup(), false);
        String spiderSource = (String) context.getJobDetail().getJobDataMap().get("spiderSource");
        String sql = (String) context.getJobDetail().getJobDataMap().get("selectSql");
        try {
            task(spiderSource);
        } catch (Exception e) {
            log.error(spiderSource, e);
        }
        log.info("Trigger's end " + triggerKey.getName() + ":" + triggerKey.getGroup());
    }

    public void task(String spiderSource) {

    }
}
