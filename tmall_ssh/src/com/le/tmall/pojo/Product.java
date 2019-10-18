package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class Product {
    private int id;
    private String name;
    private String subTitle;
    private float originalPrice;
    private float promotePrice;
    private int stock;
    private Date createDate;
    private int cid;

    private Category category;
    private Set<OrderItem> orderItems;
    private Set<Review> reviews;
    private Set<ProductImage> productImages;
    private Set<PropertyValue> propertyValues;

    //延伸属性
    private List<ProductImage> productSingleImages;
    private List<ProductImage> productDetailImages;
    private ProductImage firstImage;
    private int reviewCount;
    private int saleCount;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", originalPrice=" + originalPrice +
                ", promotePrice=" + promotePrice +
                ", stock=" + stock +
                ", createDate=" + createDate +
                ", cid=" + cid +
                '}';
    }
}
