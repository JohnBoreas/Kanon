package com.kanon.charlotte.spider;

import com.kanon.common.http.httpclient.HttpClientUtils;
import com.kanon.common.db.JdbcTemplateUtils;
import com.kanon.charlotte.util.StructureChangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author boreas
 * @create 2021-09-17 16:42
 */
@Component
@Slf4j
public class FetchOrgReportSpider {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FetchOrgReportSpider(@Qualifier("wealthPasswordJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void fetch() {
        List<Map<String, Object>> datas = JdbcTemplateUtils.queryForList(jdbcTemplate,"select code, exchange from tb_stock_basic");
        if (datas != null) {
            for (Map<String, Object> data : datas) {
                fetchDate("2021-06-30", data.get("exchange").toString() + data.get("code").toString());
            }
        }
    }

    public void fetchDate(String date, String code) {
        String url = "http://emweb.securities.eastmoney.com/ShareholderResearch/PageJGCC?code={code}&date={reportDate}";
        String params = "{\"{code}\":\"" + code + "\",\"{reportDate}\":\"" + date + "\"}";
        String headersParams = "";
        String content = HttpClientUtils.get(url, StructureChangeUtils.stringToMap(params), null, null, String.class, false);
        try {
            JdbcTemplateUtils.update(jdbcTemplate, "");
        } catch (Exception e) {
            log.error(content, e);
        }
    }
}
