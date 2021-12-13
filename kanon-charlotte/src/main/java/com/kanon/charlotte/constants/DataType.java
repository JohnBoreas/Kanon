package com.kanon.charlotte.constants;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/9 12:02
 */
public enum DataType {

    JSON, HTML, JSON_PAGE, HTML_PAGE;

    private static final Map<String, DataType> mappings = new HashMap<>(16);

    static {
        for (DataType dataType : values()) {
            mappings.put(dataType.name(), dataType);
        }
    }

    @Nullable
    public static DataType resolve(@Nullable String type) {
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
