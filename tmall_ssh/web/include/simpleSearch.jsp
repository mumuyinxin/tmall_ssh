<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<div class="simpleSearchOutDiv">
	<a href="frontLogin_Front.action">
		<img id="simpleLogo" class="simpleLogo" src="img/site/simpleLogo.png">	
	</a>
	
	<form action="frontSearch_Front.action" method="post" >
	<div class="simpleSearchDiv pull-right">
		<input type="text" placeholder="平衡车 原汁机"  value="${param.keyword}" name="keyword">
		<button class="searchButton" type="submit">搜天猫</button>
		<div class="searchBelow">
			<c:forEach items="${categoryList}" var="c" varStatus="st">
				<c:if test="${st.count>=8 and st.count<=11}">
					<span>
						<a href="frontCategory_Front.action?category.id=${c.id}">
							${c.name}
						</a>
						<c:if test="${st.count!=11}">				
							<span>|</span>				
						</c:if>
					</span>			
				</c:if>
			</c:forEach>			
		</div>
	</div>
	</form>
	<div style="clear:both"></div>
</div>