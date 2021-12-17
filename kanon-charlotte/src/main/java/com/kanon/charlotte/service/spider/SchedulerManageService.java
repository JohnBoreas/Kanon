package com.kanon.charlotte.service.spider;

import com.kanon.charlotte.entity.SchedulerManage;
import com.kanon.charlotte.param.SchedulerParam;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 19:12
 */
public interface SchedulerManageService {

    /**
     * 查询任务配置
     *
     * @param id 任务配置ID
     * @return 任务配置
     */
    public SchedulerManage selectSchedulerManageById(Long id);

    /**
     * 查询任务配置列表
     *
     * @param schedulerManage 任务配置
     * @return 任务配置集合
     */
    public List<SchedulerManage> selectSchedulerManageList(SchedulerManage schedulerManage);

    /**
     * 新增任务配置
     *
     * @param schedulerManage 任务配置
     * @return 结果
     */
    public int insertSchedulerManage(SchedulerManage schedulerManage);

    /**
     * 修改任务配置
     *
     * @param schedulerManage 任务配置
     * @return 结果
     */
    public int updateSchedulerManage(SchedulerManage schedulerManage);

    /**
     * 批量删除任务配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSchedulerManageByIds(String ids);

    /**
     * 删除任务配置信息
     *
     * @param id 任务配置ID
     * @return 结果
     */
    public int deleteSchedulerManageById(Long id);
}
