package com.kanon.charlotte.service.spider;

import com.kanon.charlotte.entity.SpiderRequest;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/14 18:47
 */
public interface SpiderRequestService {
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
     * 批量删除请求request配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderRequestByIds(String ids);

    /**
     * 删除请求request配置信息
     *
     * @param id 请求request配置ID
     * @return 结果
     */
    public int deleteSpiderRequestById(Long id);
}
