<%@ page language="java" contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<title>编辑属性</title>

<div class="workingArea">
	<ol class="breadcrumb">
	  <li><a href="listCategory_Category.action">所有分类</a></li>
	  <li class="active">${property.category.name}</li>
	  <li class="active">编辑属性</li>
	</ol>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑属性</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="edit_Property.action">
				<table class="editTable">
					<tr>
						<td>属性名称</td>
						<td>
							<input id="name" name="property.name" value="${property.name}" type="text" class="form-control">
						</td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
						<input type="hidden" name="property.id" value="${property.id}">
						<input type="hidden" name="property.cid" value="${property.category.id}">
						<button type="submit" class="btn btn-success">提 交</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>