package com.kanon.charlotte.entity;

import com.kanon.charlotte.entity.BaseTime;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpiderExplain extends BaseTime {

    private Long id;
    /**
     * 数据来源名称
     */
    @SerializedName("spider_source")
    protected String spiderSource;
    /**
     * explain
     */
    @SerializedName("explain_name")
    protected String explainName;
    /**
     * explain value
     */
    @SerializedName("explain_value")
    protected String explainValue;
    /**
     * value
     */
    @SerializedName("value_type")
    protected String valueType;
}
