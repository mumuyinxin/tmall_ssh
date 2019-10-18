package com.le.tmall.action;

import com.le.tmall.util.comparator.*;
import com.le.tmall.pojo.*;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Setter
@Getter
public class FrontAction extends ParameterAndPojo {
    //前端登录成功后，开始加载商品
    public String frontLogin() {
        User user = (User) actionContext.getSession().get("user");
        List<Product> productList = productService.findAll();
        List<Category> categoryList = categoryService.findAll();
        List<Order> orderList = orderService.findByKeyword("status", "待付款", "user.id", user.getId());
        if (orderList != null)
            actionContext.getSession().put("cartTotalItemNumber", orderList.size());
        else
            actionContext.getSession().put("cartTotalItemNumber", 0);
        request.put("productList", productList);
        request.put("categoryList", categoryList);
        return "frontLogin";
    }

    public String frontCategory() {
        category = categoryService.get(category.getId());
        Set<Product> productSet = new HashSet<>();
        Iterator<Product> productIterator = category.getProducts().iterator();
        while (productIterator.hasNext()) {
            product = productIterator.next();
            productService.SetProductFirstImageAndReviewCountAndSaleCount(product);
            productSet.add(productService.get(product.getId()));//赋值firstImage图片
        }
        category.setProducts(productSet);

        List<Product> productList = new ArrayList<>(category.getProducts());
        if (sort == null)
            sort = "all";
        switch (sort) {
            case "review":
                Collections.sort(productList, new ProductReviewComparator());
                break;
            case "date":
                Collections.sort(productList, new ProductDateComparator());
                break;
            case "saleCount":
                Collections.sort(productList, new ProductSaleCountComparator());
                break;
            case "price":
                Collections.sort(productList, new ProductPriceComparator());
                break;
            case "all":
                Collections.sort(productList, new ProductAllComparator());
                break;
        }
        category.setProductList(productList);

        List<Category> categoryList = categoryService.findAll();
        request.put("categoryList", categoryList);
        request.put("category", category);
        request.put("sort", sort);
        return "findCategory";
    }

    //查找商品名称中含有某些关键字的商品集合
    public String frontSearch() {
        List<Category> categoryList = categoryService.findAll();
        request.put("categoryList", categoryList);
        List<Product> productList;
        if (!keyword.equals(""))
            productList = productService.findIndistinctlyByKeyword("name", keyword);
        else
            productList = productService.findAll();
        if (productList == null)
            return "findSearch";
        for (Product product : productList) {
            productService.SetProductFirstImageAndReviewCountAndSaleCount(product);
        }
        request.put("productList", productList);
        return "findSearch";
    }

    //点击某件商品
    public String frontProduct() {
        product = productService.get(product.getId());
        productService.SetProductFirstImageAndReviewCountAndSaleCount(product);
        List<Category> categoryList = categoryService.findAll();
        request.put("categoryList", categoryList);
        request.put("product", product);
        return "findProduct";
    }

    //立即购买商品，创建此商品订单项
    //或者是购物车，一键购买所有商品
    public String frontBuy() {
        User user = (User) actionContext.getSession().get("user");
        user = userService.get(user.getId());
        if (oiids != null) {
            order = orderService.createNewOrderByUserAndOiids(user, oiids);//返回一个全新订单，旧订单项
            List<Order> orderList = orderService.findByKeyword("status", "待付款");
            if (orderList != null)
                actionContext.getSession().put("cartTotalItemNumber", orderList.size());
            else
                actionContext.getSession().put("cartTotalItemNumber", 0);
            request.put("order", order);
            request.put("orderItems", order.getOrderItems());
            return "buy";
        }

        order = new Order();
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(0, 10000);
        order.setOrderCode(orderCode);
        product = productService.get(product.getId());
        product.setFirstImage(productImageService.findByKeyword("product.id", product.getId()).get(0));

        //先创建订单项，绑定到订单项集合
        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(num);
        orderItem.setUser(user);
        orderItem.setProduct(product);

        //将订单项绑定到订单
        order.setCreateDate(new Date());
        order.setStatus("待付款");
        order.setUser(user);

        orderItem.setOrder(order);//绑定订单项
        orderItemService.update(orderItem);//整的累了，早知道就用级联更新了，不过危险很大，容易更改到危险数据
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);

        orderService.SetOrderTotalPriceAndTotalNumber(order);

        List<Order> orderList = orderService.findByKeyword("status", "待付款");
        if (orderList != null)
            actionContext.getSession().put("cartTotalItemNumber", orderList.size());
        else
            actionContext.getSession().put("cartTotalItemNumber", 0);

