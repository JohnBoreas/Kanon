package com.kanon.charlotte.service.explain;

import com.kanon.charlotte.entity.PageResults;
import com.kanon.charlotte.entity.Results;
import com.kanon.charlotte.entity.SpiderExplainStringDto;
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
    Results<Map<String, String>> explain(Map<String, SpiderExplainStringDto> dtoMap, String content);

    /**
     * 解析分页
     * @param dtoMap
     * @return
     */
    PageResults<Map<String, String>> explainPage(Map<String, SpiderExplainStringDto> dtoMap, String content);

}
