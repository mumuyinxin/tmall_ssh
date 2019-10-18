<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${empty param.categorycount}">
	<c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
	<c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>

<div class="homepageCategoryProducts">
	<c:forEach items="${categoryList}" var="c" varStatus="stc">
		<%--最多展示100个类别的4项展示商品--%>
		<c:if test="${stc.count<=100}">
			<div class="eachHomepageCategoryProducts">
				<div class="left-mark"></div>
				<span class="categoryTitle">${c.name}</span>
				<br>
				<c:forEach items="${c.products}" var="p" varStatus="st">
					<c:if test="${st.count<=5}">
						<div class="productItem" >
							<a href="frontProduct_Front.action?product.id=${p.id}">
								<img width="100px" src="/img/productSingle_middle/${p.firstImage.id}.jpg">
							</a>
							<a class="productItemDescLink" href="frontProduct_Front.action?product.id=${p.id}">
								<span class="productItemDesc">[热销]
								${fn:substring(p.name, 0, 20)}
								</span>
						    </a>
							<span class="productPrice">
								<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
							</span>
						</div>
					</c:if>				
				</c:forEach>
				<div style="clear:both"></div>
			</div>
		</c:if>
	</c:forEach>

	<img id="endpng" class="endpng" src="img/site/end.png">
</div>