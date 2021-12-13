package com.kanon.charlotte.entity;

import com.kanon.charlotte.entity.BaseTime;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpiderSourceDto extends BaseTime {

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

    public Boolean isNeedProxy() {
        if (needProxy != null && needProxy == 1) {
            return true;
        }
        return false;
    }

    public Boolean isNeedCookie() {
        if (needProxy != null && needProxy == 1) {
            return true;
        }
        return false;
    }
}
