<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
	<title>产品管理</title>
	<script>
        $(function() {
            $("#addForm").submit(function() {
                if (!checkEmpty("name", "产品名称"))
                    return false;
                if (!checkEmpty("subTitle", "小标题"))
                    return false;
                if (!checkNumber("orignalPrice", "原价格"))
                    return false;
                if (!checkNumber("promotePrice", "优惠价格"))
                    return false;
                if (!checkInt("stock", "库存"))
                    return false;
                return true;
            });
        });
	</script>
</head>
<body>
<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="listCategory_Category.action">所有分类</a></li>
		<li><a href="listProduct_Product.action?product.cid=${category.id}">${category.name}</a></li>
		<li class="active">产品管理</li>
	</ol>


	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover table-condensed">
			<thead>
			<tr class="success">
				<th>ID</th>
				<th>图片</th>
				<th>产品名称</th>
				<th>产品小标题</th>
				<th>原价格</th>
				<th>优惠价格</th>
				<th>库存数量</th>
				<th>图片管理</th>
				<th>设置属性</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${productList}" var="p">
				<tr>
					<td>${p.id}</td>
					<td>
						<img width="40px" src="/img/productSingle/${p.firstImage.id}.jpg">
					</td>
					<td>${p.name}</td>
					<td>${p.subTitle}</td>
					<td>${p.originalPrice}</td>
					<td>${p.promotePrice}</td>
					<td>${p.stock}</td>
					<td>
						<a href="listProductImage_Product.action?product.id=${p.id}">
							<span class="glyphicon glyphicon-picture"></span>
						</a>
					</td>
					<td>
						<a href="editProductValuePage_Product.action?product.id=${p.id}">
							<span class="glyphicon glyphicon-th-list"></span>
						</a>
					</td>
					<td>
						<a href="editProductPage_Product.action?product.id=${p.id}">
							<span class="glyphicon glyphicon-edit"></span>
						</a>
					</td>
					<td>
						<a href="delete_Product.action?product.id=${p.id}">
							<span class="glyphicon glyphicon-trash"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>

	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增产品</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="add_Product.action">
				<table class="addTable">
					<tr>
						<td>产品名称</td>
						<td><input id="name" name="product.name" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>产品小标题</td>
						<td><input id="subTitle" name="product.subTitle" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>原价格</td>
						<td><input id="orignalPrice" value="" name="product.originalPrice" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>优惠价格</td>
						<td><input id="promotePrice"  value="" name="product.promotePrice" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>库存</td>
						<td><input id="stock"  value="" name="product.stock" type="text" class="form-control"></td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
							<input type="hidden" name="product.cid" value="${category.id}">
							<button type="submit" class="btn btn-success">提 交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>
</body>
</html>
