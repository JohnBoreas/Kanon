package com.kanon.charlotte.util;

import com.alibaba.fastjson.JSONObject;
import com.kanon.charlotte.entity.ScheduleJob;
import com.kanon.charlotte.param.SchedulerParam;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 17:16
 */
@Component
@Slf4j
public class ScheduleJobUtils {

    private static SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    public ScheduleJobUtils(SchedulerFactoryBean schedulerFactoryBean) {
        ScheduleJobUtils.schedulerFactoryBean = schedulerFactoryBean;
    }

    /**
     * 初始化任务
     *
     * @param job
     */
    public void initJob(ScheduleJob job) throws Exception {
        if (job == null) {
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        if (!ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            this.deleteJob(scheduler, triggerKey);
            log.info("删除任务{}", job.getJobName());
        } else {
            addJob(scheduler, triggerKey, job);
            log.info(scheduler + "添加任务:{}", JSONObject.toJSONString(job));
        }

    }

    /**
     * 增加定时任务job
     *
     * @param scheduler  调度器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    private void addJob(Scheduler scheduler, TriggerKey triggerKey, ScheduleJob job) throws Exception {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            /*不存在,创建一个*/
            setJobNoExistTrigger(scheduler, trigger, triggerKey, job);
        } else {
            /*存在,直接用*/
            setJobExistTrigger(scheduler, trigger, triggerKey, job);
        }
    }

    /**
     * 如果触发器key不存在,设置job
     *
     * @param scheduler  调度器
     * @param trigger    触发器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    private void setJobExistTrigger(Scheduler scheduler, CronTrigger trigger, TriggerKey triggerKey, ScheduleJob job)
            throws Exception {
        /*Trigger已存在，那么更新相应的定时设置*/
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
        /* 按新的cronExpression表达式重新构建trigger*/
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        /*按新的trigger重新设置job执行*/
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 如果触发器key存在,设置job
     *
     * @param scheduler  调度器
     * @param trigger    触发器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    public void setJobNoExistTrigger(Scheduler scheduler, CronTrigger trigger,
                                     TriggerKey triggerKey, ScheduleJob job) throws Exception {
        Class clazz = Class.forName(job.getJobClass());
        /*设定job详情和属性*/
        JobDetailImpl tail = new JobDetailImpl();
        tail.setDescription(job.getDescription());
        tail.setGroup(job.getJobGroup());
        tail.setJobClass(clazz);
        tail.setJobDataMap(new JobDataMap());
        tail.setKey(JobKey.jobKey(job.getJobGroup(), job.getJobName()));
        tail.setRequestsRecovery(job.isRequestsRecovery());
        /*设置持久*/
        tail.setDurability(job.isDruable());
        JobDetail jobDetail = tail;
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule(job.getCronExpression());

        trigger = TriggerBuilder.newTrigger()
                .withIdentity(job.getJobName(), job.getJobGroup())
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 添加job
     * @param param
     */
    public static Boolean addJob(SchedulerParam param) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Class clazz = null;
        try {
            // 判断是否存在
            if (scheduler.checkExists(JobKey.jobKey(param.getJobName(), param.getJobGroup()))) {
                log.info("当前已存在该job，请确定名称，组是否正确 " + param.getJobName() + "-" + param.getJobGroup());
                return false;
            }
            clazz = Class.forName(param.getJobClass());
            Map<String, String> params = StructureChangeUtils.stringToMap(param.getParameters());
            JobBuilder jobBuilder = JobBuilder.newJob(clazz).withIdentity(param.getJobName(), param.getJobGroup());
            //设置参数
            if (params != null) {
                params.entrySet().stream().forEach(par -> {jobBuilder.usingJobData(par.getKey(), par.getValue());});
            }
            //创建jobdetail
            JobDetail jobDetail = jobBuilder
                    //描述
                    .withDescription(param.getDescription())
                    .build();
            // 使用cron表达式
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(param.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger-" + param.getJobName(), param.getJobGroup())
                    .startNow()
                    .withSchedule(cronScheduleBuilder)
                    .build();
            // 交由Scheduler安排触发
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException e) {
            log.error("添加job出现异常", e);
        } catch (ClassNotFoundException e) {
            log.error(param.getJobClass(), e);
        }
        return false;
    }
    /**
     * 删除job
     *
     * @param scheduler
     * @param triggerKey
     * @throws Exception
     */
    private void deleteJob(Scheduler scheduler, TriggerKey triggerKey) {
        if (null != triggerKey) {
            try {
                scheduler.unscheduleJob(triggerKey);
            } catch (SchedulerException e) {
                log.error("删除job异常");
                e.printStackTrace();
            }
        } else {
            log.warn("删除job无效,没有指定对应的Trigger");
        }
    }

    /**
     * 停止job
     *
     * @param name  名称
     * @param group 分组
     */
    public void stopJob(String name, String group) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("停止job异常", e);
        }
    }

    /**
     * 停止所有的job
     */
    public void pauseAll() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            log.error("暂停所有job出现异常", e);
        }
    }

    /**
     * 关闭所有job
     */
    public void shutdown() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error("关闭所有Job出现异常!", e);
        }
    }

    /**
     * 恢复指定任务
     *
     * @param name
     * @param group
     */
    public void restartJob(String name, String group) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("恢复任务异常", e);
        }
    }

    /**
     * 恢复所有job
     */
    public void restartAll() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            log.error("恢复所有job异常", e);
        }
    }

    /**
     * 删除job
     *
     * @param jobName  job名称
     * @param jobGroup job 分组
     */
    public void deleteJob(String jobName, String jobGroup) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            /*scheduler.deleteJob(JobKey.jobKey(jobName,jobGroup));*/
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            log.error("删除job" + jobGroup + "." + jobName + "出现异常", e);
        }
    }
}
