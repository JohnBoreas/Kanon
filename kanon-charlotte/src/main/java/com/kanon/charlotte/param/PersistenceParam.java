package com.kanon.charlotte.param;

import com.kanon.charlotte.common.SpiderPageResult;
import com.kanon.charlotte.common.SpiderResult;
import lombok.Data;

/**
 * @author xuhua.jiang
 * @date 2021/6/23 10:08
 */
@Data
public class PersistenceParam<T> {

    private String spiderSource;

    private SpiderPageResult<T> spiderPageResult;

    private SpiderResult<T> spiderResult;

    private String tableName;

    private String insertField;
    /**
     * 更新字段
     */
    private String updateField;
}
