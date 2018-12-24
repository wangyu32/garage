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
	<link rel="stylesheet" href="<c:url value='/css/garage/user/edit.css' />"/>
	<script src='<c:url value="/js/garage/user/edit.js" />'></script>
</head>

<body class="data-wrapper p20edit">
<!-- 标题 -->
<div class="editTitle">
	<h5 class="panel-title title"></h5>
	<div style="width:65px;"></div>
</div>
<!-- 表单 -->
<form class="form-horizontal editPage" id="form">
	<input type="hidden" name="id" id ="id" class="form-control" value="${model.id}">
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">手机号</label>
		<div class="col-sm-6">
			<input type="text" name="phone" class="form-control" min="1" maxlength=20  value="${model.phone}">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">姓名</label>
		<div class="col-sm-6">
			<input type="text" name="name" class="form-control"  maxlength=25 value="${model.name}">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label clearfix">性别</label>
		<div class=" col-sm-6">
			<label class="radio-inline">
				<input type="radio" name="sex" value='0' <c:if test="${(model.sex eq 0) or (model.sex eq null)}">checked</c:if> /> 男
			</label>
			<label class="radio-inline">
				<input type="radio" name="sex" value='1' <c:if test="${(model.sex eq 1)}">checked</c:if> /> 女
			</label>
		</div>
	</div>
    <div class="form-group clearfix" id="up_password">
        <label class="col-sm-2 control-label">密码</label>
        <div class="col-sm-6">
            <input type="text" name="password" class="form-control"  maxlength=25  value="123456" data-noempty='密码' id="password" readonly="readonly">
            <input type="hidden" name="passwordConfirm" class="form-control"  value="123456" id="passwordConfirm" readonly="readonly">
        </div><span class="required">*</span>
    </div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label clearfix">类型</label>
		<div class=" col-sm-6">
			<label class="radio-inline">
				<input type="radio" name="type" value='0' <c:if test="${(model.type eq 0) or (model.type eq null)}">checked</c:if> />临时用户
			</label>
			<label class="radio-inline">
				<input type="radio" name="type" value='1' <c:if test="${(model.type eq 1)}">checked</c:if> />会员
			</label>
			<label class="radio-inline">
				<input type="radio" name="type" value='2' <c:if test="${(model.type eq 2)}">checked</c:if> />管理员
			</label>
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">单价</label>
		<div class="col-sm-6">
			<input type="text" name="price" class="form-control" maxlength=5  value="${model.price eq null ? 2.0 : model.price}">
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