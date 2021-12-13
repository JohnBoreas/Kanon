package com.kanon.charlotte.param;

import lombok.Data;

import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 18:18
 */
@Data
public class SchedulerJobParam {

//    private Integer id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 是否启动任务
     */
    private String jobStatus;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * spring bean 任务所在 类名
     */
    private String jobClass;
    /**
     * 任务调用的方法传入的参数,统一使用String,
     */
    private String parameters;
    /**
     * 任务是否有状态 是否支持并行
     */
    private boolean isConcurrent;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 是否更新
     */
    private boolean isUpdateData;
    /**
     * 是否要求唤醒
     */
    private boolean requestsRecovery;
    /**
     * 是否持久化
     */
    private boolean isDruable;
}
