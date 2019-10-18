package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.Order;
import com.le.tmall.pojo.OrderItem;
import com.le.tmall.pojo.User;
import com.le.tmall.service.OrderService;
import lombok.Setter;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Setter
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    private BaseDao<Order> baseDao;

    public void setBaseDao(BaseDao<Order> baseDao) {
        super.setBaseDao(baseDao);
    }

    //先创建新订单包含所有旧的订单项，然后删除旧的订单，返回新订单
    @Override
    public Order createNewOrderByUserAndOiids(User user, String[] oiids) {
        Order order = new Order();
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(0, 10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUser(user);
        //遇到这个非常奇葩的问题，就是setStatus("待付款")，非得出现乱码.....
        order.setStatus(new String("待付款".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));

        //设置新订单，接过旧订单项
        Set<OrderItem> orderItems = new HashSet<>();
        float totalPrice = 0;
        int totalNumber = 0;
        for (String oiid : oiids) {
            OrderItem orderItem = orderItemService.get(Integer.parseInt(oiid));
            orderItem.getProduct().setFirstImage(
                    productImageService
                            .findByKeyword("product.id", orderItem.getProduct().getId())
                            .get(0));
            totalPrice += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
            totalNumber += orderItem.getNumber();
            orderItems.add(orderItem);
        }
        order.setTotalPrice(totalPrice);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);

        //设置旧订单状态为“删除”
        for (String oiid : oiids) {
            OrderItem orderItem = orderItemService.get(Integer.parseInt(oiid));
            if (orderItem.getOrder() != null) {
                Order oldOrder = this.get(orderItem.getOrder().getId());
                oldOrder.setStatus("合并");
                this.update(oldOrder);
            }
        }
        this.add(order);
        order.setId((this.findByKeyword("user.id", user.getId()).get(0)).getId());
        return order;
    }

    //设定订单总价、总数量、订单商品图片
    @Override
    public void SetOrderTotalPriceAndTotalNumber(Order order) {
        float totalPrice = 0;
        int totalNumber = 0;
        Iterator<OrderItem> iterator = order.getOrderItems().iterator();
        while (iterator.hasNext()) {
            OrderItem orderItem = iterator.next();
            orderItem.getProduct().setFirstImage(productImageService.
                    findByKeyword("product.id", orderItem.getProduct().getId()).get(0));
            totalPrice += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
            totalNumber += orderItem.getNumber();
        }
        order.setTotalPrice(totalPrice);
        order.setTotalNumber(totalNumber);
    }

    @Override
    public void SetOrderListTotalPriceAndTotalNumber(List<Order> orderList) {
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                SetOrderTotalPriceAndTotalNumber(orderList.get(i));
            }
        }
    }
}