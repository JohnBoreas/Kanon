package com.kanon.charlotte.service.spider;

import com.kanon.charlotte.entity.SpiderPersistence;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/15 16:41
 */
public interface SpiderPersistenceService {

    /**
     * 查询持久化配置
     *
     * @param id 持久化配置ID
     * @return 持久化配置
     */
    public SpiderPersistence selectSpiderPersistenceById(Long id);

    /**
     * 查询持久化配置列表
     *
     * @param spiderPersistence 持久化配置
     * @return 持久化配置集合
     */
    public List<SpiderPersistence> selectSpiderPersistenceList(SpiderPersistence spiderPersistence);

    /**
     * 新增持久化配置
     *
     * @param spiderPersistence 持久化配置
     * @return 结果
     */
    public int insertSpiderPersistence(SpiderPersistence spiderPersistence);

    /**
     * 修改持久化配置
     *
     * @param spiderPersistence 持久化配置
     * @return 结果
     */
    public int updateSpiderPersistence(SpiderPersistence spiderPersistence);

    /**
     * 批量删除持久化配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderPersistenceByIds(String ids);

    /**
     * 删除持久化配置信息
     *
     * @param id 持久化配置ID
     * @return 结果
     */
    public int deleteSpiderPersistenceById(Long id);
}
