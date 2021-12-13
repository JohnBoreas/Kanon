package com.kanon.admin.service.impl;

import com.kanon.admin.mapper.SysRoleMapper;
import com.kanon.admin.mapper.SysUserMapper;
import com.kanon.admin.mapper.SysUserRoleMapper;
import com.kanon.admin.service.ISysConfigService;
import com.kanon.common.annotation.DataScope;
import com.kanon.common.constant.UserConstants;
import com.kanon.common.core.text.Convert;
import com.kanon.common.exception.BusinessException;
import com.kanon.common.utils.StringUtils;
import com.kanon.common.utils.security.Md5Utils;
import com.kanon.framework.system.entity.SystemRole;
import com.kanon.framework.system.entity.SystemUser;
import com.kanon.framework.system.entity.SystemUserRole;
import com.kanon.framework.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * 用户 业务层处理
 */
@Slf4j
@Service
public class SysUserServiceImpl implements ISysUserService {

    private SysUserMapper userMapper;

    private SysRoleMapper roleMapper;

    private SysUserRoleMapper userRoleMapper;

    private ISysConfigService configService;

    @Autowired
    public SysUserServiceImpl(SysUserMapper userMapper,
                              SysRoleMapper roleMapper,
                              SysUserRoleMapper userRoleMapper,
                              ISysConfigService configService) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.configService = configService;
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SystemUser> selectUserList(SystemUser user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SystemUser> selectAllocatedList(SystemUser user) {
        return userMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SystemUser> selectUnallocatedList(SystemUser user) {
        return userMapper.selectUnallocatedList(user);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SystemUser selectUserByUserId(String userName) {
        return userMapper.selectUserByUserId(userName);
    }

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public SystemUser selectUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public SystemUser selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    @Override
    public SystemUser selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    /**
     * 通过用户ID查询用户和角色关联
     *
     * @param id 用户ID
     * @return 用户和角色关联列表
     */
    @Override
    public List<SystemUserRole> selectUserRoleByUserId(Long id) {
        return userRoleMapper.selectUserRoleByUserId(id);
    }

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id) {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(id);
        return userMapper.deleteUserById(id);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws BusinessException {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds) {
            checkUserAllowed(new SystemUser(userId));
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SystemUser user) {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user.getId(), user.getRoleIds());
        return rows;
    }

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean registerUser(SystemUser user) {
        user.setUserType(UserConstants.REGISTER_USER_TYPE);
        return userMapper.insertUser(user) > 0;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SystemUser user) {
        Long id = user.getId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(id);
        // 新增用户与角色管理
        insertUserRole(user.getId(), user.getRoleIds());
        // 新增用户与岗位管理
        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(SystemUser user) {
        return userMapper.updateUser(user);
    }

    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(userId, roleIds);
    }

    /**
     * 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(SystemUser user) {
        return updateUserInfo(user);
    }

    /**
     * 新增用户角色信息
     * @param id 用户对象
     */
    public void insertUserRole(Long id, Long[] roleIds) {
        if (StringUtils.isNotNull(roleIds)) {
            // 新增用户与角色管理
            List<SystemUserRole> list = new ArrayList<SystemUserRole>();
            for (Long roleId : roleIds) {
                SystemUserRole ur = new SystemUserRole();
                ur.setUserId(id);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SystemUser user) {

    }

    /**
     * 校验登录名称是否唯一
     *
     * @param userId 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String userId) {
        int count = userMapper.checkLoginNameUnique(userId);
        if (count > 0) {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SystemUser user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        SystemUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != userId.longValue()) {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SystemUser user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        SystemUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != userId.longValue()) {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SystemUser user) {
        if (StringUtils.isNotNull(user.getId()) && user.isAdmin()) {
            throw new BusinessException("不允许操作超级管理员用户");
        }
    }

    /**
     * 查询用户所属角色组
     *
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long id) {
        List<SystemRole> list = roleMapper.selectRolesById(id);
        StringBuffer idsStr = new StringBuffer();
        for (SystemRole role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId) {
        return null;
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SystemUser> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (SystemUser user : userList) {
            try {
                // 验证是否存在这个用户
                SystemUser u = userMapper.selectUserByUserId(user.getUserId());
                if (StringUtils.isNull(u)) {
                    user.setPassword(Md5Utils.hash(user.getUserId() + password));
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserId() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserId() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserId() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int changeStatus(SystemUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int insertUserInvite(String account, String inviteId, String ip) {
        return 0;//userMapper.insertUserInvite(account, inviteId, ip);
    }

    @Override
    public boolean checkAccountExist(String account) {
        SystemUser user = userMapper.selectUserByUserId(account);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPhoneExist(String phone) {
        boolean accountExist = this.checkAccountExist(phone);
        if (!accountExist) {
            SystemUser user = userMapper.selectUserByPhoneNumber(phone);
            if (user == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isBindPhone(SystemUser user) {
        user = userMapper.selectUserById(user.getId());
        return user.getPhoneFlag() == 1;
    }

    @Override
    public boolean isBindEmail(SystemUser user) {
        user = userMapper.selectUserById(user.getId());
        return user.getEmailFlag() == 1;
    }
}
