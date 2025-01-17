<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
	$(function(){
		$(".addFormSingle").submit(function(){
			if(checkEmpty("filepathSingle","图片文件")){
				$("#filepathSingle").value("");
				return true;
			}
			return false;
		});
		$(".addFormDetail").submit(function(){
			if(checkEmpty("filepathDetail","图片文件"))
				return true;
			return false;
		});
	});

</script>

<title>产品图片管理</title>

<div class="workingArea">
	<ol class="breadcrumb">
	  <li><a href="listCategory_Category.action">所有分类</a></li>
	  <li><a href="listProduct_Product.action?product.cid=${product.category.id}">${product.name}</a></li>
	  <li class="active">${product.name}</li>
	  <li class="active">产品图片管理</li>
	</ol>

	<table class="addPictureTable" align="center">
		<tr>
			<td class="addPictureTableTD">
			  <div>
				<div class="panel panel-warning addPictureDiv">
					<div class="panel-heading">新增产品<b class="text-primary"> 单个 </b>图片</div>
					  <div class="panel-body">
					    	<form method="post" class="addFormSingle" action="addProductImage_Product.action" enctype="multipart/form-data">
					    		<table class="addTable">
					    			<tr>
					    				<td>请选择本地图片 尺寸400X400 为佳</td>
					    			</tr>
					    			<tr>
					    				<td>
					    					<input id="filepathSingle" type="file" name="img" />
					    				</td>
					    			</tr>
					    			<tr class="submitTR">
					    				<td align="center">
					    					<input type="hidden" name="productImage.type" value="type_single" />
					    					<input type="hidden" name="productImage.product.id" value="${product.id}" />
					    					<button type="submit" class="btn btn-success">提 交</button>
					    				</td>
					    			</tr>
					    		</table>
					    	</form>
					  </div>
				  </div>			  
			  	<table class="table table-striped table-bordered table-hover  table-condensed"> 
					<thead>
						<tr class="success">
							<th>ID</th>
							<th>产品单个图片缩略图</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${productSingleImages}" var="pi">
							<tr>
								<td>${pi.id}</td>
								<td>
									<a title="点击查看原图" href="img/productSingle/${pi.id}.jpg">
										<img height="50px" src="img/productSingle/${pi.id}.jpg">
									</a>
								</td>
								<td>
									<a deleteLink="true" href="deleteProductImage_Product.action?productImage.id=${pi.id}">
										<span class="glyphicon glyphicon-trash"></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>	  
				</table>
			  </div>			
			</td>

			<td class="addPictureTableTD">
			  <div>
				<div class="panel panel-warning addPictureDiv">
					<div class="panel-heading">新增产品<b class="text-primary"> 详情 </b>图片</div>
					  <div class="panel-body">
					    	<form method="post" class="addFormDetail" action="addProductImage_Product.action" enctype="multipart/form-data">
					    		<table class="addTable">
					    			<tr>
					    				<td>请选择本地图片 宽度790  为佳</td>
					    			</tr>
					    			<tr>
					    				<td>
					    					<input id="filepathDetail"  type="file" name="img" />
					    				</td>
					    			</tr>
					    			<tr class="submitTR">
					    				<td align="center">
											<input type="hidden" name="productImage.type" value="type_detail" />
											<input type="hidden" name="productImage.product.id" value="${product.id}" />
					    					<button type="submit" class="btn btn-success">提 交</button>
					    				</td>
					    			</tr>
					    		</table>
					    	</form>
					  </div>
				  </div>
				  <table class="table table-striped table-bordered table-hover  table-condensed"> 
						<thead>
							<tr class="success">
								<th>ID</th>
								<th>产品详情图片缩略图</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productDetailImages}" var="pi">
								<tr>
									<td>${pi.id}</td>
									<td>
										<a title="点击查看原图" href="img/productDetail/${pi.id}.jpg">
											<img height="50px" src="img/productDetail/${pi.id}.jpg">
										</a>
									</td>
									<td>
										<a deleteLink="true" href="deleteProductImage_Product.action?productImage.id=${pi.id}">
											<span class="glyphicon glyphicon-trash"></span>
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>	  
					</table>					  		
			  </div>			
			</td>
		</tr>
	</table>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>