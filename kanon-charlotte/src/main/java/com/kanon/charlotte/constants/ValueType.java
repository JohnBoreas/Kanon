package com.kanon.charlotte.constants;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/24 16:14
 */
public enum  ValueType {

    // TIME:时间;PAGE:分页;COMMON:公共;LIST:分页'
    TIME, PAGE, COMMON, LIST;

    private static final Map<String, ValueType> mappings = new HashMap<>(16);

    static {
        for (ValueType valueType : values()) {
            mappings.put(valueType.name(), valueType);
        }
    }

    @Nullable
    public static ValueType resolve(@Nullable String type) {
        return (type != null ? mappings.get(type.toUpperCase()) : null);
    }


    /**
     * Determine whether this {@code DataType} matches the given method value.
     * @param type the type as a String
     * @return {@code true} if it matches, {@code false} otherwise
     */
    public boolean matches(String type) {
        return name().equals(type);
    }
}
