package com.kanon.charlotte.service.explain.impl;

import com.alibaba.fastjson.JSONObject;
import com.kanon.charlotte.constants.SpiderConstants;
import com.kanon.charlotte.constants.ValueType;
import com.kanon.charlotte.common.SpiderPageResult;
import com.kanon.charlotte.common.SpiderResult;
import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.service.explain.ExplainStringService;
import com.kanon.charlotte.util.DateUtils;
import com.kanon.charlotte.util.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xuhua.jiang
 * @date 2021/6/7 23:07
 */
@Service("explainJsonStringService")
public class ExplainJsonStringServiceImpl implements ExplainStringService {

    @Override
    public SpiderResult<Map<String, String>> explain(Map<String, SpiderExplain> dtoMap, String content) {
        //  mtopjsonp(........)
        SpiderResult<Map<String, String>> spiderResult = SpiderResult.emptyResult();
        if (content.contains("<html>")) {
            spiderResult.setErrorMsg("当前页非Json, 为HTML");
            return spiderResult;
        }
        if (content.endsWith(SpiderConstants.SMALL_RIGHT_BRACKETS) || content.endsWith(");") || !content.startsWith(SpiderConstants.BIG_LEFT_BRACKETS)) {
            content = content.substring(content.indexOf(SpiderConstants.BIG_LEFT_BRACKETS), content.lastIndexOf(SpiderConstants.BIG_RIGHT_BRACKETS) + 1);
        }
        // 用于判断是否抓取正常
        String code = String.valueOf(JsonUtils.getObject(dtoMap.get(SpiderConstants.CODE).getExplainValue(), content));
        spiderResult.setCode(code);
        // 失败返回
        if (!dtoMap.get(SpiderConstants.CODE_VALUE).getExplainValue().equals(code)) {
            spiderResult.setErrorMsg("抓取失败, 抓取返回code非正常返回值");
            return spiderResult;
        }
        String currentDate = DateUtils.getBeforeMinuteTimeStr(new Date(), 0, "yyyy-MM-dd");
        // 获取数据
        Map<String, String> resultMap = new HashMap<>();
        for (Map.Entry<String, SpiderExplain> entry : dtoMap.entrySet()) {
            String explainValue = entry.getValue().getExplainValue();
            if (ValueType.COMMON.matches(entry.getValue().getValueType())) {
                Object object = JsonUtils.getObject(explainValue, content);
                String value = String.valueOf(object);
                resultMap.put(entry.getKey(), value);
            }
            if (explainValue.equals("currentDate")) {
                resultMap.put(entry.getKey(), currentDate);
            }
        }
        spiderResult.setResults(resultMap);
        return spiderResult;
    }

    @Override
    public SpiderPageResult<Map<String, String>> explainPage(Map<String, SpiderExplain> dtoMap, String content) {
        //  mtopjsonp(........)
        SpiderPageResult<Map<String, String>> results = SpiderPageResult.emptyResult();
        if (content.contains("<html>")) {
            results.setErrorMsg("当前页非Json, 为HTML");
            return results;
        }
        if (content.endsWith(SpiderConstants.SMALL_RIGHT_BRACKETS) || content.endsWith(");") || !content.startsWith(SpiderConstants.BIG_LEFT_BRACKETS)) {
            content = content.substring(content.indexOf(SpiderConstants.BIG_LEFT_BRACKETS), content.lastIndexOf(SpiderConstants.BIG_RIGHT_BRACKETS) + 1);
        }
        // 用于判断是否抓取正常
        String code = String.valueOf(JsonUtils.getObject(dtoMap.get(SpiderConstants.CODE).getExplainValue(), content));
        results.setCode(code);
        // 失败返回
        if (!dtoMap.get(SpiderConstants.CODE_VALUE).getExplainValue().equals(code)) {
            results.setErrorMsg("抓取失败, 抓取返回code非正常返回值, 原始抓取数据" + content);
            return results;
        }
        // 分页数据
        if (dtoMap.containsKey(SpiderConstants.TOTAL)) {
            results.setTotal(Integer.valueOf(String.valueOf(JsonUtils.getObject(dtoMap.get(SpiderConstants.TOTAL).getExplainValue(), content))));
        }
        if (dtoMap.containsKey(SpiderConstants.HASH_NEXT)) {
            results.setHashNext(Boolean.valueOf(String.valueOf(JsonUtils.getObject(dtoMap.get(SpiderConstants.HASH_NEXT).getExplainValue(), content))));
        }
        if (dtoMap.containsKey(SpiderConstants.PAGE_COUNT)) {
            results.setPageCount(Integer.valueOf(String.valueOf(JsonUtils.getObject(dtoMap.get(SpiderConstants.PAGE_COUNT).getExplainValue(), content))));
        }
        String currentDate = DateUtils.getBeforeMinuteTimeStr(new Date(), 0, "yyyy-MM-dd");
        // 获取外部公共数据
        Map<String, String> commonValueMap = new HashMap<>();
        for (Map.Entry<String, SpiderExplain> entry : dtoMap.entrySet()) {
            String explainValue = entry.getValue().getExplainValue();
            if (ValueType.COMMON.matches(entry.getValue().getValueType())) {
                Object object = JsonUtils.getObject(explainValue, content);
                String value = String.valueOf(object);
                commonValueMap.put(entry.getKey(), value);
            }
        }
        // 列表数据解析
        String listValue = dtoMap.get(SpiderConstants.LIST).getExplainValue();
        // 获取list数据
        List<?> contentList = (List<?>) JsonUtils.getObject(listValue, content);
        if (contentList != null && contentList.size() > 0) {
            List<Map<String, String>> resultList = new ArrayList<>();
            for (int i = 0; i < contentList.size(); i++) {
                // 一条数据
                Map<String, String> resultMap = new HashMap<>();
                for (Map.Entry<String, SpiderExplain> entry : dtoMap.entrySet()) {
                    // codeValue : data.code
                    String explainValue = entry.getValue().getExplainValue();
                    if (ValueType.LIST.matches(entry.getValue().getValueType()) && explainValue.length() > listValue.length()) {
                        // 截取有效的节点
                        explainValue = explainValue.substring(listValue.length() + 1);
                        Object object = JsonUtils.getObject(explainValue, JSONObject.toJSONString(contentList.get(i)));
                        // 解析节点
                        String value = String.valueOf(object);
                        resultMap.put(entry.getKey(), value);
                    } else if (ValueType.COMMON.matches(entry.getValue().getValueType())) {
                        // 获取list以外的公共数据
                        resultMap.put(entry.getKey(), commonValueMap.get(entry.getKey()));
                    }
                    if (explainValue.equals("currentDate")) {
                        resultMap.put(entry.getKey(), currentDate);
                    }
                }
                resultList.add(resultMap);
            }
            results.setResults(resultList);
        }
        // 是否有下一页
        return results;
    }
}
