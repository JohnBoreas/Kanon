package com.kanon.charlotte.service.persistence.impl;

import com.kanon.charlotte.param.PersistenceParam;
import com.kanon.charlotte.service.persistence.PersistenceDataService;
import com.kanon.common.db.JdbcTemplateUtils;
import com.kanon.charlotte.util.StructureChangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/17 19:38
 */
@Service
@Slf4j
public class DbPersistenceDataService implements PersistenceDataService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DbPersistenceDataService(@Qualifier("wealthJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer save(PersistenceParam param) {
        String table = param.getTableName();
        List<Map<String, String>> results = param.getSpiderPageResult().getResults();
        int re = 0;
        if (results != null) {
            Map<String, String> insertData = StructureChangeUtils.stringToMap(param.getInsertField());
            Map<String, String> updateData = StructureChangeUtils.stringToMap(param.getUpdateField());
            for (Map<String, String> result : results) {
                if (insertData == null || insertData.size() == 0) {
                    return -1;
                }
                StringBuilder fieldSql = new StringBuilder();
                StringBuilder valueSql = new StringBuilder();
                StringBuilder updateSql = new StringBuilder();
                List<String> field = new ArrayList<>();
                List<String> updateField = new ArrayList<>();
                List<String> value = new ArrayList<>();
                List<String> params = new ArrayList<>();
                for (java.util.Map.Entry<String, String> entry : insertData.entrySet()) {
                    field.add(entry.getValue() + "");
                    params.add(result.get(entry.getKey()) + "");
                    value.add("?");
                }

                if (updateData != null) {
                    for (java.util.Map.Entry<String, String> entry : updateData.entrySet()) {
                        String col = entry.getValue();
                        if (col.equals("fetch_time") || col.equals("fetch_time")) {
                            updateField.add(entry.getValue() + " = NOW()");
                            continue;
                        }
                        updateField.add(entry.getValue() + " = IFNULL(?, " + entry.getValue() + ")");
                        params.add(result.get(entry.getKey()) + "");
                    }
                }

                fieldSql.append(" (").append(StringUtils.join(field, ",")).append(") ");
                valueSql.append(" (").append(StringUtils.join(value, ",")).append(") ");
                updateSql.append(" ").append(StringUtils.join(updateField, ",")).append(" ");
                StringBuilder sql = new StringBuilder();
                sql.append("insert into ").append(table).append(fieldSql).append("values ").append(valueSql);
                if (updateData != null && updateData.size() > 0) {
                    sql.append("on duplicate key update").append(updateSql);
                }

                log.info("insert sql : " + sql.toString());
                // 更新语句
                JdbcTemplateUtils.update(jdbcTemplate, String.valueOf(sql), params);
            }
        }
        return re;
    }
}
