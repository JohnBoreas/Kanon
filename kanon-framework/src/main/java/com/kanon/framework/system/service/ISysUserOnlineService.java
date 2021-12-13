package com.kanon.framework.system.service;

import com.kanon.framework.system.entity.SystemUserOnline;

import java.util.Date;
import java.util.List;

/**
 * 在线用户 服务层
 */
public interface ISysUserOnlineService {
    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public SystemUserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteOnline(List<String> sessions);

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    public void saveOnline(SystemUserOnline online);

    /**
     * 查询会话集合
     *
     * @param userOnline 分页参数
     * @return 会话集合
     */
    public List<SystemUserOnline> selectUserOnlineList(SystemUserOnline userOnline);

    /**
     * 强退用户
     *
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId);

    /**
     * 清理用户缓存
     *
     * @param userId 登录名称
     * @param sessionId 会话ID
     */
    public void removeUserCache(String userId, String sessionId);

    /**
     * 查询会话集合
     *
     * @param expiredDate 有效期
     * @return 会话集合
     */
    public List<SystemUserOnline> selectOnlineByExpired(Date expiredDate);
}
