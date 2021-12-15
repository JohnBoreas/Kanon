package com.kanon.charlotte.dao;

import com.kanon.charlotte.entity.SpiderRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpiderRequestDao {

    /**
     * 获取抓取的非淘宝客商品
     */
    @Select("SELECT header_name AS headerName, " +
            "header_value AS headerValue " +
            "FROM tb_spider_request WHERE spider_source = #{spiderSource}")
    List<SpiderRequest> selectBySource(@Param("spiderSource") String spiderSource);

}
