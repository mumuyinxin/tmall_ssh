package com.le.tmall.action;

import com.le.tmall.pojo.Category;
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
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class CategoryAction extends ParameterAndPojo<Category> {
    public String listCategory() {
        // 获取分页参数
        int count = 5;
        if (page == null)
            page = new Page();
        page.setCount(count);
        page.setTotal(categoryService.getTotal());
        categoryService.listByPage(page);
        request.put("categoryList", page.getList());
        request.put("page", page);
        return "listCategory";
    }

    public String delete() {
        category = categoryService.get(category.getId());
        categoryService.delete(category);
        return listCategory();
    }

    //修改页面回显信息
    public String editPage() {
        category = categoryService.get(category.getId());
        request.put("category", category);
        return "editCategory";
    }

    public String edit() {
        Category oldCategory = categoryService.get(category.getId());
        oldCategory.setName(category.getName());
        categoryService.update(oldCategory);
        if (img != null) {
            File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
            if (!imageFolder.exists()) {
                imageFolder.mkdirs();
            }
            File file = new File(imageFolder, oldCategory.getId() + ".jpg");//在imageFolder路径下，创建child子文件
            try {
                FileUtils.copyFile(img, file);
                BufferedImage img = ImageUtil.change2jpg(file);
                ImageIO.write(img, "jpg", file);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return listCategory();
    }

    public String add() {
        categoryService.add(category);
        List<Category> categoryList = categoryService.findByKeyword("name", category.getName());
        category = categoryList.get(0);
        File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
        }
        File file = new File(imageFolder, category.getId() + ".jpg");
        try {
            FileUtils.copyFile(img, file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCategory();
    }
}
