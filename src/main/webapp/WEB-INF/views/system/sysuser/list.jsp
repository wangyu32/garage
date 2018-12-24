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
	<script src='<c:url value="/js/system/sysuser/list.js" />'></script>
</head>

<body style="padding:0px 10px 10px 10px;">
	<%--菜单栏--%>
	<nav class="navbar" id="navbar">
        <button id="addBtn" type="button">新增</button>
        <button id="delBtn" type="button">删除</button>
	</nav>
	<div class='listSplitLine'></div>
	<!--查询栏-->
	<div class="search-input navml15 search-current" id="search-input">
			<div class="list-search" id="list-search">
				<span>
					<b>登录名</b>
					<input type="text" class="u_logname" size="15" maxlength="30" name="u_logname"/>
				</span>
				<span>
					<b>姓名</b>
					<input type="text" class="u_realname" size="10" maxlength="25" name="u_realname"/>
				</span>
				<span >
					<b>电子邮箱</b>
					<input type="text" class="u_email" size="18" maxlength="25"  name="u_email"/>
				</span>
				<span >
					<b>联系方式</b>
					<input type="text" class="u_mobilephone" size="12" maxlength="20"  name="u_mobilephone"/>
				</span>
				<span>
					<b>状态</b>
					<select class="u_status" name="u_status">
						<option value="">所有</option>
						<option value="0">启用</option>
						<option value="1">禁用</option>
					</select>
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
			  data-sort-name="u_updatetime" 
			  data-sort-order="desc"
			  class="table-bordered table-condensed table-striped">
		<thead>
			<tr>
				<th data-checkbox='true' data-width="20" ></th>
				<%--
				<th data-field="u_id" data-width="40" data-sortable="true" data-halign="center" data-align="center">用户ID</th>
				 --%>
				<th data-field="u_logname" data-sortable="true" data-halign="center" data-align="left" data-formatter='formatName'>登录名</th>
				<th data-field="u_realname" data-sortable="true"  data-halign="center" data-align="left">姓名</th>
				<th data-field="u_email" data-sortable="true"  data-halign="center" data-align="left">电子邮箱</th>
				<th data-field="u_mobilephone" data-width="120"  data-sortable="true"  data-halign="center" data-align="center">联系方式</th>
				<th data-field="u_status" data-width="80"  data-halign="center" data-align="center" data-formatter="dataFormatterForCommonStatus">状态</th>
				<th data-field="u_updatetime" data-width="180" data-sortable="true"  data-halign="center" data-align="center" data-formatter="formatDateTime">最后修改于</th>
			</tr>
		</thead>
	</table>
	<!-- 弹窗查询 --><!-- 
	<div id="search-more-wrapper" class="none">
			<table class="more-table">
				<tr>
			        <td class="even">用户ID :</td>
			        <td class="odd">
						<input class="u_id" size="15" maxlength="8"  name="u_id">
					</td>
			        <td class="even">联系方式 :</td>
			        <td class="odd">							
						<input  type="text" class="u_mobilephone" size="15" maxlength="20"  name="u_mobilephone">
					</td>
			        <td class="even">登录名 :</td>
			        <td class="odd">
			        	<input  type="text" class="u_logname" size="15" maxlength="25" name="u_logname">
			        </td>
			    </tr>
			    <tr>
			        <td class="even">电子邮箱 :</td> 
			        <td id="type" class="odd">
		            	<input class="u_email" size="15" maxlength="20"  name="u_email">
			        </td>
			        <td class="even">姓名 :</td>
			        <td class="odd">
		            	<input class="u_realname" size="15" maxlength="25" name="u_realname">
			        </td>
			     </tr>
			     <tr>
			        <td class="even">状态 :</td>
			        <td id="status" class="odd">
						<select class="u_status" name="u_status">
							<option value="0">启用</option>
							<option value="1">禁用</option>
							<option value="">所有</option>
						</select>
			        </td>
			     </tr>
		</table>     
		<button type="button" class="button btn-primary btn-sm search-btn searchmorebtn" id="search">
			<span class="glyphicon glyphicon-search"></span> 搜索
		</button>	
		<button class="searchmorebtn" id="search">搜索</button>
	</div> -->
</body>
</html>