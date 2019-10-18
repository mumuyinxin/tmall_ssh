package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.Category;
import com.le.tmall.pojo.Property;
import com.le.tmall.service.PropertyService;
import com.le.tmall.util.Page;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Transactional
public class PropertyServiceImpl extends BaseServiceImpl<Property> implements PropertyService {
    private BaseDao<Property> baseDao;

    public void setBaseDao(BaseDao<Property> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public int getTotalByCid(int cid) {
        Category category = categoryService.get(cid);
        return category.getPropertys().size();
    }

    @Override
    public void listPageByCid(Page page, int cid) {
        DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
        dc.add(Restrictions.eq("category.id", cid));
        dc.addOrder(Order.desc("id"));
        page.setList(findByCriteria(dc, page));
    }
}