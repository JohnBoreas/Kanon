package com.kanon.common.core.page;

import com.kanon.common.constant.Constants;
import com.kanon.common.utils.ServletUtils;

/**
 * 表格数据处理
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageData getPageDomain() {
        PageData pageData = new PageData();
        pageData.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageData.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageData.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageData.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageData;
    }

    public static PageData buildPageRequest() {
        return getPageDomain();
    }
}
