package com.kanon.charlotte.util;

import com.kanon.charlotte.constants.SpiderConstants;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class StructureChangeUtils {

    public static Gson gson;

    @Autowired
    public void setGson(Gson gson) {
        StructureChangeUtils.gson = gson;
    }

    public static Map<String, String> stringToMap(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        return gson.fromJson(jsonStr, map.getClass());
    }

    /**
     * @param object
     * @return
     */
    public static String jsonToString(Object object) {
        return new Gson().toJson(object);
    }

    /**
     * param1=dada_param2=dj_param3=134
     * @param params
     * @return
     */
    public static Map<String, String> paramsToMap(String params) {
        if (StringUtils.isNotEmpty(params)) {
            List<String> paramList = Arrays.asList(params.split(SpiderConstants.UNDERLINE));
            if (paramList != null) {
                Map<String, String> paramMap = new HashMap<>();
                paramList.stream().forEach(param -> {
                    String[] s = param.split(SpiderConstants.EQUAL_SIGN);
                    String key = s[0];
                    String value = s[1];
                    paramMap.put(key, value);
                });
                return paramMap;
            }
        }
        return new HashMap<>(0);
    }

    /**
     * 复制beanOld中的属性给beanNew
     * @param beanNew
     * @param beanOld
     */
    public static void copy(Object beanNew, Object beanOld) {
        Field[] fields2 = beanOld.getClass().getDeclaredFields();
        for (Field field2 : fields2) {
            field2.setAccessible(true);
            Field field1 = null;
            try {
                field1 = beanNew.getClass().getDeclaredField(field2.getName());
                field1.setAccessible(true);
                field1.set(beanNew, field2.get(beanOld));
            } catch (NoSuchFieldException e) {
                log.error(beanNew.getClass() + "-" + beanOld.getClass(), e);
            } catch (IllegalAccessException e) {
                log.error(beanNew.getClass() + "-" + beanOld.getClass(), e);
            }
        }
    }
}
