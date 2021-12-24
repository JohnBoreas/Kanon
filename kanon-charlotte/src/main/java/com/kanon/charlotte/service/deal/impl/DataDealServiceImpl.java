package com.kanon.charlotte.service.deal.impl;

import com.kanon.charlotte.service.deal.DataDealService;
import com.kanon.common.db.JdbcTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/12/24 13:20
 */
@Slf4j
@Service("dataDealService")
public class DataDealServiceImpl implements DataDealService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DataDealServiceImpl(@Qualifier("wealthJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int updateACode(String date) {
        int count = 0;
        log.info("updateACode start ");
        List<Map<String, Object>> stockCodeList = JdbcTemplateUtils.queryForList(jdbcTemplate, "select stock_code stockCode from tb_stock_date_price where exchange_date = ? ", date);
        if (stockCodeList != null && stockCodeList.size() > 0) {
            for (Map<String, Object> objectMap : stockCodeList) {
                String stockCode = String.valueOf(objectMap.get("stockCode"));
                List<Map<String, Object>> stockMap = JdbcTemplateUtils.queryForList(jdbcTemplate, "select id from tb_stock_basic where code = ? ", stockCode);
                if (stockMap == null || stockMap.size() == 0) {
                    int result = JdbcTemplateUtils.update(jdbcTemplate, "insert into tb_stock_basic ( code ) values ( ? ) on duplicate key update update_time = NOW() ", stockCode);
                    if (result > 0) {
                        count ++;
                    }
                }
            }
        }
        log.info("updateACode end, insert count " + count);
        return count;
    }
}
