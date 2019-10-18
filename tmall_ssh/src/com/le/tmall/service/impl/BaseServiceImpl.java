package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.service.*;
import com.le.tmall.util.Page;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Setter
@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    UserService userService;
    @Autowired
    BaseDao<T> baseDao;
    private Class<T> clazz;

    public BaseServiceImpl() {
        //this表示当前被实例化的对象；如BaseServiceImpl
        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();//BaseServiceImpl<User>
            clazz = (Class<T>) pt.getActualTypeArguments()[0];
        } catch (Exception ex) {
            System.out.println("BaseServiceImpl----这里有个小异常:" + ex);
        }

    }

    //凡是继承了BaseDao的Dao类，都能使用
    //通过此方法可改变调用的Dao对象
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void add(T t) {
        baseDao.add(t);
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public T get(int id) {
        return baseDao.get(clazz, id);
    }

    @Override
    public void listByPage(Page page) {
        baseDao.listByPage(clazz, page);
    }

    @Override
    public List<T> findByKeyword(Object... pairParms) {
        return baseDao.findByKeyword(clazz, pairParms);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll(clazz);
    }

    @Override
    public int getTotal() {
        return baseDao.getTotal(clazz);
    }

    @Override
    public boolean isExist(Object... pairParms) {
        return findByKeyword(pairParms) != null;
    }


    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria, Page page) {
        return baseDao.findByCriteria(detachedCriteria, page);
    }

    @Override
    public List<T> findIndistinctlyByKeyword(String column, String keyword) {
        return baseDao.findIndistinctlyByKeyword(clazz, column, keyword);
    }
}
