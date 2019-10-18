package com.le.tmall.dao.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.util.Page;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
//    @Resource(name = "sessionFactory")
//    public void setHibernateTemplate(SessionFactory sessionFactory) {
//        super.createHibernateTemplate(sessionFactory);
//        super.setSessionFactory(sessionFactory);
//    }

    @Override
    public void add(T t) {
        this.getHibernateTemplate().merge(t);
    }

    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    @Override
    public T get(Class clazz, int id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        detachedCriteria.setProjection(null);
        detachedCriteria.add(Restrictions.eq("id", id));
        List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0);
        } else
            return null;
    }

    @Override
    public int getTotal(Class clazz) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public void listByPage(Class clazz, Page page) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        detachedCriteria.setProjection(null);
        detachedCriteria.addOrder(Order.desc("id"));
        page.setList(this.getHibernateTemplate().findByCriteria(detachedCriteria, page.getStart(), page.getCount()));
    }

    @Override
    public List<T> findByKeyword(Class clazz, Object... pairParms) {
        HashMap<String, Object> m = new HashMap<>();
        for (int i = 0; i < pairParms.length; i = i + 2)
            m.put(pairParms[i].toString(), pairParms[i + 1]);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        Set<String> ks = m.keySet();
        for (String key : ks) {
            if (null == m.get(key))
                detachedCriteria.add(Restrictions.isNull(key));
            else
                detachedCriteria.add(Restrictions.eq(key, m.get(key)));
        }
        //除商品图片，其余数据对象皆按降序Id排列
        if (!clazz.getSimpleName().equals("ProductImage"))
            detachedCriteria.addOrder(Order.desc("id"));
        List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0)
            return list;
        else
            return null;
    }

    // 获取所有实体
    @Override
    public List findAll(Class clazz) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        detachedCriteria.setProjection(null);
        List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0)
            return list;
        else
            return null;
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria, Page page) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, page.getStart(), page.getCount());
    }

    @Override
    public List<T> findIndistinctlyByKeyword(Class clazz, String column, String keyword) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        detachedCriteria.setProjection(null);
        detachedCriteria.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
        detachedCriteria.addOrder(Order.desc("id"));
        List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0)
            return list;
        else
            return null;
    }
}

