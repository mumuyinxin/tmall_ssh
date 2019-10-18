package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class Category {
    private int id;
    private String name;
    private Set<Product> products = new HashSet<>();
    private Set<Property> propertys = new HashSet<>();

    private List<Product> productList;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
