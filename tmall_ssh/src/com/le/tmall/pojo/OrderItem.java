package com.le.tmall.pojo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItem {
    private int id;
    private int pid;
    private int oid;
    private int uid;
    private int number;
    private User user;
    private Product product;
    private Order order;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", pid=" + pid +
                ", oid=" + oid +
                ", uid=" + uid +
                ", number=" + number +
                ", user=" + user +
                ", order=" + order +
                '}';
    }
}
