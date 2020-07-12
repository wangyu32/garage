<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<jsp:include flush="true" page="../../comm/iframeInc.jsp">
		<jsp:param value="yes" name="validate"/> 
		<jsp:param value="yes" name="layer"/>
		<jsp:param value="yes" name="bootstrap"/>
		<jsp:param value="yes" name="treeview"/>
	</jsp:include>
	<link rel="stylesheet" href="<c:url value='/css/system/sysrole/edit.css' />"/>
	<script src='<c:url value="/js/system/sysrole/edit.js" />'></script>
</head>

<body class="data-wrapper p20edit">
	<!-- 标题 -->
	<div class="editTitle">
		<h5 class="panel-title title"></h5>
		<div style="width:113px;"></div>
	</div>
	<!-- 表单 -->
	<form class="form-horizontal editPage" id="form">
		<input type="hidden" name="id" id ="id" class="form-control" value="${model.id}">
		<div class="form-group clearfix">
		    <label class="col-sm-2 control-label">角色名称</label>
		    <div class="col-sm-6">
		      	<input type="text" name="name" class="form-control"  maxlength="30"  value="${model.name}">
		    </div>
		    <span class="required">*</span>
		</div>
		<div class="form-group clearfix">
		    <label class="col-sm-2 control-label">描述 </label>
		    <div class="col-sm-6">
		    	<textarea rows="2" cols="" type="text" name="desc" class="form-control" maxlength="100"  value="${model.desc}">${model.desc}</textarea>
		    </div>
		</div>
		<!-- 分配菜单 -->
	   	<div class="form-group clearfix">
	   		<label class="col-sm-2 control-label clearfix">菜单</label>
	   		<div class="col-sm-6">
	   			<div class="roleHandelButton">
					<span onclick="dosome(1)">选择全部</span>
					<span onclick="dosome(2)">全不选择</span>
					<span onclick="dosome(4)">展开全部</span>
					<span onclick="dosome(3)">折叠全部</span>
				</div>
		    </div>
	   	</div>
	   	<div class="form-group clearfix">
	   		<label class="col-sm-2 control-label clearfix"></label>
	   		<div class="col-sm-6" id="menuDiv">
				<div style="border: 1px solid #ccc;">
					<div id="treeview"></div>
					<div style="display: none;" id="treeviewVal"></div>
				</div>
		    </div>
	   	</div>
		<div class="form-group clearfix">
		    <label class="col-sm-2 control-label clearfix">状态</label>
			<div class=" col-sm-6">
                <label class="radio-inline">
					<input type="radio" name="status" value='0' <c:if test="${(model.status eq 0) or (model.status eq null)}">checked</c:if> /> 启用
				</label>
				<label class="radio-inline">
					<input type="radio" name="status" value='1' <c:if test="${(model.status eq 1)}">checked</c:if> /> 禁用
				</label>
		    </div>
		</div>
		<div class="form-group clearfix">
		    <label class="col-sm-2 control-label clearfix"></label>
			<div class=" col-sm-6">
				<button class="save" type="submit">保存</button>
				<button class="back" id="back" type="button">取消</button>
		    </div>
		</div>
	</form>
</body>

</html>