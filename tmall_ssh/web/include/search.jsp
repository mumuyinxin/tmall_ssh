<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<div class="searchOutDiv">
	<a href="frontLogin_Front.action">
		<img id="logo" src="/img/site/logo.gif" class="logo">
	</a>
	<form action="frontSearch_Front.action" method="post" >
		<div class="searchDiv">
			<%--<input name="keyword" type="text" value="${param.keyword}" placeholder="时尚男鞋  太阳镜 ">--%>
			<input name="keyword" type="text" value="" placeholder="时尚男鞋  太阳镜 ">
			<button  type="submit" class="searchButton">搜索</button>
			<div class="searchBelow">
				<c:forEach items="${categoryList}" var="c" varStatus="st">
					<c:if test="${st.count>=5 and st.count<=8}">
						<span>
							<a href="frontCategory_Front.action?category.id=${c.id}">
								${c.name}
							</a>
							<c:if test="${st.count!=8}">				
								<span>|</span>				
							</c:if>
						</span>			
					</c:if>
				</c:forEach>		
			</div>
		</div>
	</form>	
</div>