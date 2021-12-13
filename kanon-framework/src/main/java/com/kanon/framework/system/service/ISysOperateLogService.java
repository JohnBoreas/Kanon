package com.kanon.framework.system.service;


import com.kanon.framework.system.entity.SystemOperateLog;

import java.util.List;

/**
 * 操作日志 服务层
 */
public interface ISysOperateLogService {
    /**
     * 新增操作日志
     *
     * @param operateLog 操作日志对象
     */
    public void insertOperateLog(SystemOperateLog operateLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SystemOperateLog> selectOperateLogList(SystemOperateLog operateLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteOperateLogByIds(String ids);

    /**
     * 查询操作日志详细
     *
     * @param operateId 操作ID
     * @return 操作日志对象
     */
    public SystemOperateLog selectOperateLogById(Long operateId);

    /**
     * 清空操作日志
     */
    public void cleanOperateLog();


}
