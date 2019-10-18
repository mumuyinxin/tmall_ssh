package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class User {
    private int id;
    private String name;
    private String password;

    private Set<Order> orders = new HashSet<>();
    private Set<OrderItem> orderItems = new HashSet<>();
    private Set<Review> reviews = new HashSet<>();

    //造匿名
    public String getAnonymousName() {
        if (null == name)
            return null;

        if (name.length() <= 1)
            return "*";

        if (name.length() == 2)
            return name.substring(0, 1) + "*";

        char[] cs = name.toCharArray();
        for (int i = 1; i < cs.length - 1; i++) {
            cs[i] = '*';
        }
        return new String(cs);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
