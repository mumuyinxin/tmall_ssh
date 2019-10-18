<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<title>模仿天猫官网 ${product.name}</title>
<div class="categoryPictureInProductPageDiv">
	<img class="categoryPictureInProductPage" src="img/category/${product.category.id}.jpg">
</div>

<div class="productPageDiv">
	<%--商品图片信息--%>
	<%@include file="imgAndInfo.jsp" %>
	<%--商品详情--%>
	<%@include file="productDetail.jsp" %>
	<%--商品评论--%>
	<%@include file="productReview.jsp" %>
</div>