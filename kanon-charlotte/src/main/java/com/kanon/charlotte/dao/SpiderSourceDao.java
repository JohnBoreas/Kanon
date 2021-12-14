package com.kanon.charlotte.dao;

import com.kanon.charlotte.entity.SpiderSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
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
    SpiderSource selectBySource(@Param("spiderSource") String spiderSource);

}
