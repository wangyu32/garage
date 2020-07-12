<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>智能云</title>
	<link rel="shortcut icon"  href='<c:url value="/images/jiege-favicon.ico" /> '>
	<jsp:include flush="true" page="comm/homeInc.jsp">
		<jsp:param value="yes" name="layer"/>
	</jsp:include>	
	<link rel="stylesheet" href="css/comm/reset.css"/>
	<link rel="stylesheet" href="css/login/login.css"/>
	<script type="text/javascript" src="js/login/login.js"></script>
</head>

<body>
	<div class="login">
        <div class="login-left">
            <img src='<c:url value="/images/login/login.png" />' style='height:100%;'>
        </div>
        <%-- action='<c:url value="/userlogin" />' --%>
      <form method="post" id="loginForm" >
        <div class="login-right">
            <table class="login_panel_layout">
                <tr>
                    <td>
                    <div class="login_inner_container">
                        <div class="inner">
                            <h1>智能车库</h1>
                            <div>
                                <label for="">用户名:</label>
                                <input type="text" value="${username }" maxlength="50" id="username" name="username">
                            </div>
                            <div>
                                <label for="">密码:</label>
                                <input type="password" value="${password }" maxlength="16" id="password" name="password">
                            </div>
                            <div>
                                <label for="">验证码:</label>
                                <input type="text" class="code" maxlength="4" id="checkcode" name="checkcode">
                                <img id="verifycode" src="Verify/Code"/>
                            </div>
                            <div class="clearfix"></div>
                            <div>
                          	  	<span class="remmber">
							        <input id="recordAccountReturn" type="hidden" value="${recordAccount}">
							        <input id="recordAccount" type="checkbox" value="1" name="recordAccount">
									<label for="recordAccount">记住用户名</label>
                                </span>
                            </div>
                            <div>
                                <button type="button" id="btnlogin">登录</button>
                            </div>
                            <div class="erroralert" id="alert">${errorMessage}</div>
                        </div>
                    </div>
                    <div class="foot">
                         <p><a href="http://www.prm.com" target='_blank'>管理系统</a></p>
                         <p>Copyright@2018-2020</p>
                     </div>
                    </td>
                </tr>
            </table>
        </div>
      </form>
    </div>
</body>

</html>