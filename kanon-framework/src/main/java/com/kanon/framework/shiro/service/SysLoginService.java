package com.kanon.framework.shiro.service;

import com.kanon.common.constant.Constants;
import com.kanon.common.constant.ShiroConstants;
import com.kanon.common.constant.UserConstants;
import com.kanon.common.enums.UserStatus;
import com.kanon.common.exception.user.*;
import com.kanon.common.utils.DateUtils;
import com.kanon.common.utils.MessageUtils;
import com.kanon.common.utils.ServletUtils;
import com.kanon.framework.manager.AsyncManager;
import com.kanon.framework.manager.factory.AsyncFactory;
import com.kanon.framework.system.entity.SystemUser;
import com.kanon.framework.system.service.ISysUserService;
import com.kanon.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录校验方法

 */
@Component
public class SysLoginService {

    private SysPasswordService passwordService;

    private ISysUserService userService;

    @Autowired
    public SysLoginService(SysPasswordService passwordService, ISysUserService userService) {
        this.passwordService = passwordService;
        this.userService = userService;
    }
    /**
     * 登录
     */
    public SystemUser login(String username, String password, boolean noPwd) {
        if (!noPwd) {
            // 验证码校验
            if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
            // 用户名或密码为空 错误
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
                throw new UserNotExistsException();
            }
            // 密码如果不在指定范围内 错误
            if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                    || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }

            // 用户名不在指定范围内 错误
            if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                    || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
        }
        // 查询用户信息
        SystemUser user = userService.selectUserByUserId(username);

        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }
        if (!noPwd) {
            passwordService.validate(user, password);
        }

        AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user);
        return user;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SystemUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
