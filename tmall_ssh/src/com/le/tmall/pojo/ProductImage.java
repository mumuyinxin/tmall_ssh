package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImage {
    public static final String type_single = "type_single";
    public static final String type_detail = "type_detail";

    private int id;
    private int pid;
    private String type;

    private Product product;

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", pid=" + pid +
                ", type='" + type + '\'' +
                ", product=" + product +
                '}';
    }
}
