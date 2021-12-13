package com.kanon.admin.service;

import com.kanon.admin.entity.SystemConfig;

import java.util.List;

/**
 * 参数配置 服务层
 */
public interface ISysConfigService {
    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    public SystemConfig selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String selectConfigByKey(String configKey);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @param fromCache 默认true从cache取值否则从数据库
     * @return 参数键值
     */
    public String selectConfigByKey(String configKey, boolean fromCache);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    public List<SystemConfig> selectConfigList(SystemConfig config);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    public int insertConfig(SystemConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    public int updateConfig(SystemConfig config);

    /**
     * 批量删除参数配置信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigByIds(String ids);

    /**
     * 清空缓存数据
     */
    public void clearCache();

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数信息
     * @return 结果
     */
    public String checkConfigKeyUnique(SystemConfig config);

    /**
     * @param key
     * @param configValue
     * @author zmr
     */
    public int updateValueByKey(String key, String configValue);
}
