package com.kanon.charlotte.service.spider.impl;

import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.mapper.SpiderExplainMapper;
import com.kanon.charlotte.service.spider.SpiderExplainService;
import com.kanon.common.core.text.Convert;
import com.kanon.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("spiderExplainService")
public class SpiderExplainServiceImpl implements SpiderExplainService {

    private SpiderExplainMapper spiderExplainMapper;

    @Autowired
    public SpiderExplainServiceImpl(SpiderExplainMapper spiderExplainMapper) {
        this.spiderExplainMapper = spiderExplainMapper;
    }

    /**
     * 查询返回内容解析规则配置
     *
     * @param id 返回内容解析规则配置ID
     * @return 返回内容解析规则配置
     */
    @Override
    public SpiderExplain selectSpiderExplainById(Long id) {
        return spiderExplainMapper.selectSpiderExplainById(id);
    }

    /**
     * 查询返回内容解析规则配置列表
     *
     * @param spiderExplain 返回内容解析规则配置
     * @return 返回内容解析规则配置
     */
    @Override
    public List<SpiderExplain> selectSpiderExplainList(SpiderExplain spiderExplain) {
        return spiderExplainMapper.selectSpiderExplainList(spiderExplain);
    }

    /**
     * 新增返回内容解析规则配置
     *
     * @param spiderExplain 返回内容解析规则配置
     * @return 结果
     */
    @Override
    public int insertSpiderExplain(SpiderExplain spiderExplain) {
        spiderExplain.setCreateTime(DateUtils.getNowDate());
        return spiderExplainMapper.insertSpiderExplain(spiderExplain);
    }

    /**
     * 修改返回内容解析规则配置
     *
     * @param spiderExplain 返回内容解析规则配置
     * @return 结果
     */
    @Override
    public int updateSpiderExplain(SpiderExplain spiderExplain) {
        spiderExplain.setUpdateTime(DateUtils.getNowDate());
        return spiderExplainMapper.updateSpiderExplain(spiderExplain);
    }

    /**
     * 删除返回内容解析规则配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpiderExplainByIds(String ids) {
        return spiderExplainMapper.deleteSpiderExplainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除返回内容解析规则配置信息
     *
     * @param id 返回内容解析规则配置ID
     * @return 结果
     */
    @Override
    public int deleteSpiderExplainById(Long id) {
        return spiderExplainMapper.deleteSpiderExplainById(id);
    }
}
