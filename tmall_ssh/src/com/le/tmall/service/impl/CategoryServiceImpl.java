package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.Category;
import com.le.tmall.service.CategoryService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
    private BaseDao<Category> baseDao;

    public void setBaseDao(BaseDao<Category> baseDao) {
        super.setBaseDao(baseDao);
    }
}
