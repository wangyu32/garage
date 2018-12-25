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
	<script src='<c:url value="/js/garage/stoprecording/list.js" />'></script>
</head>

<body style="padding:0px 10px 10px 10px;">
	<%--菜单栏
	<nav class="navbar" id="navbar">
        <button id="addBtn" type="button">新增</button>
        <button id="delBtn" type="button">删除</button>
	</nav>
	<div class='listSplitLine'></div>
	--%>
	<!--查询栏-->
	<div class="search-input navml15 search-current" id="search-input">
			<div class="list-search" id="list-search">
				<span>
					<b>姓名</b>
					<input type="text" class="name" size="10" maxlength="15" name="name"/>
				</span>
				<span>
					<b>性别</b>
					<select class="sex" name="sex">
						<option value="">所有</option>
						<option value="0">男</option>
						<option value="1">女</option>
					</select>
				</span>
				<span >
					<b>手机号</b>
					<input type="text" class="phone" size="12" maxlength="20"  name="phone"/>
				</span>
				<span>
					<b>类型</b>
					<select class="type" name="type">
						<option value="">所有</option>
						<option value="0">临时用户</option>
						<option value="1">会员</option>
						<option value="2">管理员</option>
					</select>
				</span>
				<span>
					<b>状态</b>
					<select class="status" name="status">
						<option value="">所有</option>
						<option value="0">已入库</option>
						<option value="1">已出库</option>
					</select>
				</span>
                <span>
                    <b>入库时间</b>
                    <input type="text" id="intimeStart" class="dateimg mr3 intimeStart  Wdate" name="intimeStart" value="${intimeStart}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style='width:110px;'>至
                    <input type="text" id="intimeEnd" class="dateimg intimeEnd  Wdate"  name="intimeEnd"  value="${intimeEnd}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style='width:110px;'>
                </span>
                <span>
                    <b>出库时间</b>
                    <input type="text" id="outtimeStart" class="dateimg mr3 outtimeStart  Wdate" name="outtimeStart" value="${outtimeStart}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style='width:110px;'>至
                    <input type="text" id="outtimeEnd" class="dateimg outtimeEnd  Wdate"  name="outtimeEnd"  value="${outtimeEnd}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style='width:110px;'>
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
			  data-sort-name="intime"
			  data-sort-order="desc"
			  class="table-bordered table-condensed table-striped">
		<thead>
			<tr>
				<th data-checkbox='true' data-width="20" ></th>
				<%--
				<th data-field="u_id" data-width="40" data-sortable="true" data-halign="center" data-align="center">用户ID</th>
				 --%>
				<th data-field="name" data-sortable="true" data-width="100" data-align="center">姓名</th>
				<th data-field="sex" data-sortable="true"  data-width="50" data-align="center" data-formatter='formatterSex'>性别</th>
				<th data-field="phone" data-sortable="true" data-width="200"  data-align="center">手机号</th>
				<th data-field="type" data-sortable="true" data-width="100"  data-align="center" data-formatter='formatterType'>用户类型</th>
				<th data-field="status" data-sortable="true" data-width="80"  data-align="center" data-formatter='formatterStatus'>状态</th>
				<th data-field="intime" data-sortable="true" data-width="80"  data-align="center" data-formatter='formatDateTime'>入库时间</th>
				<th data-field="outtime" data-sortable="true" data-width="80"  data-align="center" data-formatter='formatOutTime'>出库时间</th>
				<th data-field="totaltime" data-sortable="true" data-width="80"  data-align="right" >停车时长</th>
				<th data-field="amount" data-sortable="true" data-width="80"  data-align="right" data-formatter='formatterPrice'>金额</th>
				<th data-field="price" data-sortable="true" data-width="80"  data-align="right" data-formatter='formatterPrice'>单价</th>
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