package com.kanon.charlotte.vo;

import lombok.Data;

/**
 * @author xuhua.jiang
 * @date 2021/6/10 19:45
 */
@Data
public class StockDetailVo {
    // 交易日期
    private String exchangeDate;
    // 代码
    private String stockCode;
    // 名称
    private String stockName;
    // 最新价
    private String newPrice;
    // 涨跌幅
    private String changeRate;
    // 涨跌额
    private String changePrice;
    // 成交量
    private String volume;
    // 成交额
    private String turnVolume;
    // 振幅
    private String swing;
    // 最高价
    private String maxPrice;
    // 最低价
    private String minPrice;
    // 成交均价
    private String avgPrice;
    // 开盘价
    private String startPrice;
    // 收盘价
    private String closePrice;
    // 昨日收盘价
    private String lastPrice;
    // 换手率
    private String turnoverRate;
    // 市盈率（动）: PE、“本益比”、“股价收益比率”或“市价盈利比率
    private String priceEarningsRatio;
}
