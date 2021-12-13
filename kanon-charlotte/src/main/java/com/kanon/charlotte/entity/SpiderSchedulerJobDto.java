package com.kanon.charlotte.entity;

import com.kanon.charlotte.entity.BaseTime;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 18:54
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpiderSchedulerJobDto extends BaseTime {

    /**
     * 数据来源名称
     */
    @SerializedName("spider_source")
    protected String spiderSource;
    /**
     * header
     */
    @SerializedName("header_name")
    protected String headerName;
    /**
     * header value
     */
    @SerializedName("header_value")
    protected String headerValue;
}
