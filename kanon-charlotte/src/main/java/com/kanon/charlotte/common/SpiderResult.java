package com.kanon.charlotte.common;

import lombok.Data;

/**
 * @author xuhua.jiang
 * @date 2021/6/24 17:03
 */
@Data
public class SpiderResult<T> {

    private String errorMsg;// 抓取失败原因

    private String code;// 抓取的状态

    private T results;// List

    public static <T> SpiderResult<T> emptyResult() {
        return new SpiderResult<>();
    }
}
