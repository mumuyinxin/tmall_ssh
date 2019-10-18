package com.le.tmall.action;

import com.le.tmall.pojo.Category;
import com.le.tmall.pojo.Property;
import com.le.tmall.util.Page;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class PropertyAction extends ParameterAndPojo<Property> {
    public String listProperty() {
        // 获取分页参数
        int count = 5;
        if (page == null)
            page = new Page();
        page.setCount(count);
        page.setTotal(propertyService.getTotalByCid(property.getCid()));
        propertyService.listPageByCid(page, property.getCid());
        if (page.getList().size() > 0) {
            page.setParam("property.cid=" + page.getList().get(0).getCategory().getId());
        }
        Category category = categoryService.get(property.getCid());
        request.put("category", category);
        request.put("propertyList", page.getList());
        request.put("page", page);
        return "listProperty";
    }

    //解决hibernate删除时的异常 deleted object would be re-saved by cascade (remove deleted object from associa
    //需解开藕断丝连的问题
    public String delete() {
        int cid = property.getCid();
        property = propertyService.get(property.getId());
        property.getCategory().getPropertys().remove(property);
        property.setCategory(null);
        propertyService.delete(property);
        property.setCid(cid);
        return listProperty();
    }

    //类别：修改页面回显信息
    public String editPage() {
        property = propertyService.get(property.getId());
        request.put("property", property);
        return "editProperty";
    }

    //类别：修改属性
    public String edit() {
        Property oldProperty = propertyService.get(property.getId());
        oldProperty.setName(property.getName());
        propertyService.update(oldProperty);
        return listProperty();
    }

    public String add() {
        property.setCategory(categoryService.get(property.getCid()));
        propertyService.add(property);
        return listProperty();
    }
}
