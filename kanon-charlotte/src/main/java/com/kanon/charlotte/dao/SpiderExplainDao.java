package com.kanon.charlotte.dao;

import com.kanon.charlotte.entity.SpiderExplain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpiderExplainDao {

    /**
     * 获取抓取的非淘宝客商品
     */
    @Select("SELECT explain_name AS explainName, " +
            "explain_value AS explainValue, value_type AS valueType " +
            "FROM tb_spider_explain_string WHERE spider_source = #{spiderSource}")
    List<SpiderExplain> selectBySource(@Param("spiderSource") String spiderSource);

}
