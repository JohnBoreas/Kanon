package com.kanon.framework.system.entity;

import com.kanon.common.core.entity.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * @author boreas
 * @create 2021-11-24 18:45
 */
@Data
public class SystemUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户ID
     */
    private String userId;

    /** 角色ID */
    private Long roleId;
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /*个人说明*/
    private String description;
    /*邮箱验证标志*/
    private Integer emailFlag;
    /*手机验证标志*/
    private Integer phoneFlag;

    private Integer score;
    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    private String thirdAccount;//扩展字段，用户第三方登录的账号，不同于openid
    /**
     * 最后登陆时间
     */
    private Date loginDate;

    private Date lastLoginTime;//用于判断每天第一次登录

    private int questionCount;//扩展字段，用户发帖数量
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 角色组
     */
    private Long[] roleIds;
    /**
     * 岗位组
     */
    private Long[] postIds;

    private List<SystemRole> roles;

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long id) {
        return id != null && 1L == id;
    }

    public SystemUser() {

    }

    public SystemUser(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("email", getEmail())
                .append("phonenumber", getPhonenumber())
                .append("sex", getSex())
                .append("avatar", getAvatar())
                .append("password", getPassword())
                .append("salt", getSalt())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("loginIp", getLoginIp())
                .append("loginDate", getLoginDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
