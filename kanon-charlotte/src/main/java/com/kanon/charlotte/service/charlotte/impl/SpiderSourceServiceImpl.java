package com.kanon.charlotte.service.charlotte.impl;

import com.kanon.charlotte.entity.SpiderSource;
import com.kanon.charlotte.mapper.SpiderSourceMapper;
import com.kanon.charlotte.service.charlotte.SpiderSourceService;
import com.kanon.common.core.text.Convert;
import com.kanon.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/13 15:17
 */
@Service("spiderSourceService")
public class SpiderSourceServiceImpl implements SpiderSourceService {

    private SpiderSourceMapper spiderSourceMapper;

    @Autowired
    public SpiderSourceServiceImpl(SpiderSourceMapper spiderSourceMapper) {
         this.spiderSourceMapper = spiderSourceMapper;
    }
    /**
     * 查询请求来源配置
     *
     * @param id 请求来源配置ID
     * @return 请求来源配置
     */
    @Override
    public SpiderSource selectSpiderSourceById(Long id) {
        return spiderSourceMapper.selectSpiderSourceById(id);
    }

    /**
     * 查询请求来源配置列表
     *
     * @param SpiderSource 请求来源配置
     * @return 请求来源配置
     */
    @Override
    public List<SpiderSource> selectSpiderSourceList(SpiderSource SpiderSource) {
        return spiderSourceMapper.selectSpiderSourceList(SpiderSource);
    }

    /**
     * 新增请求来源配置
     *
     * @param SpiderSource 请求来源配置
     * @return 结果
     */
    @Override
    public int insertSpiderSource(SpiderSource SpiderSource) {
        SpiderSource.setCreateTime(DateUtils.getNowDate());
        return spiderSourceMapper.insertSpiderSource(SpiderSource);
    }

    /**
     * 修改请求来源配置
     *
     * @param SpiderSource 请求来源配置
     * @return 结果
     */
    @Override
    public int updateSpiderSource(SpiderSource SpiderSource) {
        SpiderSource.setUpdateTime(DateUtils.getNowDate());
        return spiderSourceMapper.updateSpiderSource(SpiderSource);
    }

    /**
     * 删除请求来源配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpiderSourceByIds(String ids) {
        return spiderSourceMapper.deleteSpiderSourceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请求来源配置信息
     *
     * @param id 请求来源配置ID
     * @return 结果
     */
    @Override
    public int deleteSpiderSourceById(Long id) {
        return spiderSourceMapper.deleteSpiderSourceById(id);
    }
}
