package com.kanon.charlotte.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

/**
 * 通用时间类
 */
@Data
public class BaseTime {
    /**
     * 创建时间
     */
    @SerializedName("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;
    /**
     * 更新时间
     */
    @SerializedName("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;
}
