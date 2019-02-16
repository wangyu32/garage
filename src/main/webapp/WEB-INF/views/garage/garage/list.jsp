<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<jsp:include flush="true" page="../../comm/iframeInc.jsp">
		<jsp:param value="yes" name="table"/>
		<jsp:param value="yes" name="layer"/>
		<jsp:param value="yes" name="bootstrap"/>
        <jsp:param value="yes" name="ckplayer"/>
        <jsp:param value="yes" name="switch"/>
        <jsp:param value="yes" name="DatePicker"/>
	</jsp:include>
	<link rel="stylesheet" href="<c:url value='/css/comm/global.css' />"/>
	<script src='<c:url value="/js/garage/garage/list.js" />'></script>
</head>

<body style="padding:0px 10px 10px 10px;">
	<%--菜单栏--%>
	<nav class="navbar" id="navbar">
        <button id="addBtn" type="button">新增</button>
	</nav>
	<div class='listSplitLine'></div>
	<!--查询栏-->
	<div class="search-input navml15 search-current" id="search-input">
			<div class="list-search" id="list-search">
				<span>
					<b>名称</b>
					<input type="text" class="name" size="10" maxlength="15" name="name"/>
				</span>
				<span class="btn-search">
        			<button class="search-btn" type="button">搜索</button>
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
			  data-sort-name="createtime"
			  data-sort-order="desc"
			  class="table-bordered table-condensed table-striped">
		<thead>
			<tr>
				<th data-checkbox='true' data-width="20" ></th>
				<th data-field="name" data-sortable="true" data-width="150" data-align="center" data-formatter='formatName'>名称</th>
				<th data-field="total" data-sortable="true" data-width="40"  data-align="right">总车位</th>
				<th data-field="inuse" data-sortable="true" data-width="40"  data-align="right">已用车位</th>
				<th data-field="unuse" data-sortable="true" data-width="40"  data-align="right">空闲车位</th>
				<th data-field="price" data-sortable="true" data-width="40"  data-align="right">单价</th>
				<th data-field="uname" data-sortable="true" data-width="80"  data-align="center" >计费单位</th>
				<th data-field="serverIp" data-sortable="true" data-width="80"  data-align="center" >服务IP</th>
				<th data-field="serverPort" data-sortable="true" data-width="80"  data-align="center" >服务端口</th>
				<th data-field="createtime" data-sortable="true" data-width="180" data-align="center" data-formatter="formatDateTime">创建于</th>
			</tr>
		</thead>
	</table>
</body>
</html>