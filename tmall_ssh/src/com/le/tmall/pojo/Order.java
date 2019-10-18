package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Order {
    private int id;
    private String orderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private Date createDate;//创建日期
    private Date payDate;//付款日期
    private Date deliveryDate;//发货日期
    private Date confirmDate;//确认收货日期
    private String status;
    private int uid;

    private User user;
    private Set<OrderItem> orderItems = new HashSet<>();

    //拓展字段
    private int totalNumber;
    private float totalPrice;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", address='" + address + '\'' +
                ", post='" + post + '\'' +
                ", receiver='" + receiver + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                ", deliveryDate=" + deliveryDate +
                ", confirmDate=" + confirmDate +
                ", status='" + status + '\'' +
                ", uid=" + uid +
                ", totalNumber=" + totalNumber +
                ", totalPrice=" + totalPrice +
                '}';
    }
}