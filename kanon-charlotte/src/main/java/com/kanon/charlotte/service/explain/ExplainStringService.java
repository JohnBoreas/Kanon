package com.kanon.charlotte.service.explain;

import com.kanon.charlotte.common.SpiderPageResult;
import com.kanon.charlotte.common.SpiderResult;
import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.service.BaseService;

import java.util.Map;

/**
 * 解析文本
 * @author xuhua.jiang
 * @date 2021-06-07
 */
public interface ExplainStringService extends BaseService {
    /**
     * 解析单页
     * @param dtoMap
     * @return
     */
    SpiderResult<Map<String, String>> explain(Map<String, SpiderExplain> dtoMap, String content);

    /**
     * 解析分页
     * @param dtoMap
     * @return
     */
    SpiderPageResult<Map<String, String>> explainPage(Map<String, SpiderExplain> dtoMap, String content);

}
