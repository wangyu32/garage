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
	<%-- 
	<link rel="stylesheet" href="<c:url value='/css/account/redpacketsend/list.css' />"/> --%>
	<link rel="stylesheet" href="<c:url value='/css/comm/global.css' />"/>
	<script src='<c:url value="/js/system/costtype/list.js" />'></script>
</head>

<body style="padding:0px 10px 10px 10px;">
	<!--菜单栏-->
	<nav class="navbar" id="navbar">
        <button id="addBtn" type="button">新增</button>
        <button id="delBtn" type="button">删除</button>
	</nav>
	<div class='listSplitLine'></div>
	<!--查询栏-->
	<div class="search-input navml15" id="search-input">
			<div class="list-search" id="list-search">
				<span>
					<b>消费类型</b>
					<input type="text" id="name" size="12" maxlength="30"/>
				</span>
				<span>
					<b>上级类型</b>
					<select name="status" id="status">
						<option value="0">启用</option>
						<option value="1">禁用</option>
						<option value="">所有</option>
					</select>
				</span>
				<span class="btn-search">
					<button class="search-btn" id="search" type="button">搜索</button>
				</span>
			</div>
	</div>
	<!--数据列表-->
	<table id="dataTable" 
			  data-tb="yes" 
			  data-toggle="table" 
			  data-ajax="ajaxRequest"  
			  data-side-pagination="server" 
			  data-pagination="true" 
			  data-sort-name="updatetime"
			  data-sort-order="desc" 
			  class="table-bordered table-condensed table-striped">
		<thead>
			<tr>
				<th data-checkbox='true' data-width="20" ></th>
				<%--
				<th data-field="id" data-width="40"  data-sortable="true"  data-halign="center" data-align="center">角色ID</th>
				 --%>
				<th data-field="name" data-sortable="true" data-halign="center" data-align="center" data-formatter='formatName'>消费类型</th>
				<th data-field="notes" data-halign="center" data-align="center">备注</th>
				<th data-field="createtime" data-width="180"  data-sortable="true" data-halign="center" data-align="center" data-formatter="formatDateTime">创建时间</th>
				<th data-field="updatetime" data-width="180"  data-sortable="true" data-halign="center" data-align="center" data-formatter="formatDateTime">最后修改于</th>
			</tr>
		</thead>
	</table>
</body>
</html>