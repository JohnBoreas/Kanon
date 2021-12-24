package com.kanon.charlotte.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpiderSource extends BaseTime {
    /** 自增id */
    private Long id;
    /**
     * 数据来源名称
     */
    @SerializedName("spider_source")
    protected String spiderSource;
    /**
     * 请求描述
     */
    @SerializedName("source_des")
    protected String sourceDes;
    /**
     * 请求URL
     */
    @SerializedName("req_url")
    protected String reqUrl;
    /**
     * 请求方法
     */
    @SerializedName("req_method")
    protected String reqMethod;
    /**
     * 请求参数
     */
    @SerializedName("req_param")
    protected String reqParam;
    /**
     * 数据类型
     */
    @SerializedName("data_type")
    protected String dataType;
    /**
     * 是否分页，0-不分页;1-分页
     */
    @SerializedName("is_page")
    protected Integer isPage;
    /**
     * 字符集
     */
    @SerializedName("charset")
    protected String charset;
    /**
     * 是否需要代理ip
     */
    @SerializedName("need_proxy")
    protected Integer needProxy;
    /**
     * 是否需要cookie
     */
    @SerializedName("need_cookie")
    protected Integer needCookie;
    /**
     * 是否需要token认证信息
     */
    @SerializedName("need_token")
    protected Integer needToken;
    /**
     * cookie是否过期
     */
    @SerializedName("is_last_cookie")
    protected Integer isLastCookie;

    public Boolean isPageList() {
        if (isPage != null && isPage == 1) {
            return true;
        }
        return false;
    }

    public Boolean needProxy() {
        if (needProxy != null && needProxy == 1) {
            return true;
        }
        return false;
    }

    public Boolean needCookie() {
        if (needCookie != null && needCookie == 1) {
            return true;
        }
        return false;
    }

}
