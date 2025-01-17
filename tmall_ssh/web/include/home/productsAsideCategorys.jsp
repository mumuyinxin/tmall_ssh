<%@ page language="java" contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script>
$(function(){
	$("div.productsAsideCategorys div.row a").each(function(){
		var v = Math.round(Math.random() *6);
		if(v == 1)
			$(this).css("color","#87CEFA");
	});
});

</script>
<c:forEach items="${categoryList}" var="c" >
	<div cid="${c.id}" class="productsAsideCategorys">
		<%--<c:forEach items="${c.productsByRow}" var="ps">--%>
			<div class="row ">
				<c:forEach items="${c.products}" var="p" varStatus="status">
                    <c:if test="${status.count<=30}">
                        <c:if test="${!empty p.subTitle}">
                            <a href="frontProduct_Front.action?product.id=${p.id}">
                                <c:forEach items="${fn:split(p.subTitle, ' ')}" var="title" varStatus="st">
                                    <c:if test="${st.index==0}">
                                        ${title}
                                    </c:if>
                                </c:forEach>
                            </a>
                        </c:if>
                    </c:if>
				</c:forEach>
				<div class="seperator"></div>
			</div>		
		<%--</c:forEach>--%>
	</div>			
</c:forEach>
	
