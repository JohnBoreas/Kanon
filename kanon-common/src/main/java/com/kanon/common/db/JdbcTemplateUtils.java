package com.kanon.common.db;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuhua.jiang
 * @date 2021/6/25 14:49
 */
public class JdbcTemplateUtils {
    /**
     * 查询一批数据，默认将每行数据转换为Map  List<Map<String, Object>>集
     * @param jdbcTemplate
     * @param sql
     * @param args
     * @return
     */
    public static List<Map<String, Object>> queryForList(JdbcTemplate jdbcTemplate,
                                                         String sql, @Nullable Object... args) {
        // 返回的是一个 List<Map<String, Object>> 类型
        return jdbcTemplate.queryForList(sql, args);
    }

    /**
     * RowCallbackHandler 返回的是多行数据
     * @param jdbcTemplate
     * @param sql
     * @param param
     * @return
     * @throws DataAccessException
     */
    public static List<Map<String, Object>> queryForList(JdbcTemplate jdbcTemplate,
                             String sql, List<String> param) throws DataAccessException {
        // RowCallbackHandler 返回的是多行数据
        List<Map<String, Object>> list = new ArrayList<>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            /**
             * RowCallbackHandler：用于处理ResultSet的每一行结果，
             *
             * 用户需实现方法processRow(ResultSet rs)来完成处理，
             * 在该回调方法中无需执行rs.next()，该操作由JdbcTemplate来执行， 用户只需按行获取数据然后处理即可。
             */
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                // 这里为每一行里的数据，相当于已经在 rs.next()里
                Map<String, Object> row = new HashMap<>();
                // 得到 ResultSetMetaData 对象
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                // 得到列的个数
                int columnCount = resultSetMetaData.getColumnCount();
                for(int i = 0 ; i < columnCount; i++){
                    // 列的别名
                    row.put(resultSetMetaData.getColumnLabel(i + 1), rs.getString(i + 1));
                }
                row.put(rs.getString(1), rs.getString(2));
                list.add(row);
            }
        }, param.toArray());
        return list;
    }

    /**
     * 查询一行数据并将该行数据转换为Map返回 ,查一条
     * @param jdbcTemplate
     * @param sql
     * @param args
     * @return
     */
    public static Map<String, Object> queryForMap(JdbcTemplate jdbcTemplate,
                                                         String sql, @Nullable Object... args) {
        // 返回的是一个 List<Map<String, Object>> 类型
        return jdbcTemplate.queryForMap(sql, args);
    }

    /**
     * 更新一条sql
     * @param jdbcTemplate
     * @param sql
     * @param args
     * @return
     */
    public static int update(JdbcTemplate jdbcTemplate,
                                    String sql, @Nullable Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    /**
     * PreparedStatementSetter：通过回调获取JdbcTemplate提供的PreparedStatement，由用户来对相应的预编译语句相应参数设值；
     * @param jdbcTemplate
     * @param sql
     * @param params
     */
    public static Long update(JdbcTemplate jdbcTemplate,
                              String sql, List<String> params) {
        // GeneratedKeyHolder，该类返回新增记录时的自增长主键值
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int re = jdbcTemplate.update(new PreparedStatementCreator() {
            // PreparedStatement ，执行预编译sql
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                // 指定主键 Statement.RETURN_GENERATED_KEYS
                PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sql), new String[]{"id"});
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setString(i + 1, params.get(i));
                }
                return preparedStatement;
            }
        }, keyHolder);
        // insert update 语句会返回2个
        return keyHolder.getKeyList().size() == 1 && keyHolder.getKey() != null ? keyHolder.getKey().longValue() : re;
    }

    /**
     * 批量操作
     * @param sql
     * @param batchArgs
     * @return
     */
    public static int[] batchUpdateDef(JdbcTemplate jdbcTemplate,
                             String sql, List<Object[]> batchArgs) {
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * 批处理
     * @param jdbcTemplate
     * @param sql
     * @param batchArgs
     * @return
     */
    public static int[] batchUpdate(JdbcTemplate jdbcTemplate,
                             String sql, List<List<String>> batchArgs) {
        // 批量增删改
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                // 相当与循环一次给一条sql赋值
                List<String> collection = batchArgs.get(i);
                for (int j = 0; j < collection.size(); j++) {
                    preparedStatement.setString(j + 1, collection.get(i));
                }
            }
            // 这里的 返回值就是上面要的参数
            @Override
            public int getBatchSize() {
                return batchArgs.size();
            }
        });
    }

    /**
     * Statement 每次执行sql，都要执行sql的编译，最好用于仅执行一次查询并返回结果的情形
     * PrepareStatement 预编译 ：
     *   A，执行可变参数的sql时，优于 Statement
     *   B，安全，防止sql注入
     *   C，可读性强，可维护
     * CallableStatement 扩展PrepareStatement 用于调用存储过程，提供了对输入输出参数的支持
     */
}
