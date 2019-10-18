package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.User;
import com.le.tmall.service.UserService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;


@Setter
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private BaseDao<User> baseDao;

    public void setBaseDao(BaseDao<User> baseDao) {
        super.setBaseDao(baseDao);
    }
}
