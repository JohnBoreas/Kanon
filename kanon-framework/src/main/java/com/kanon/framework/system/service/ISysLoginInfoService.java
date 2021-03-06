package com.kanon.framework.system.service;

import com.kanon.framework.system.entity.SystemLoginInfo;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 */
public interface ISysLoginInfoService {
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(SystemLoginInfo logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public List<SystemLoginInfo> selectLogininforList(SystemLoginInfo logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    public int deleteLogininforByIds(String ids);

    /**
     * 清空系统登录日志
     */
    public void cleanLogininfor();
}
