package com.kanon.admin.service.impl;


import com.kanon.admin.mapper.SysCostTimeMapper;
import com.kanon.common.core.text.Convert;
import com.kanon.framework.system.entity.SystemCostTime;
import com.kanon.framework.system.service.ISysCostTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统响应时间统计记录（方便对响应时间长的进行优化） 服务层实现
 */
@Service
public class SysCostTimeServiceImpl implements ISysCostTimeService {

    private SysCostTimeMapper costTimeMapper;

    @Autowired
    public SysCostTimeServiceImpl(SysCostTimeMapper costTimeMapper) {
        this.costTimeMapper = costTimeMapper;
    }

    /**
     * 查询系统响应时间统计记录（方便对响应时间长的进行优化）信息
     *
     * @param id 系统响应时间统计记录（方便对响应时间长的进行优化）ID
     * @return 系统响应时间统计记录（方便对响应时间长的进行优化）信息
     */
    @Override
    public SystemCostTime selectCostTimeById(Integer id) {
        return costTimeMapper.selectCostTimeById(id);
    }

    /**
     * 查询系统响应时间统计记录（方便对响应时间长的进行优化）列表
     *
     * @param costTime 系统响应时间统计记录（方便对响应时间长的进行优化）信息
     * @return 系统响应时间统计记录（方便对响应时间长的进行优化）集合
     */
    @Override
    public List<SystemCostTime> selectCostTimeList(SystemCostTime costTime) {
        return costTimeMapper.selectCostTimeList(costTime);
    }

    /**
     * 新增系统响应时间统计记录（方便对响应时间长的进行优化）
     *
     * @param costTime 系统响应时间统计记录（方便对响应时间长的进行优化）信息
     * @return 结果
     */
    @Override
    public int insertCostTime(SystemCostTime costTime) {
        return costTimeMapper.insertCostTime(costTime);
    }

    /**
     * 删除系统响应时间统计记录（方便对响应时间长的进行优化）对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCostTimeByIds(String ids) {
        return costTimeMapper.deleteCostTimeByIds(Convert.toStrArray(ids));
    }

}
