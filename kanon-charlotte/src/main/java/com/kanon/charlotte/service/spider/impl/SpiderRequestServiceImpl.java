package com.kanon.charlotte.service.spider.impl;

import com.kanon.charlotte.entity.SpiderRequest;
import com.kanon.charlotte.mapper.SpiderRequestMapper;
import com.kanon.charlotte.service.spider.SpiderRequestService;
import com.kanon.common.core.text.Convert;
import com.kanon.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("spiderRequestService")
public class SpiderRequestServiceImpl implements SpiderRequestService {

    private SpiderRequestMapper spiderRequestMapper;

    @Autowired
    public SpiderRequestServiceImpl(SpiderRequestMapper spiderRequestMapper) {
        this.spiderRequestMapper = spiderRequestMapper;
    }

    /**
     * 查询请求request配置
     *
     * @param id 请求request配置ID
     * @return 请求request配置
     */
    @Override
    public SpiderRequest selectSpiderRequestById(Long id) {
        return spiderRequestMapper.selectSpiderRequestById(id);
    }

    /**
     * 查询请求request配置列表
     *
     * @param SpiderRequest 请求request配置
     * @return 请求request配置
     */
    @Override
    public List<SpiderRequest> selectSpiderRequestList(SpiderRequest SpiderRequest) {
        return spiderRequestMapper.selectSpiderRequestList(SpiderRequest);
    }

    /**
     * 新增请求request配置
     *
     * @param SpiderRequest 请求request配置
     * @return 结果
     */
    @Override
    public int insertSpiderRequest(SpiderRequest SpiderRequest) {
        SpiderRequest.setCreateTime(DateUtils.getNowDate());
        return spiderRequestMapper.insertSpiderRequest(SpiderRequest);
    }

    /**
     * 修改请求request配置
     *
     * @param SpiderRequest 请求request配置
     * @return 结果
     */
    @Override
    public int updateSpiderRequest(SpiderRequest SpiderRequest) {
        SpiderRequest.setUpdateTime(DateUtils.getNowDate());
        return spiderRequestMapper.updateSpiderRequest(SpiderRequest);
    }

    /**
     * 删除请求request配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpiderRequestByIds(String ids) {
        return spiderRequestMapper.deleteSpiderRequestByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请求request配置信息
     *
     * @param id 请求request配置ID
     * @return 结果
     */
    @Override
    public int deleteSpiderRequestById(Long id) {
        return spiderRequestMapper.deleteSpiderRequestById(id);
    }
}
