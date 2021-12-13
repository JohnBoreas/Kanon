package com.kanon.charlotte.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResults<T> {

    private Boolean hashNext;// 是否有下一页

    private Integer page;// 当前页

    private Integer pageCount;// 页总数

    private Integer total;// 商品总数

    private Integer pageSize;// 页大小

    private List<T> results;// List

    private Boolean apiOutLimit;// api是否超过次数

    public static <T> PageResults<T> emptyResult() {
        return new PageResults<>();
    }

    private String errorMsg;// 抓取失败原因

    private String code;// 抓取的状态
}
