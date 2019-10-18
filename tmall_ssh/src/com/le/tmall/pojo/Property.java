package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Property {
    private int id;
    private int cid;
    private String name;

    private Category category;
    private Set<PropertyValue> propertyValues = new HashSet<>();

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
