package com.kanon.charlotte.mapper;

import com.kanon.charlotte.entity.SchedulerManage;
import com.kanon.common.annotation.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/16 16:18
 */
@Mapper
@Repository
@DataSource(value = "spiderDataSource")
public interface SchedulerManageMapper {

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SchedulerManage selectSchedulerManageById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param schedulerManage 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SchedulerManage> selectSchedulerManageList(SchedulerManage schedulerManage);

    /**
     * 新增【请填写功能名称】
     *
     * @param schedulerManage 【请填写功能名称】
     * @return 结果
     */
    public int insertSchedulerManage(SchedulerManage schedulerManage);

    /**
     * 修改【请填写功能名称】
     *
     * @param schedulerManage 【请填写功能名称】
     * @return 结果
     */
    public int updateSchedulerManage(SchedulerManage schedulerManage);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSchedulerManageById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSchedulerManageByIds(String[] ids);
}
