package com.le.tmall.service;

import com.le.tmall.pojo.Order;
import com.le.tmall.pojo.User;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    Order createNewOrderByUserAndOiids(User user, String[] oiids);

    void SetOrderTotalPriceAndTotalNumber(Order order);

    void SetOrderListTotalPriceAndTotalNumber(List<Order> orderList);
}
