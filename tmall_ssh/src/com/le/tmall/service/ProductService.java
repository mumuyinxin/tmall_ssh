package com.le.tmall.service;

import com.le.tmall.pojo.Product;
import com.le.tmall.util.Page;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    List<Product> findAll();

    Product get(int id);

    int getTotalByCid(int cid);

    void listPageByCid(Page page, int cid);

    void SetProductFirstImageAndReviewCountAndSaleCount(Product product);
}
