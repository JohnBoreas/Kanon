package com.kanon.common.model.producer;

import com.kanon.common.db.JdbcTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/12/23 13:56
 */
@Slf4j
public class SelectDbProducer implements Runnable {

    private JdbcTemplate jdbcTemplate;

    private String selectSql;

    private ProcessorList processorList;

    private Object[] args;

    private String orderByName = "id";

    public SelectDbProducer(ProcessorList processorList, JdbcTemplate jdbcTemplate, String selectSql, Object...args) {
        this.processorList = processorList;
        this.jdbcTemplate = jdbcTemplate;
        this.selectSql = selectSql;
        this.args = args;
    }

    @Override
    public void run() {
        List<Map<String, Object>> list = null;
        boolean flag = true;
        while ((list != null && list.size() > 0) || flag) {

            list = JdbcTemplateUtils.queryForList(jdbcTemplate, selectSql, args);

            if (list == null || list.size() == 0) {
                log.warn("load data is empty.dont keeping on loop. then exit");
                flag = false;
                break;
            }
            if (null != orderByName) {
                try {
                    BigInteger id = new BigInteger(String.valueOf(list.get(list.size() - 1).get(orderByName)));
                    args[0] = id;
                    log.info("will put " + list.size() + " data to queue; next id : " + id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            processorList.doProcess(list);
            list.clear();
            list = null;
        }
    }
}
