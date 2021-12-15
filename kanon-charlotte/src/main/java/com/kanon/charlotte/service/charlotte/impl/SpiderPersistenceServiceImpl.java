package com.kanon.charlotte.service.charlotte.impl;

import com.kanon.charlotte.entity.SpiderPersistence;
import com.kanon.charlotte.mapper.SpiderPersistenceMapper;
import com.kanon.charlotte.service.charlotte.SpiderPersistenceService;
import com.kanon.common.core.text.Convert;
import com.kanon.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/15 17:20
 */
@Service("spiderPersistenceService")
public class SpiderPersistenceServiceImpl implements SpiderPersistenceService {

    private SpiderPersistenceMapper spiderPersistenceMapper;

    @Autowired
    public SpiderPersistenceServiceImpl(SpiderPersistenceMapper spiderPersistenceMapper) {
        this.spiderPersistenceMapper = spiderPersistenceMapper;
    }
    /**
     * 查询持久化配置
     *
     * @param id 持久化配置ID
     * @return 持久化配置
     */
    @Override
    public SpiderPersistence selectSpiderPersistenceById(Long id) {
        return spiderPersistenceMapper.selectSpiderPersistenceById(id);
    }

    /**
     * 查询持久化配置列表
     *
     * @param SpiderPersistence 持久化配置
     * @return 持久化配置
     */
    @Override
    public List<SpiderPersistence> selectSpiderPersistenceList(SpiderPersistence SpiderPersistence) {
        return spiderPersistenceMapper.selectSpiderPersistenceList(SpiderPersistence);
    }

    /**
     * 新增持久化配置
     *
     * @param SpiderPersistence 持久化配置
     * @return 结果
     */
    @Override
    public int insertSpiderPersistence(SpiderPersistence SpiderPersistence) {
        SpiderPersistence.setCreateTime(DateUtils.getNowDate());
        return spiderPersistenceMapper.insertSpiderPersistence(SpiderPersistence);
    }

    /**
     * 修改持久化配置
     *
     * @param SpiderPersistence 持久化配置
     * @return 结果
     */
    @Override
    public int updateSpiderPersistence(SpiderPersistence SpiderPersistence) {
        SpiderPersistence.setUpdateTime(DateUtils.getNowDate());
        return spiderPersistenceMapper.updateSpiderPersistence(SpiderPersistence);
    }

    /**
     * 删除持久化配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpiderPersistenceByIds(String ids) {
        return spiderPersistenceMapper.deleteSpiderPersistenceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除持久化配置信息
     *
     * @param id 持久化配置ID
     * @return 结果
     */
    @Override
    public int deleteSpiderPersistenceById(Long id) {
        return spiderPersistenceMapper.deleteSpiderPersistenceById(id);
    }
}
