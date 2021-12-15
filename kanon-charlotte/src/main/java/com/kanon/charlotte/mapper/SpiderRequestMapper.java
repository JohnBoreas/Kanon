package com.kanon.charlotte.mapper;

import com.kanon.charlotte.entity.SpiderRequest;
import com.kanon.common.annotation.DataSource;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/14 18:47
 */
@DataSource(value = "spiderDataSource")
public interface SpiderRequestMapper {

    /**
     * 查询请求request配置
     *
     * @param id 请求request配置ID
     * @return 请求request配置
     */
    public SpiderRequest selectSpiderRequestById(Long id);

    /**
     * 查询请求request配置列表
     *
     * @param spiderRequest 请求request配置
     * @return 请求request配置集合
     */
    public List<SpiderRequest> selectSpiderRequestList(SpiderRequest spiderRequest);

    /**
     * 新增请求request配置
     *
     * @param spiderRequest 请求request配置
     * @return 结果
     */
    public int insertSpiderRequest(SpiderRequest spiderRequest);

    /**
     * 修改请求request配置
     *
     * @param spiderRequest 请求request配置
     * @return 结果
     */
    public int updateSpiderRequest(SpiderRequest spiderRequest);

    /**
     * 删除请求request配置
     *
     * @param id 请求request配置ID
     * @return 结果
     */
    public int deleteSpiderRequestById(Long id);

    /**
     * 批量删除请求request配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderRequestByIds(String[] ids);
}
