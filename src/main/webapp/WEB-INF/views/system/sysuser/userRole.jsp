<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<jsp:include flush="true" page="../../comm/iframeInc.jsp">
		<jsp:param value="yes" name="table"/>
		<jsp:param value="yes" name="layer"/>
		<jsp:param value="yes" name="bootstrap"/>
	</jsp:include>
	<link rel="stylesheet" href="<c:url value='/css/comm/global.css' />"/>
	<script src='<c:url value="/js/system/sysuser/userRole.js" />'></script>
</head>

<body>
	<!--菜单栏-->
	<nav class="navbar" id="navbar">
        <button class="navbar-btn btn btn-primary btn-xs" id="addBtn" type="button">
        	<span class="glyphicon glyphicon-plus"></span> 新增
        </button>
 		<button class="navbar-btn btn btn-danger btn-xs" id="delBtn" type="button">
        	<span class="glyphicon glyphicon-trash"></span> 删除
        </button>
	</nav>
	
	<input type="hidden" id="u_id" value="${u_id}">
	
	<!--数据列表-->
	<table id="dataTable" 
			  data-tb="yes" 
			  data-toggle="table" 
			  data-ajax="ajaxRequest"  
			  data-side-pagination="server" 
			  data-pagination="true" 
			  data-sort-name="r_updatetime" 
			  data-sort-order="desc" 
			  class="table-bordered table-condensed table-striped">
		<thead>
			<tr>
				<th data-checkbox='true' data-width="20" ></th>
				<th data-field="r_id" data-width="40"  data-sortable="true"  data-halign="center" data-align="center">角色ID</th>
				<th data-field="r_name" data-width="40"  data-sortable="true" data-halign="center" data-align="center" data-formatter='formatName'>角色名称</th>
				<th data-field="r_desc" data-width="60" data-halign="center" data-align="center">描述</th>
				<th data-field="r_status" data-width="100"  data-halign="center" data-align="center" data-formatter="dataFormatterForCommonStatus">状态</th>
			</tr>
		</thead>
	</table>
	
	<!-- 角色列表 -->
	<div id="roleDiv" style="display: none;">
		<div class="search-input navml15" id="search-input">
				<div class="list-search" id="list-search">
					<span class="btn-search">
						<button type="button" class="button btn-primary btn-sm search-btn" id="saveRole">
							<span class="glyphicon glyphicon-search"></span> 确认
						</button>
					</span>
					<span>
						<b>角色ID</b>
						<input type="text" id="r_id" size="8" maxlength="8"/>
					</span>
					<span>
						<b>角色名称</b>
						<input type="text" id="r_name" size="12"/>
					</span>
					<span>
						<b>状态</b>
						<select name="r_status" id="r_status">
							<option value="">-- 所有 --</option>
							<option value="0">启用</option>
							<option value="1">禁用</option>
						</select>
					</span>
					<span class="btn-search">
						<button type="button" class="button btn-primary btn-sm search-btn" id="searchRole">
							<span class="glyphicon glyphicon-search"></span> 搜索
						</button>
					</span>
				</div>
		</div>
		<!--数据列表-->
		<table id="roleTable" 
				  data-tb="yes" 
				  data-toggle="table" 
				  data-ajax="ajaxRequestRole"  
				  data-side-pagination="server" 
				  data-pagination="true" 
				  data-sort-name="r_updatetime" 
				  data-sort-order="desc" 
				  class="table-bordered table-condensed table-striped">
			<thead>
				<tr>
					<th data-checkbox='true' data-width="20" ></th> 
					<th data-field="r_id" data-width="40"  data-sortable="true"  data-halign="center" data-align="center">角色ID</th>
					<th data-field="r_name" data-width="40"  data-sortable="true" data-halign="center" data-align="center" data-formatter='formatName'>角色名称</th>
					<th data-field="r_desc" data-width="60" data-halign="center" data-align="center">描述</th>
					<th data-field="r_status" data-width="100"  data-halign="center" data-align="center" data-formatter="dataFormatterForCommonStatus">状态</th>
				</tr>
			</thead>
		</table>
	</div>
	
</body>