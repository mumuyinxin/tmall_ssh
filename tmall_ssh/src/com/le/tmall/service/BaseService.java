package com.le.tmall.service;

import com.le.tmall.util.Page;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface BaseService<T> {
    void add(T t);

    void delete(T t);

    void update(T t);

    T get(int id);

    void listByPage(Page page);

    List<T> findByKeyword(Object... pairParms);

    List<T> findAll();

    int getTotal();

    boolean isExist(Object... pairParms);

    List<T> findByCriteria(DetachedCriteria detachedCriteria, Page page);

    List<T> findIndistinctlyByKeyword(String column, String keyword);
}
