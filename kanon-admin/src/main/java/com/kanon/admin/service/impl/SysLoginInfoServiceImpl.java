package com.kanon.admin.service.impl;

import com.kanon.admin.mapper.SysLoginInfoMapper;
import com.kanon.common.core.text.Convert;
import com.kanon.framework.system.entity.SystemLoginInfo;
import com.kanon.framework.system.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 */
@Service
public class SysLoginInfoServiceImpl implements ISysLoginInfoService {

    private SysLoginInfoMapper logininforMapper;

    @Autowired
    public SysLoginInfoServiceImpl(SysLoginInfoMapper logininforMapper) {
        this.logininforMapper = logininforMapper;
    }
    /**
     * 新增系统登录日志
     *
     * @param loginInfo 访问日志对象
     */
    @Override
    public void insertLogininfor(SystemLoginInfo loginInfo) {
        logininforMapper.insertLogininfor(loginInfo);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SystemLoginInfo> selectLogininforList(SystemLoginInfo loginInfo) {
        return logininforMapper.selectLogininforList(loginInfo);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids) {
        return logininforMapper.deleteLogininforByIds(Convert.toStrArray(ids));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        logininforMapper.cleanLogininfor();
    }
}
