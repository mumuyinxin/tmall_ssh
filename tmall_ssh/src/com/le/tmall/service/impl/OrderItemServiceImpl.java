package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.Order;
import com.le.tmall.pojo.OrderItem;
import com.le.tmall.service.OrderItemService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Setter
@Transactional
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem> implements OrderItemService {
    private BaseDao<OrderItem> baseDao;

    public void setBaseDao(BaseDao<OrderItem> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public Set<OrderItem> getOrderItemsByOrderListStatus(List<Order> orderList, String param) {
        Set<OrderItem> orderItems = new HashSet<>();
        if (orderList == null)
            return null;
        for (Order order : orderList) {
            Iterator<OrderItem> iterator = order.getOrderItems().iterator();
            while (iterator.hasNext()) {
                OrderItem orderItem = iterator.next();
                if (order.getStatus().equals(param)) {
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }
}