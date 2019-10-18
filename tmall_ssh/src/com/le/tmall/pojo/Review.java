package com.le.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Review {
    private int id;
    private String content;
    private int uid;
    private int pid;
    private Date createDate;

    private User user;
    private Product product;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", pid=" + pid +
                ", createDate=" + createDate +
                '}';
    }
}
