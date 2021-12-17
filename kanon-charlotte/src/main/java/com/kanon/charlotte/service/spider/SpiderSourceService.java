package com.kanon.charlotte.service.spider;

import com.kanon.charlotte.entity.SpiderSource;

import java.util.List;

public interface SpiderSourceService {

    /**
     * 查询请求来源配置
     *
     * @param id 请求来源配置ID
     * @return 请求来源配置
     */
    public SpiderSource selectSpiderSourceById(Long id);

    /**
     * 查询请求来源配置列表
     *
     * @param spiderSource 请求来源配置
     * @return 请求来源配置集合
     */
    public List<SpiderSource> selectSpiderSourceList(SpiderSource spiderSource);

    /**
     * 新增请求来源配置
     *
     * @param spiderSource 请求来源配置
     * @return 结果
     */
    public int insertSpiderSource(SpiderSource spiderSource);

    /**
     * 修改请求来源配置
     *
     * @param spiderSource 请求来源配置
     * @return 结果
     */
    public int updateSpiderSource(SpiderSource spiderSource);

    /**
     * 批量删除请求来源配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderSourceByIds(String ids);

    /**
     * 删除请求来源配置信息
     *
     * @param id 请求来源配置ID
     * @return 结果
     */
    public int deleteSpiderSourceById(Long id);
}
