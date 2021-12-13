package com.kanon.charlotte.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 通用时间类
 */
@Data
public class BaseTime {
    /**
     * 创建时间
     */
    @SerializedName("create_time")
    protected String createTime;
    /**
     * 更新时间
     */
    @SerializedName("update_time")
    protected String updateTime;
}
