package com.kanon.charlotte.entity;

import com.kanon.charlotte.entity.BaseTime;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author xuhua.jiang
 * @date 2021/6/22 17:41
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpiderPersistence extends BaseTime {

    private Long id;
    /**
     * 数据来源名称
     */
    @SerializedName("spider_source")
    protected String spiderSource;
    /**
     * 表名
     */
    @SerializedName("table_name")
    protected String tableName;
    /**
     * 插入字段
     */
    @SerializedName("insert_field")
    protected String insertField;
    /**
     * 更新字段
     */
    @SerializedName("update_field")
    protected String updateField;
}
