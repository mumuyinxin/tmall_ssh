<%@ page language="java" contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="categoryProducts">
	<c:forEach items="${category.productList}" var="p" varStatus="stc">
		<div class="productUnit" price="${p.promotePrice}">
			<div class="productUnitFrame">
				<a href="frontProduct_Front.action?product.id=${p.id}">
					<img class="productImage" src="img/productSingle_middle/${p.firstImage.id}.jpg">
				</a>
				<span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
				<a class="productLink" href="frontProduct_Front.action?product.id=${p.id}">
				 ${fn:substring(p.name, 0, 50)}
				</a>
				
				<a  class="tmallLink" href="frontProduct_Front.action?product.id=${p.id}">天猫专卖</a>
	
				<div class="show1 productInfo">
					<%--实际为总成交量--%>
					<span class="monthDeal ">总成交量 <span class="productDealNumber">${p.saleCount}笔</span></span>
					<span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
					<span class="wangwang">
					<a class="wangwanglink" href="#nowhere">
						<img src="img/site/wangwang.png">
					</a>
					
					</span>
				</div>
			</div>
		</div>
	</c:forEach>
	<div style="clear:both"></div>
</div>