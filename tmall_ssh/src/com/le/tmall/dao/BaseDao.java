package com.le.tmall.dao;

import com.le.tmall.util.Page;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface BaseDao<T> {
    // 添加实体
    void add(T t);

    // 删除实体
    void delete(T t);

    // 更新实体
    void update(T t);

    // 根据ID加载实体
    T get(Class entityClazz, int id);

    // 获取部分实体
    void listByPage(Class clazz, Page page);

    // 根据关键字搜索实体
    List<T> findByKeyword(Class clazz, Object... pairParms);

    // 获取所有实体
    List<T> findAll(Class clazz);

    //获取实体总数
    int getTotal(Class clazz);

    List<T> findByCriteria(DetachedCriteria detachedCriteria, Page page);

    List<T> findIndistinctlyByKeyword(Class clazz, String column, String keyword);
}
