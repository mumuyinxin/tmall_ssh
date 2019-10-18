<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--<base href="<%=basePath%>">--%>
<div class="navitagorDiv">
    <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
        <img style="margin-left:10px;margin-right:0px" class="pull-left" src="../img/site/tmallbuy.png" height="45px">
        <a class="navbar-brand" href="listUser_User.action">天猫后台</a>
        <a class="navbar-brand" href="listCategory_Category.action">分类管理</a>
        <a class="navbar-brand" href="listUser_User.action">用户管理</a>
        <a class="navbar-brand" href="listOrder_Order.action">订单管理</a>
        <a class="navbar-brand" href="logout_User.action">退出</a>
    </nav>
</div>