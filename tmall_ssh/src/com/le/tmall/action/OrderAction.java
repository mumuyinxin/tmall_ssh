package com.le.tmall.action;

import com.le.tmall.pojo.Order;
import com.le.tmall.pojo.OrderItem;
import com.le.tmall.pojo.User;
import com.le.tmall.util.Page;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

@Setter
@Getter
public class OrderAction extends ParameterAndPojo<Order> {
    public String listOrder() {
        // 获取分页参数
        int count = 4;
        if (page == null)
            page = new Page();
        page.setCount(count);
        page.setTotal(orderService.getTotal());
        orderService.listByPage(page);
        orderService.SetOrderListTotalPriceAndTotalNumber(page.getList());
        request.put("orderList", page.getList());
        request.put("page", page);
        return "listOrder";
    }

    //前端订单删除，“删除”后重定向回前端订单页面
    public String delete() {
        order = orderService.get(order.getId());
        order.getUser().getOrders().remove(order);
        order.setUser(null);
        Iterator<OrderItem> orderItemIterator = order.getOrderItems().iterator();
        while (orderItemIterator.hasNext()) {
            orderItemIterator.next().setOrder(null);
        }
        order.setOrderItems(null);
        orderService.delete(order);
        return "redirectBought";
    }

    public String editStatus() {
        order = orderService.get(order.getId());
        order.setStatus("待收货");
        order.setDeliveryDate(new Date());
        orderService.update(order);
        return listOrder();
    }

    public String add() {
        User user = (User) actionContext.getSession().get("user");
        order.setUser(user);
        order.setCreateDate(new Date());
        orderService.add(order);
        return null;
    }
}
