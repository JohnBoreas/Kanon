package com.kanon.charlotte.service.explain.impl;

import com.kanon.charlotte.common.SpiderResult;
import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.service.explain.ExplainStringService;
import com.kanon.charlotte.common.SpiderPageResult;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/7 23:07
 */
@Service("explainHtmlStringService")
public class ExplainHtmlStringServiceImpl implements ExplainStringService {

    @Override
    public SpiderResult<Map<String, String>> explain(Map<String, SpiderExplain> dtoMap, String content) {
        if (dtoMap != null) {

        }
        return null;
    }

    @Override
    public SpiderPageResult<Map<String, String>> explainPage(Map<String, SpiderExplain> dtoMap, String content) {
        return null;
    }
}
