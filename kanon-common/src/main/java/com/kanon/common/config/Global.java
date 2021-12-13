package com.kanon.common.config;

import com.kanon.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置类
 *
 */
@Configuration
public class Global {

    private static String name;

    private static String version;

    private static String copyrightYear;

    private static String cacheEnabled;

    private static Boolean addressEnabled;

    private static String profile;

    /**
     * 菜单导航显示风格
     */
    private static String menuStyle;
    public static Long ROLE_ID_DEFAULT = 2L;//新增的用户赋予的默认角色的ID

    @Value("${kanon.name}")
    public void setName(String name) {
        Global.name = name;
    }

    @Value("${kanon.version}")
    public void setVersion(String version) {
        Global.version = version;
    }

    @Value("${kanon.copyrightYear}")
    public void setCopyrightYear(String copyrightYear) {
        Global.copyrightYear = copyrightYear;
    }

    @Value("${kanon.addressEnabled}")
    public void setAddressEnabled(Boolean addressEnabled) {
        Global.addressEnabled = addressEnabled;
    }

    @Value("${kanon.menuStyle}")
    public void setMenuStyle(String menuStyle) {
        Global.menuStyle = menuStyle;
    }

    @Value("${kanon.profile}")
    public void setProfile(String profile) {
        Global.profile = profile;
    }

    @Value("${kanon.cacheEnabled}")
    public void setCacheEnabled(String cacheEnabled) {
        Global.cacheEnabled = cacheEnabled;
    }

    /**
     * 获取项目名称
     */
    public static String getName() {
        return StringUtils.nvl(name, "KaNon");
    }

    /**
     * 获取项目版本
     */
    public static String getVersion() {
        return StringUtils.nvl(version, "1.0.0");
    }

    /**
     * 获取版权年份
     */
    public static String getCopyrightYear() {
        return StringUtils.nvl(copyrightYear, "2021");
    }

    /**
     * 缓存开关
     */
    public static String isCacheEnabled() {
        return StringUtils.nvl(cacheEnabled, "true");
    }

    /**
     * 获取文件上传路径
     */
    public static String getProfile() {
        return profile;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getProfile() + "/upload";
    }

    /**
     * 获取素材上传路径
     */
    public static String getMaterialPath() {
        return getProfile() + "/material";
    }

    /**
     * 获取附件上传路径
     */
    public static String getAttachPath() {
        return getProfile() + "/attach";
    }

    /**
     * 获取资源上传路径
     */
    public static String getResourcePath() {
        return getProfile() + "/resource";
    }

    /**
     * 获取模板上传路径
     */
    public static String getTemplatePath() {
        return getProfile() + "/template";
    }

    /**
     * 获取插件上传路径
     */
    public static String getPlugPath() {
        return getProfile() + "/plugs";
    }

    /**
     * 获取数据库备份路径
     */
    public static String getDbBackupPath() {
        return getProfile() + "/dbbackup";
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static String getMenuStyle() {
        return menuStyle;
    }


}
