package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.Review;
import com.le.tmall.service.ReviewService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Transactional
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewService {
    private BaseDao<Review> baseDao;

    public void setBaseDao(BaseDao<Review> baseDao) {
        super.setBaseDao(baseDao);
    }
}
