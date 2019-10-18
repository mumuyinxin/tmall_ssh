package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.PropertyValue;
import com.le.tmall.service.PropertyValueService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Transactional
public class PropertyValueServiceImpl extends BaseServiceImpl<PropertyValue> implements PropertyValueService {
    private BaseDao<PropertyValue> baseDao;

    public void setBaseDao(BaseDao<PropertyValue> baseDao) {
        super.setBaseDao(baseDao);
    }
}
