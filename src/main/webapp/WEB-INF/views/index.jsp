<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<link rel="stylesheet" href="<c:url value='/css/comm/global.css' />"/>
	<jsp:include flush="true" page="comm/iframeInc.jsp">
		<jsp:param value="yes" name="layer"/>
	</jsp:include>
</head>

<body style="padding:0 5px 0 5px;">

            <div class="leftdata">
                <div class="caption"></div>
                <div class="chart">
                    <div>
                        <div class="line">入库二维码</div>
                        <div>
                            <img src="qccode/comein">
                        </div>
                    </div>
                    <div>
                        <div class="line">出库二维码</div>
                        <div>
                            <img src="qccode/comeout">
                        </div>

                    </div>
                </div>
            </div>
	
	
</body>
</html>