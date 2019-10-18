package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PropertyValue {
    private int id;
    private int pid;
    private int ptid;
    private String value;

    private Product product;
    private Property property;

    @Override
    public String toString() {
        return "PropertyValue{" +
                "id=" + id +
                ", pid=" + pid +
                ", ptid=" + ptid +
                ", value='" + value + '\'' +
                '}';
    }
}
