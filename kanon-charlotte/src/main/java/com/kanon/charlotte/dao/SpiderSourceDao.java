package com.kanon.charlotte.dao;

import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.entity.SpiderPersistence;
import com.kanon.charlotte.entity.SpiderRequest;
import com.kanon.charlotte.entity.SpiderSource;
import com.kanon.common.annotation.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@DataSource(value = "spiderDataSource")
public interface SpiderSourceDao {

    /**
     * 获取抓取的非淘宝客商品
     */
    @Select("SELECT spider_source AS spiderSource, " +
            "source_des AS sourceDes, " +
            "req_url AS reqUrl, " +
            "req_method AS reqMethod, " +
            "req_param AS reqParam, " +
            "data_type AS dataType, " +
            "need_proxy AS needProxy, " +
            "need_cookie AS needCookie, " +
            "need_token AS needToken, " +
            "is_last_cookie AS isLastCookie, " +
            "create_time AS createTime,  " +
            "update_time AS updateTime " +
            "FROM tb_spider_source WHERE spider_source = #{spiderSource} LIMIT 1")
    SpiderSource selectSourceBySource(@Param("spiderSource") String spiderSource);

    /**
     * 获取抓取的非淘宝客商品
     */
    @Select("SELECT header_name AS headerName, " +
            "header_value AS headerValue " +
            "FROM tb_spider_request WHERE spider_source = #{spiderSource}")
    List<SpiderRequest> selectRequestBySource(@Param("spiderSource") String spiderSource);
    /**
     * 获取抓取的非淘宝客商品
     */
    @Select("SELECT spider_source AS spiderSource, " +
            "table_name AS tableName, " +
            "insert_field AS insertField, " +
            "update_field AS updateField " +
            "FROM tb_spider_persistence_config WHERE spider_source = #{spiderSource} LIMIT 1")
    SpiderPersistence selectPersistenceBySource(@Param("spiderSource") String spiderSource);

    /**
     * 获取抓取的非淘宝客商品
     */
    @Select("SELECT explain_name AS explainName, " +
            "explain_value AS explainValue, value_type AS valueType " +
            "FROM tb_spider_explain_string WHERE spider_source = #{spiderSource}")
    List<SpiderExplain> selectExplainBySource(@Param("spiderSource") String spiderSource);
}
