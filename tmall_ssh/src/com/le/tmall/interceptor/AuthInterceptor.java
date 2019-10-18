package com.le.tmall.interceptor;

import com.le.tmall.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AuthInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String[] noNeedAuthPage = new String[]{
                "home", "checkLogin", "register", "loginAjax", "login", "product", "category", "search"
        };
        //对比Session session = (Session) ActionContext.getContext().getSession();
        ActionContext actionContext = actionInvocation.getInvocationContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletContext servletContext = ServletActionContext.getServletContext();
        String uri = request.getRequestURI();
        if (uri.startsWith("/fore")) {
            String method = StringUtils.substringAfterLast(uri, "/fore");
            if (!Arrays.asList(noNeedAuthPage).contains(method)) {
                User user = (User) actionContext.getSession().get("user");
                if (null == user) {
                    response.sendRedirect("login.jsp");
                    // return null;
                }
            }
        }
        return actionInvocation.invoke();
    }
}
