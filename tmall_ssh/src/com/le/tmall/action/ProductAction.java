package com.le.tmall.action;

import com.le.tmall.pojo.*;
import com.le.tmall.util.ImageUtil;
import com.le.tmall.util.Page;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ProductAction extends ParameterAndPojo<Product> {
    public String listProduct() {
        // 获取分页参数
        int count = 3;
        if (page == null)
            page = new Page();
        page.setCount(count);
        page.setTotal(productService.getTotalByCid(product.getCid()));
        productService.listPageByCid(page, product.getCid());
        if (page.getList().size() > 0)
            page.setParam("product.cid=" + page.getList().get(0).getCategory().getId());
        Category category = categoryService.get(product.getCid());
        request.put("category", category);
        request.put("productList", page.getList());
        request.put("page", page);
        return "listProduct";
    }

    public String delete() {
        Product oldProduct = productService.get(product.getId());
        product.setCid(oldProduct.getCategory().getId());
        productService.delete(oldProduct);
        return listProduct();
    }

    //修改页面回显信息
    public String editProductPage() {
        product = productService.get(product.getId());
        List<Category> categoryList = categoryService.findAll();
        request.put("categoryList", categoryList);
        request.put("product", product);
        return "editProduct";
    }

    //修改商品
    public String editProduct() {
        Product oldProduct = productService.get(product.getId());
        int cid = oldProduct.getCategory().getId();
        oldProduct.setName(product.getName());
        oldProduct.setSubTitle(product.getSubTitle());
        oldProduct.setOriginalPrice(product.getOriginalPrice());
        oldProduct.setPromotePrice(product.getPromotePrice());
        oldProduct.setStock(product.getStock());
        oldProduct.setCategory(categoryService.get(product.getCid()));
        productService.update(oldProduct);
        product.setCid(cid);
        return listProduct();
    }

    //回显商品属性值页面
    public String editProductValuePage() {
        product = productService.get(product.getId());
        request.put("product", product);
        return "editPropertyValue";
    }

    //修改商品属性值
    public String editProductValue() {
        PropertyValue oldPropertyValue = propertyValueService.get(propertyValue.getId());
        oldPropertyValue.setValue(propertyValue.getValue());
        propertyValueService.update(oldPropertyValue);
        return "editProductValueSuccess";
    }

    //添加商品时，商品属性值设置为默认值
    public String add() {
        product.setCreateDate(new Date());
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
        product.setCid(category.getId());
        productService.add(product);

        List<Product> productList = productService.findByKeyword("name", product.getName());
        List<Property> propertyList = propertyService.findByKeyword("category.id", category.getId());
        Product oldProduct = productList.get(productList.size() - 1);
        if (propertyList == null)
            return listProduct();
        for (int i = 0; i < propertyList.size(); i++) {
            PropertyValue propertyValue = new PropertyValue();
            propertyValue.setProperty(propertyList.get(i));
            propertyValue.setProduct(oldProduct);
            if(propertyList.get(i).getPropertyValues().size() != 0)
            {
                Iterator<PropertyValue> propertyValueIterator = propertyList.get(i).getPropertyValues().iterator();
                propertyValue.setValue(propertyValueIterator.next().getValue());
            }
            propertyValueService.add(propertyValue);
        }
        return listProduct();
    }

    public String listProductImage() {
        product = productService.get(product.getId());
        List<ProductImage> productSingleImages = productImageService.findByKeyword("type", "type_single", "product.id", product.getId());
        List<ProductImage> productDetailImages = productImageService.findByKeyword("type", "type_detail", "product.id", product.getId());
        request.put("product", product);
        request.put("productSingleImages", productSingleImages);
        request.put("productDetailImages", productDetailImages);
        return "listProductImage";
    }

    public String addProductImage() {
        product = productService.get(productImage.getProduct().getId());
        productImage.setProduct(product);
        productImageService.add(productImage);
        List<ProductImage> productImageList = productImageService.findByKeyword("product.id", productImage.getProduct().getId());
        productImage = productImageList.get(productImageList.size() - 1);
        String folder = "img/";
        if (productImage.getType().equals("type_single")) {
            folder += "productSingle";
        } else {
            folder += "productDetail";
        }
        File imageFolder = new File(ServletActionContext.getServletContext().getRealPath(folder));
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
        }
        File file = new File(imageFolder, productImage.getId() + ".jpg");
        String fileName = file.getName();
        try {
            FileUtils.copyFile(img, file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (productImage.getType().equals("type_single")) {
            String imageFolder_small = ServletActionContext.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle = ServletActionContext.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }
        return listProductImage();
    }

    public String deleteProductImage() {
        productImage = productImageService.get(productImage.getId());
        product = new Product();
        product.setId(productImage.getProduct().getId());
        productImageService.delete(productImage);
        return listProductImage();
    }
}
