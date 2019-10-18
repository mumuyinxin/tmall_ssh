<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<div class="productDetailDiv">
	<div class="productDetailTopPart">
		<a onclick="return false" class="productDetailTopPartSelectedLink selected">
			商品详情
		</a>
		<a onclick="return false" class="productDetailTopReviewLink">
			累计评价
			<span class="productDetailTopReviewLinkNumber">${product.reviewCount}</span>
		</a>
	</div>
	
	
	<div class="productParamterPart">
		<div class="productParamter">产品参数：</div>
		
		<div class="productParamterList">
			<c:forEach items="${product.propertyValues}" var="pv">
				<span>${pv.property.name}:  ${fn:substring(pv.value, 0, 10)} </span>
			</c:forEach>
		</div>
		<div style="clear:both"></div>
	</div>
	
	<div class="productDetailImagesPart">
		<c:forEach items="${product.productDetailImages}" var="pi">
			<img src="img/productDetail/${pi.id}.jpg">
		</c:forEach>
	</div>
</div>

