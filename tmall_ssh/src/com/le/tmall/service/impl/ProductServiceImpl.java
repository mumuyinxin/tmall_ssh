package com.le.tmall.service.impl;

import com.le.tmall.dao.BaseDao;
import com.le.tmall.pojo.Category;
import com.le.tmall.pojo.OrderItem;
import com.le.tmall.pojo.Product;
import com.le.tmall.pojo.ProductImage;
import com.le.tmall.service.ProductService;
import com.le.tmall.util.Page;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Setter
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
    private BaseDao<Product> baseDao;

    public void setBaseDao(BaseDao<Product> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = super.findAll();
        for (Product product : productList) {
            int id = product.getId();
            List<ProductImage> list = productImageService.findByKeyword("product.id", id);
            if (list != null)
                product.setFirstImage(list.get(0));
        }
        if (productList.size() > 0)
            return productList;
        else
            return null;
    }

    @Override
    public Product get(int id) {
        Product product = super.get(id);
        List<ProductImage> list = productImageService.findByKeyword("product.id", id);
        if (list != null)
            product.setFirstImage(list.get(0));
        List<ProductImage> productSingleImages = productImageService.findByKeyword("type", "type_single", "product.id", product.getId());
        List<ProductImage> productDetailImages = productImageService.findByKeyword("type", "type_detail", "product.id", product.getId());
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);
        if (product != null)
            return product;
        else
            return null;
    }

    @Override
    public int getTotalByCid(int cid) {
        Category category = categoryService.get(cid);
        if (category != null)
            return category.getProducts().size();
        else
            return 0;
    }

    @Override
    public void listPageByCid(Page page, int cid) {
        DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
        dc.add(Restrictions.eq("category.id", cid));
        dc.addOrder(Order.desc("id"));
        List<Product> productList = this.findByCriteria(dc, page);
        for (Product product : productList) {
            List<ProductImage> productImageList = productImageService.findByKeyword("product.id", product.getId());
            if (productImageList != null)
                product.setFirstImage(productImageList.get(0));
        }
        page.setList(productList);
    }

    //设置商品首图 评论数量 销售数量
    @Override
    public void SetProductFirstImageAndReviewCountAndSaleCount(Product product) {
        int saleCount = 0;
        Iterator<OrderItem> orderItemIterator = product.getOrderItems().iterator();
        while (orderItemIterator.hasNext()) {
            OrderItem orderItem = orderItemIterator.next();
            System.out.println(orderItem);
            System.out.println(orderItem.getOrder());
            if (orderItem.getOrder() != null
                    && orderItem.getOrder().getStatus() != null
                    && orderItem.getOrder().getStatus().equals("待付款")) {
                saleCount += orderItem.getNumber();
            }

        }
        product.setFirstImage(productImageService.findByKeyword("product.id", product.getId()).get(0));
        product.setSaleCount(saleCount);
        product.setReviewCount(product.getReviews().size());
    }
}