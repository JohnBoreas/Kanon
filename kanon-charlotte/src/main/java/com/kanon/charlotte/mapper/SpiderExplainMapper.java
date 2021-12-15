package com.kanon.charlotte.mapper;

import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.common.annotation.DataSource;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/15 16:03
 */
@DataSource(value = "spiderDataSource")
public interface SpiderExplainMapper {
    /**
     * 查询返回内容解析规则配置
     *
     * @param id 返回内容解析规则配置ID
     * @return 返回内容解析规则配置
     */
    public SpiderExplain selectSpiderExplainById(Long id);

    /**
     * 查询返回内容解析规则配置列表
     *
     * @param spiderExplain 返回内容解析规则配置
     * @return 返回内容解析规则配置集合
     */
    public List<SpiderExplain> selectSpiderExplainList(SpiderExplain spiderExplain);

    /**
     * 新增返回内容解析规则配置
     *
     * @param spiderExplain 返回内容解析规则配置
     * @return 结果
     */
    public int insertSpiderExplain(SpiderExplain spiderExplain);

    /**
     * 修改返回内容解析规则配置
     *
     * @param spiderExplain 返回内容解析规则配置
     * @return 结果
     */
    public int updateSpiderExplain(SpiderExplain spiderExplain);

    /**
     * 删除返回内容解析规则配置
     *
     * @param id 返回内容解析规则配置ID
     * @return 结果
     */
    public int deleteSpiderExplainById(Long id);

    /**
     * 批量删除返回内容解析规则配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderExplainByIds(String[] ids);
}
