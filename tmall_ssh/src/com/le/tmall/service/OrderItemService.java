package com.le.tmall.service;

import com.le.tmall.pojo.Order;
import com.le.tmall.pojo.OrderItem;

import java.util.List;
import java.util.Set;

public interface OrderItemService extends BaseService<OrderItem> {

    Set<OrderItem> getOrderItemsByOrderListStatus(List<Order> orderList, String param);
}
