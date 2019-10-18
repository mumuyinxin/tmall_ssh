<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
	$(function() {
		$("#addForm").submit(function() {
			if (checkEmpty("name", "属性名称"))
				return true;
			return false;
		});
	});
</script>

<title>属性管理</title>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="listCategory_Category.action">所有分类</a></li>
	  <li class="active">${category.name}</li>
	  <li class="active">属性管理</li>
	</ol>

	<div class="listDataTableDiv">
		<table
			class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>属性名称</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${propertyList}" var="p">
					<tr>
						<td>${p.id}</td>
						<td>${p.name}</td>
						<td>
							<a href="editPage_Property.action?property.id=${p.id}">
								<span class="glyphicon glyphicon-edit"></span>
							</a>
						</td>
						<td>
							<%--<a deleteLink="true" href="delete_Property.action?property.id=${p.id}&&property.cid=${category.id}">--%>
								<%--<span class="glyphicon glyphicon-trash"></span>--%>
							<%--</a>--%>
							<a href="delete_Property.action?property.id=${p.id}&&property.cid=${category.id}">
								<span class="glyphicon glyphicon-trash"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp"%>
	</div>

	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增属性</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="add_Property.action">
				<table class="addTable">
					<tr>
						<td>属性名称</td>
						<td><input id="name" name="property.name" type="text" class="form-control"></td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
							<input type="hidden" name="property.cid" value="${category.id}">
							<button type="submit" class="btn btn-success">提 交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</div>

<%@include file="../include/admin/adminFooter.jsp"%>
