package com.le.tmall.service;

import com.le.tmall.pojo.Property;
import com.le.tmall.util.Page;

public interface PropertyService extends BaseService<Property> {
    int getTotalByCid(int cid);

    void listPageByCid(Page page, int cid);
}
