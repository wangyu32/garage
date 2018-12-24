<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>智能车库-${CURRENT_APP.p_name}</title>
	<link rel="shortcut icon"  href='<c:url value="/images/jiege-favicon.ico" /> '>
	<jsp:include flush="true" page="comm/homeInc.jsp">
		<jsp:param value="yes" name="layer"/>
	</jsp:include>	
	<script src='<c:url value="/js/home/home.js"/>'></script>
</head>

 <body>
	<div class="divMain">
		<div class="divLeft">
		    <div class="logo">
		        <h1>
					<!--
		            <img src="<c:url value='images/logo/${CURRENT_APP.p_id}.png'/>"/>
		            -->
		        </h1>
		    </div>
			<ul class="functree">				 
                <li>
                    <span id="home" class="func" dataType='iframe' dataLink='home.html' jerichotabindex="0"><s></s>首页</span>
                </li>
                
                <!-- 列表数据 -->
                <c:forEach var="level1" items="${menu}">
	                <li>
	                    <span class="modules">
	                        <img src="${level1.m_image}"/>${level1.m_name}<i></i>
	                    </span>
	                    <ul>
	                        <c:forEach var="level2" items="${level1.items}">
	                            <li>
	                                <span class="func" dataType='iframe' dataLink="${level2.mm_url}"><s></s>${level2.mm_name}</span>
	                            </li> 
	                        </c:forEach>
	                    </ul>
	                </li>
	            </c:forEach>
                
				</ul>
			<ul>
				<li class="message">
                    <span id="message" class="func" dataType='iframe' dataLink='message'><s></s>我的消息</span>
                </li>
                <li class="home">
					<span id="home" class="func" dataType='iframe' dataLink='index' jerichotabindex="0"><s></s>首页</span>
				</li>
		 	</ul>
		</div>
		<div class="divRight">
			<div class="con-top">
			    <!-- <div class="con-top-left">
			    	 <img src="images/home/notice.png"/>
			        <span>
			        	用户刚刚上传了视频，请及时查看！ 
			        </span> 
			    </div> -->
			    <div class="con-top-right"><!-- 去掉搜索框 -->
			    	<!-- <div class="search">
			    		<input type="text" id="search_order" data-v="场地订单搜素(手机号)">
			    		<img src="images/home/search.png">
			    	</div> -->
					<!--

			       <a class="message"  href="javascript:void(0);">
			        	<img src="images/home/message.png">
			        	<span>消息</span>
            			<b></b>
			        </a>
			        -->
			        <!-- <a  href="help" target="_blank">
			        	<img src="images/home/question.png">
			        	<span>帮助</span>
			        </a> -->
			        <div class="admin">
			            <img src="images/home/admin.png" />
			           <span>${CURRENT_USER.u_logname }</span>
			            <s><img src="images/home/admin-arrow.png"></s>
			            <ul> 
			                <li>
			                    <a id="editpas" class="current" href="#">修改密码</a>
			                </li>
			                <li>
			                    <a id="alert" class="current" href="#">关于...</a>
			                </li>
			                <li>
			                    <a id="exit" href="javascript:void(0);">退出系统</a>
			                </li>
			            </ul>
			        </div>
			        <div class="closed">
			            <s></s>
			            <ul>
			                <li id="closeAll">关闭所有</li>
			                <li id="closeCurrent">关闭当前</li>
			                <li id="loadCurrent">刷新当前</li>
			            </ul>
			        </div>
			    </div>
			</div>
		</div>
	</div>
	<c:import url="main/about.jsp"></c:import>
	<c:import url="main/changpwd.jsp"></c:import>
 </body>

</html>