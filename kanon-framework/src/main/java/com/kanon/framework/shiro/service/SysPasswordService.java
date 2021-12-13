package com.kanon.framework.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;

import com.kanon.framework.system.entity.SystemUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.kanon.common.constant.Constants;
import com.kanon.common.constant.ShiroConstants;
import com.kanon.common.exception.user.UserPasswordNotMatchException;
import com.kanon.common.exception.user.UserPasswordRetryLimitExceedException;
import com.kanon.common.utils.MessageUtils;
import com.kanon.framework.manager.AsyncManager;
import com.kanon.framework.manager.factory.AsyncFactory;

/**
 * 登录密码方法
 */
@Component
public class SysPasswordService {
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    public void validate(SystemUser user, String password) {
        String userId = user.getUserId();

        AtomicInteger retryCount = loginRecordCache.get(userId);

        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(userId, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userId, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password)) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userId, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(userId, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(userId);
        }
    }

    public boolean matches(SystemUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUserId(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }

    public void unlock(String userId) {
        loginRecordCache.remove(userId);
    }
}
