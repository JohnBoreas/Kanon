package com.kanon.charlotte.service;

import java.util.List;

/**
 * 通用CRUD抽象接口
 * @author jiangxvhua
 * @date 创建时间  2017年1月10日 下午4:00:46
 */
public interface IBaseService<T> {

    /**
     * 分页显示列表
     * @param start
     * @param limit
     * @param model
     * @return
     */
//    Page<T> selectPage(int start, int limit, T model);
    /**
     * 根据条件查询角色
     * @param model
     * @return
     */
    List<T> selectList(T model);
    /**
     * 根据ID查询角色
     * @param id
     * @return
     */
    T selectEntityById(String id);
    /**
     * 添加Or更新角色
     * @param model
     */
    T addOrUpdate(T model);
    /**
     * 根据ID删除角色
     * @param id
     */
    void deleteById(String id);
}
