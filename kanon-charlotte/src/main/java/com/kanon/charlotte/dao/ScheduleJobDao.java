package com.kanon.charlotte.dao;

import com.kanon.charlotte.param.SchedulerParam;
import com.kanon.common.annotation.DataSource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 22:37
 */
@Mapper
@Repository
@DataSource(value = "spiderDataSource")
public interface ScheduleJobDao {

    /**
     * 获取id
     */
    @Select("SELECT id FROM tb_spider_scheduler_job WHERE job_name = #{jobName} and job_group = #{jobGroup} LIMIT 1")
    String selectIdByUnique(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    /**
     * 插入
     * @param param
     * <!-- useGeneratedKeys 设置为true之后，mybatis会使用JDBC的getGeneratedKeys 方法取出来由数据库内部生成的主键。获得到主键后将其赋值给keyProperty配置的id属性。
     *
     * <!-- 当需要设置多个属性时，使用逗号隔开，这种情况下通常还需要设置keyColumn属性，按顺序指定数据库的列，这里列的值会和keyProperty配置的属性一一对应。
     * 由于要使用数据库返回的主键值，所以SQL上下两部分的列中去掉了id列和对应的#{id}列 -->
     *
     * 设置useGeneratedKeys后 SchedulerJobParam中必须包含id，不然会报错
     * @return
     */
    @Insert({ "insert into tb_spider_scheduler_job (job_name, job_group, job_class, cron_expression, job_parameters, description, status, create_time, update_time)"
            + " values (#{jobName}, #{jobGroup}, #{jobClass}, #{cronExpression}, #{parameters}, #{description}, 1, now(), now()) " })
//    @Options(useGeneratedKeys=true, keyProperty="id")
    Long insertJob(SchedulerParam param);
}
