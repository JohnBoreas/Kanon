package com.kanon.charlotte.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 18:54
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SchedulerManage extends BaseTime {

    private Long id;
    /**
     * 任务名称
     */
    @SerializedName("job_name")
    protected String jobName;
    /**
     * 任务组
     */
    @SerializedName("job_group")
    protected String jobGroup;
    /**
     * 使用的job
     */
    @SerializedName("job_class")
    protected String jobClass;
    /**
     * cron表达式
     */
    @SerializedName("cron_expression")
    protected String cronExpression;
    /**
     * 任务参数
     */
    @SerializedName("job_parameters")
    protected String jobParameters;
    /**
     * 描述
     */
    @SerializedName("description")
    protected String description;
    /**
     * 任务状态:-1-停用;0-未启动;1-启动
     */
    @SerializedName("status")
    protected String status;
}
