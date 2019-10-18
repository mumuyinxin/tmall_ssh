package com.le.tmall.action;

import com.le.tmall.pojo.User;
import com.le.tmall.util.Page;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class UserAction extends ParameterAndPojo<User> {
    //管理员专属账号admin跳转后台，其余皆是顾客跳转前端
    //如果是ajax请求，则返回success或fail
    public String login() {
        if (userService.isExist("name", user.getName(), "password", user.getPassword())) {
            User resultUser = userService.findByKeyword("name", user.getName()).get(0);
            actionContext.getSession().put("log_on_user", resultUser);
            if (ajax)
                return "success";
            if (user.getName().equals("admin"))
                return "backgroundLoginSuccess";
            else
                return "loginSuccess";
        }
        else {
            if (ajax)
                return "fail";
            request.put("msg", "账号或密码错误");
            return "loginERROR";
        }
    }

    public String register() {
        if (userService.isExist("name", user.getName()) != true)
        {
            userService.add(user);
            return "registerSuccess";
        }
        else
        {
            request.put("msg", "会员名重复，请更换会员名！！！");
            return "registerERROR";
        }
    }

    public String listUser() {
        int count = 3;
        if (page == null)
            page = new Page();
        page.setCount(count);
        page.setTotal(userService.getTotal());
        userService.listByPage(page);
        request.put("userList", page.getList());
        request.put("page", page);
        return "listUser";
    }

    public String reset() {
        user = userService.get(user.getId());
        user.setPassword("123456");
        userService.add(user);
        return "reset";
    }

    public String logout() {
        actionContext.getSession().remove("log_on_user");
        return "logout";
    }
}
