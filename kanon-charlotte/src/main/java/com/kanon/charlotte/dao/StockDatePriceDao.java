package com.kanon.charlotte.dao;

import com.kanon.charlotte.param.SchedulerParam;
import org.apache.ibatis.annotations.Insert;

/**
 * @author xuhua.jiang
 * @date 2021/6/17 17:47
 */
public interface StockDatePriceDao {

    @Insert({ "insert into tb_stock_date_price (exchange_date, stock_code, open_price, close_price, max_price, min_price, avg_price, " +
            "last_price, volume, turn_volume, price_earnings_ratio, turnover_rate, change_rate, change_price, 5day_price, 10day_price, " +
            "20day_price, 30day_price, 60day_price, fetch_time ) " +
            "values (#{exchangeDate}, #{stockCode}, #{openPrice}, #{closePrice}, #{maxPrice}, #{minPrice}, #{avgPrice}, " +
            "#{lastPrice}, #{volume}, #{turnVolume}, #{priceEarningsRatio}, #{turnoverRate}, #{changeRate}, #{changePrice}, #{5dayPrice}, #{10dayPrice}, " +
            "#{20dayPrice}, #{30dayPrice}, #{60dayPrice}, now()) " })
//    @Options(useGeneratedKeys=true, keyProperty="id")
    Long insertJob(SchedulerParam param);
}
