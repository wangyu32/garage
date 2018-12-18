<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<jsp:include flush="true" page="../../comm/iframeInc.jsp">
		<jsp:param value="yes" name="treeview"/>
		<jsp:param value="yes" name="layer"/>
		<jsp:param value="yes" name="bootstrap"/>
	</jsp:include>
	
	<script src='<c:url value="/js/system/role/roleMenu.js" />'></script>
</head>

<body>
	<input id="r_id" type="hidden" value="${r_id}">
	<div class="p20">
		<div class="row mb15">
			<button type="button" class="btn  btn-primary btn-sm" onclick="dosome(1)">选择全部</button>
			<button type="button" class="btn  btn-primary btn-sm" onclick="dosome(2)">全不选择</button>
			<button type="button" class="btn  btn-primary btn-sm" onclick="dosome(4)">展开全部</button>
			<button type="button" class="btn  btn-primary btn-sm" onclick="dosome(3)">折叠全部</button>
			<button type="button" class="btn  btn-primary btn-sm" onclick="save()">保存</button>
			<button type="button" class="btn  btn-primary btn-sm" onclick="back()">取消</button>
		</div>
		
		<div class="row" style="width:395px !important;">
			<div class="col-sm-12">
				<label for="treeview"></label>
			</div>
			<div id="treeview"></div>
			<div style="display: none;" id="treeviewVal"></div>
		</div>
	</div>
</body>
</html>