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
	<link rel="stylesheet" href="<c:url value='/css/garage/garage/edit.css' />"/>
	<script src='<c:url value="/js/garage/garage/edit.js" />'></script>
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
		<label class="col-sm-2 control-label">名称</label>
		<div class="col-sm-6">
			<input type="text" name="name" class="form-control" min="1" maxlength=20  value="${model.name}" id="name">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">总车位</label>
		<div class="col-sm-6">
			<input type="text" name="total" class="form-control" min="1" maxlength=20  value="${model.total}" id="total">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">已用车位</label>
		<div class="col-sm-6">
			<input type="text" name="inuse" class="form-control" min="1" maxlength=20  value="${model.inuse}" id="inuse">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">空闲车位</label>
		<div class="col-sm-6">
			<input type="text" name="unuse" class="form-control" min="1" maxlength=20  value="${model.unuse}" id="unuse">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">单价</label>
		<div class="col-sm-6">
			<input type="text" name="price" class="form-control" min="1" maxlength=20  value="${model.price}" id="price">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">服务IP</label>
		<div class="col-sm-6">
			<input type="text" name="serverIp" class="form-control" min="1" maxlength=20  value="${model.serverIp}" id="serverIp">
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label">服务端口</label>
		<div class="col-sm-6">
			<input type="text" name="serverPort" class="form-control" min="1" maxlength=20  value="${model.serverPort}" id="serverPort">
		</div>
	</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label clearfix">计费单位</label>
		<div class=" col-sm-6">
            <select class="priceUnitId" name="priceUnitId">
                <c:forEach items="${priceUnitList}" var="it">
                    <option value="${it.id}">${it.uname}</option>
                </c:forEach>
            </select>
    <%--
			<label class="radio-inline">
				<input type="radio" name="sex" value='0' <c:if test="${(model.sex eq 0) or (model.sex eq null)}">checked</c:if> /> 男
			</label>
			<label class="radio-inline">
				<input type="radio" name="sex" value='1' <c:if test="${(model.sex eq 1)}">checked</c:if> /> 女
			</label>
    --%>
		</div>
	</div>
	<div class="form-group clearfix">
		<label class="col-sm-2 control-label clearfix"></label>
		<div class=" col-sm-6">
			<button class="save" type="button" id="save">保存</button>
			<button class="back" id="back" type="button">取消</button>
		</div>
	</div>
</form>
</body>

</html>