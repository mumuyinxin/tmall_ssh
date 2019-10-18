package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.ProductImage;
import com.le.tmall.service.ProductImageService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Transactional
public class ProductImageServiceImpl extends BaseServiceImpl<ProductImage> implements ProductImageService {
    private BaseDao<ProductImage> baseDao;

    public void setBaseDao(BaseDao<ProductImage> baseDao) {
        super.setBaseDao(baseDao);
    }
}