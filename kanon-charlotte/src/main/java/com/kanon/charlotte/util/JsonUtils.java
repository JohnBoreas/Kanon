package com.kanon.charlotte.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/10 14:35
 */
public class JsonUtils {
    /**
     * @param pattern Node1.node11.node111 eg: model.images.image1
     *                or
     *                Node1.node11[attr=value].node111 eg: model.attrs.group[showOrder=9].attr[attrName=屏幕尺寸].attrVal
     *                or
     *                Node1.node11[3].node111 eg: model.attrs.get(3).value
     * @param jsonStr
     * @return map or String
     */
    public static Object getObject(String pattern, String jsonStr) {
        if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(jsonStr))
            return null;
        jsonStr = jsonStr.trim();
        String[] ps = pattern.split("\\.");
        Object tmp = null;
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        for (int i = 0; i < ps.length; i++) {
            String e = ps[i];
            String attr = null;
            if (e.contains("[")) {
                attr = e.substring(e.indexOf("[") + 1, e.indexOf("]"));
                e = e.substring(0, e.indexOf("["));
            }
            tmp = jsonObject.get(e);
            if (tmp instanceof JSONObject)
                jsonObject = (JSONObject) tmp;
            if (StringUtils.isEmpty(attr))
                continue;
            if (tmp instanceof JSONArray) {
                List<?> tmpList = (List<?>) tmp;
                if (attr.contains("=")) {
                    String key = attr.split("=")[0];
                    String val = attr.split("=")[1];
                    for (Object o : tmpList) {
                        if (o instanceof JSONObject) {
                            boolean flag = false;
                            Map<?, ?> om = (Map<?, ?>) o;
                            for (Map.Entry<?, ?> en : om.entrySet()) {
                                if (en.getKey().equals(key) && en.getValue().toString().equals(val)) {
                                    flag = true;
                                    tmp = o;
                                    break;
                                }
                            }
                            if (flag) {
                                jsonObject = (JSONObject) om;
                                break;
                            }
                        }
                    }
                } else {
                    if (StringUtils.isNumeric(attr) && Integer.parseInt(attr) < tmpList.size()) {
                        tmp = tmpList.get(Integer.parseInt(attr));
                        jsonObject = (JSONObject) tmp;
                    }
                }
            }
        }
        return tmp;
    }
}
