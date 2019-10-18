<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<nav class="top ">
	<div class="top_middle">
	
		<a href="frontLogin_Front.action">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
			天猫首页
		</a>	
		
		<span>喵，欢迎来天猫</span>
		
		<c:if test="${!empty log_on_user}">
			<a href="#">${log_on_user.name}</a>
			<a href="logout_User.action">退出</a>
		</c:if>
		
		<c:if test="${empty log_on_user}">
			<a href="login.jsp">请登录</a>
			<a href="register.jsp">免费注册</a>		
		</c:if>


		<span class="pull-right">
			<a href="frontBought_Front.action">我的订单</a>
			<a href="frontCart_Front.action">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
			购物车
				<c:if test="${!empty cartTotalItemNumber}">
					<strong>${cartTotalItemNumber}</strong>
				</c:if>
				<c:if test="${empty cartTotalItemNumber}">
					<strong>0</strong>
				</c:if>
				件</a>
		</span>
	</div>
</nav>



