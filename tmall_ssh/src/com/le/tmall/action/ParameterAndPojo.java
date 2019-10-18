package com.le.tmall.action;

import com.le.tmall.pojo.*;
import com.le.tmall.service.*;
import com.le.tmall.util.Page;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ParameterAndPojo<T> {
    protected Category category;
    protected Property property;
    protected Product product;
    protected ProductImage productImage;
    protected PropertyValue propertyValue;
    protected User user;
    protected Order order;
    protected Review review;
    protected OrderItem orderItem;

    protected List<Category> categoryList;
    protected List<Property> propertyList;
    protected List<Product> productList;
    protected List<ProductImage> productImageList;
    protected List<ProductImage> productSingleImageList;
    protected List<ProductImage> productDetailImageList;
    protected List<PropertyValue> propertyValueList;
    protected List<User> userList;
    protected List<Order> orderList;
    protected List<Review> reviewList;
    protected List<OrderItem> orderItemList;

    //错误消息
    protected String msg;
    //分类页面的排序变量
    protected String sort;
    //搜索关键字
    protected String keyword;
    //购物数量
    protected int num;
    //通过购物车选中的多个订单项id
    protected String[] oiids;

    CategoryService categoryService;
    OrderItemService orderItemService;
    OrderService orderService;
    ProductImageService productImageService;
    ProductService productService;
    PropertyService propertyService;
    PropertyValueService propertyValueService;
    ReviewService reviewService;
    UserService userService;
    File img;
    Page<T> page;
    boolean ajax = false;
    ActionContext actionContext = ActionContext.getContext();
    Map request = (Map) actionContext.get("request");
}