        if (ajax)
            return "success";
        request.put("product", product);
        request.put("order", order);
        request.put("orderItems", orderItems);
        return "buy";
    }

    //填写详细信息，更新订单，准备付款
    public String frontPay() {
        Order oldOrder = orderService.get(order.getId());
        oldOrder.setCreateDate(new Date());
        oldOrder.setAddress(order.getAddress());
        oldOrder.setPost(order.getPost());
        oldOrder.setReceiver(order.getReceiver());
        oldOrder.setMobile(order.getMobile());
        oldOrder.setUserMessage(order.getUserMessage());
        orderService.update(oldOrder);
        orderService.SetOrderTotalPriceAndTotalNumber(oldOrder);//设置订单总价与总量
        request.put("order", oldOrder);
        return "pay";
    }

    //付款
    public String frontPayed() {
        float totalPrice = order.getTotalPrice();
        order = orderService.get(order.getId());
        order.setStatus("待发货");
        order.setPayDate(new Date());
        orderService.update(order);
        order.setTotalPrice(totalPrice);
        request.put("order", order);
        return "payed";
    }

    //查看用户所有订单
    public String frontBought() {
        User user = (User) actionContext.getSession().get("user");
        List<Order> orderList = orderService.findByKeyword("user.id", user.getId());
        orderService.SetOrderListTotalPriceAndTotalNumber(orderList);
        request.put("orderList", orderList);
        return "bought";
    }

    //催促卖家发货
    public String frontOrderDelivery() {
        order = orderService.get(order.getId());
        order.setStatus("待收货");
        order.setDeliveryDate(new Date());
        orderService.update(order);
        return null;
    }

    //收货页面，信息回显
    public String frontConfirmPay() {
        order = orderService.get(order.getId());
        orderService.SetOrderTotalPriceAndTotalNumber(order);
        request.put("order", order);
        return "ConfirmPay";
    }

    //确认收货
    public String frontOrderConfirmed() {
        order = orderService.get(order.getId());
        order.setStatus("待评价");
        order.setConfirmDate(new Date());
        orderService.update(order);
        return "orderConfirmed";
    }

    //跳转评论页面
    public String frontReview() {
        order = orderService.get(order.getId());
        product = order.getOrderItems().iterator().next().getProduct();
        productService.SetProductFirstImageAndReviewCountAndSaleCount(product);
        List<Category> categoryList = categoryService.findAll();
        request.put("categoryList", categoryList);
        request.put("order", order);
        request.put("product", product);
        return "review";
    }

    //添加评论，然后返回评论页面，显示所有商品“匿名”评论
    public String frontDoReview() {
        product = productService.get(product.getId());
        User user = (User) actionContext.getSession().get("user");
        user = userService.get(user.getId());
        review.setProduct(product);
        review.setUser(user);
        review.setCreateDate(new Date());
        reviewService.add(review);
        frontReview();
        request.put("showonly", true);
        return "review";
    }

    //加入购物车 或者是 查看购物车
    public String frontCart() {
        List<Category> categoryList = categoryService.findAll();
        request.put("categoryList", categoryList);
        User user = (User) actionContext.getSession().get("user");
        user = userService.get(user.getId());
        if (product != null) {
            product = productService.get(product.getId());
            productImageList = productImageService.findByKeyword("product.id", product.getId());
            if (productImageList.size() > 0)
                product.setFirstImage((ProductImage) productImageList.get(0));

            String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(0, 10000);
            order = new Order();
            order.setOrderCode(orderCode);

            //先创建订单项，绑定到订单项集合
            OrderItem orderItem = new OrderItem();
            orderItem.setNumber(1);//此处需要json上传购买数量，暂定购买数量为1
            orderItem.setUser(user);
            orderItem.setProduct(product);

            //将订单项绑定到订单
            Order order = new Order();
            order.setCreateDate(new Date());
            order.setStatus("待付款");
            order.setUser(user);

            Set<OrderItem> orderItems = new HashSet<>();
            orderItem.setOrder(order);//绑定订单项
            orderItems.add(orderItem);
            orderItemService.update(orderItem);//整的累了，早知道就用级联更新了
        }
        List<Order> orderList = orderService.findByKeyword("user.id", user.getId(), "status", "待付款");
        if (orderList != null)
            actionContext.getSession().put("cartTotalItemNumber", orderList.size());
        else
            actionContext.getSession().put("cartTotalItemNumber", 0);
        orderService.SetOrderListTotalPriceAndTotalNumber(orderList);
        //获取订单列表中的指定状态，以订单项的集合形式返回
        Set<OrderItem> orderItems = orderItemService.getOrderItemsByOrderListStatus(orderList, "待付款");
        request.put("orderItems", orderItems);
        return "cart";
    }

    public String frontOrderItemDelete() {
        orderItem = orderItemService.get(orderItem.getId());
        orderItem.getOrder().setStatus("删除");
        orderItemService.update(orderItem);
        return frontCart();
    }

    public String frontCheckLogin() {
        User user = (User) actionContext.getSession().get("user");
        if (user == null)
            return "fail";
        else
            return "success";
    }

}