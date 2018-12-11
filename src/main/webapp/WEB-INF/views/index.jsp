<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<link rel="stylesheet" href="<c:url value='/css/comm/global.css' />"/>
	<jsp:include flush="true" page="comm/iframeInc.jsp">
		<jsp:param value="yes" name="layer"/>
		<jsp:param value="yes" name="echarts"/>
	</jsp:include>
	<script src='<c:url value="/js/index/index.js" />'></script>
</head>

<body style="padding:0 5px 0 5px;">
	<span class="none" id="coin">${CURRENT_USER.u_coinunit }</span>
	<div class="leftdata">
		<div class="caption"></div>
		<div class="chart">

		</div>
	</div>
	
	
</body>
</html>