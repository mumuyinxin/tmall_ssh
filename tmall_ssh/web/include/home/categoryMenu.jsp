<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
	
<div class="categoryMenu">
	<c:forEach items="${categoryList}" var="c">
		<div cid="${c.id}" class="eachCategory">
			<span class="glyphicon glyphicon-link"></span>
			<a href="frontCategory_Front.action?category.id=${c.id}">
				${c.name}
			</a>
		</div>
	</c:forEach>
</div>