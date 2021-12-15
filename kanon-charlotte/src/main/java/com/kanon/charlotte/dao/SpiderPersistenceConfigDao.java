package com.kanon.charlotte.dao;

import com.kanon.charlotte.entity.SpiderPersistence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author xuhua.jiang
 * @date 2021/6/22 17:49
 */
@Mapper
@Repository
public interface SpiderPersistenceConfigDao {

    /**
     * 获取抓取的非淘宝客商品
     */
    @Select("SELECT spider_source AS spiderSource, " +
            "table_name AS tableName, " +
            "insert_field AS insertField, " +
            "update_field AS updateField " +
            "FROM tb_spider_persistence_config WHERE spider_source = #{spiderSource} LIMIT 1")
    SpiderPersistence selectBySource(@Param("spiderSource") String spiderSource);
}
