<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="shortcut icon"  href='<c:url value="/images/jiege-favicon.ico" /> '>
<link rel="stylesheet" href='<c:url value="/css/comm/reset.css" />' />
<link rel="stylesheet" href='<c:url value="/css/comm/global.css" />' /> 
<link rel="stylesheet" href='<c:url value="/css/comm/jquery.jerichotab.css" />' />
<link rel="stylesheet" href='<c:url value="/css/home/left.css" />' />
<link rel="stylesheet" href='<c:url value="/css/home/home.css" />' /> 
<link rel="stylesheet" href='<c:url value="/component/bootstrap/css/bootstrap.min.css" />' />
<script src='<c:url value="/js/comm/jquery-1.12.0.min.js" />'></script>
<script src='<c:url value="/js/comm/comm.js" />'></script>
<script src='<c:url value="/js/comm/jquery.jerichotab.js" />'></script>
<script src='<c:url value="/js/comm/left.js" />'></script>
<script src='<c:url value="/component/bootstrap/js/bootstrap.min.js" />'></script>

<%
//弹出层控件 2016-3-23 15:03 by licui
if (request.getParameter("layer") != null && !"".equals(request.getParameter("layer")))
{
%>
<script src="<c:url value='/component/layer/layer.js' />"></script> 
<%
} 
%>

