<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<jsp:include flush="true" page="../../comm/iframeInc.jsp">
		<jsp:param value="yes" name="table"/>
		<jsp:param value="yes" name="validate"/> 
		<jsp:param value="yes" name="layer"/>
		<jsp:param value="yes" name="bootstrap"/>
	</jsp:include>
	<link rel="stylesheet" href="<c:url value='/css/system/sysuser/edit.css' />"/>
	<script src='<c:url value="/js/system/sysuser/edit.js" />'></script>
</head>

<body class="data-wrapper p20edit">
	<!-- 标题 -->
	<div class="editTitle">
		<h5 class="panel-title title"></h5>
		<div style="width:81px;"></div>
	</div>
	<!-- 表单 -->
	<form class="form-horizontal editPage" id="form">
		<input type="hidden" name="id" id ="id" class="form-control" value="${model.id}">
		<div class="form-group clearfix">
		    <label class="col-sm-2 control-label"> 登录名</label>
		    <div class="col-sm-6">
				<div class="input-group">
				  <input type="text" name="logname" class="form-control" maxlength=30  value="${model.logname}" data-noempty='登录名' data-loginname='登录名'>
				  <span class="input-group-addon" id="basic-addon2"></span>
				</div>
			</div>
			<span class="required">*</span>
		</div>
		<div class="form-group clearfix" id="up_password">
		    <label class="col-sm-2 control-label">密码</label>
		    <div class="col-sm-6">
		      <input type="password" name="password" class="form-control"  maxlength=25  value="${model.password}" data-noempty='密码' id="password">
		    </div><span class="required">*</span>
		</div>
		<!-- 角色 -->
		<div class="form-group clearfix " >
			<label class="col-sm-2 control-label clearfix">角色</label>
			<div class="col-sm-6" id="roleDiv">
				<table id="roleTable" 
						  data-tb="yes" 
						  data-toggle="table" 
						  data-ajax="ajaxRequest"  
						  data-side-pagination="server" 
						  data-pagination="false" 
						  data-sort-name="r_id" 
						  data-sort-order="asc" 
						  class="table-bordered table-condensed table-striped">
					<thead>
						<tr>
							<th data-checkbox='true' data-width="20" data-formatter='formatChecked'></th> 
							<th data-field="r_name" data-width="" data-sortable="true" data-halign="center" data-align="center">角色名称</th>
							<th data-field="r_desc" data-width="" data-sortable="true" data-halign="center" data-align="center">描述</th>
							<%--
							<th data-field="r_id" data-width=""  data-sortable="true"  data-halign="center" data-align="center">角色ID</th>
							 
							<th data-field="r_status" data-width="" data-sortable="true" data-halign="center" data-align="center" data-formatter="dataFormatterForCommonStatus">状态</th>--%>
						</tr>
					</thead>
				</table>
			</div><span class="required">*</span>
		</div>
		<div class="form-group clearfix">
		    <label class="col-sm-2 control-label">姓名</label>
		    <div class="col-sm-6">
		      <input type="text" name="realname" class="form-control"  maxlength=25 value="${model.realname}">
		    </div>
		</div>
		<div class="form-group clearfix">
		    <label class="col-sm-2 control-label">电子邮箱</label>
		    <div class="col-sm-6">
				
				  <input type="email" name="email" class="form-control" placeholder="" value="${model.email}" aria-describedby="basic-addon2">
			</div>
		</div>
		<div class="form-group clearfix">
			<label class="col-sm-2 control-label">移动电话</label>
		    <div class="col-sm-6">
		      	<input type="text" name="mobilephone" class="form-control" min="1" maxlength=20  value="${model.mobilephone}">
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
			 	<button class="save" type="button" id="save">保存</button>
			 	<button class="back" id="back" type="button">取消</button>
			 	<button class="repassword" id="repassword" type="button">重设密码</button>
		    </div>
		</div>
	</form> 
</body>

</html>