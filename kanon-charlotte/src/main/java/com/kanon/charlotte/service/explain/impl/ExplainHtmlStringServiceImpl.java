package com.kanon.charlotte.service.explain.impl;

import com.kanon.charlotte.entity.Results;
import com.kanon.charlotte.entity.SpiderExplainStringDto;
import com.kanon.charlotte.service.explain.ExplainStringService;
import com.kanon.charlotte.entity.PageResults;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/7 23:07
 */
@Service("explainHtmlStringService")
public class ExplainHtmlStringServiceImpl implements ExplainStringService {

    @Override
    public Results<Map<String, String>> explain(Map<String, SpiderExplainStringDto> dtoMap, String content) {
        if (dtoMap != null) {

        }
        return null;
    }

    @Override
    public PageResults<Map<String, String>> explainPage(Map<String, SpiderExplainStringDto> dtoMap, String content) {
        return null;
    }
}
