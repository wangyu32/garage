<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<jsp:include flush="true" page="../comm/iframeInc.jsp">
		<jsp:param value="yes" name="echarts"/>
	</jsp:include>
	<link rel="stylesheet" href='<c:url value="/css/home/index.css" />' />
	<script src='<c:url value="/js/home/index.js?1=1"/>'></script>
</head>

 <body>
 	<div class="con">
        <ul class="data-show">
            <li>
                <span><img src="<c:url value='/images/home/index/icon1.png'/>"/><b>今日场地订单量</b></span>
                <span id="totalOrder"></span>
            </li>
            <li>
                <span><img src="<c:url value='/images/home/index/icon2.png'/>"/><b>今日场地销售流水</b></span>
                <span id="totalAccount"></span>
            </li>
           <li>
                <span><img src="<c:url value='/images/home/index/icon3.png'/>"/><b>今日未收款订单量</b></span>
                <span id="unStartedOrder"></span>
            </li>
           <li>
                <span><img src="<c:url value='/images/home/index/icon4.png'/>"/><b>会员总数</b></span>
                <span id="memberCount"></span>
            </li>
            <li>
                <span><img src="<c:url value='/images/home/index/icon5.png'/>"/><b>会员总余额</b></span>
                <span id="memberBalance"></span>
            </li>
           <li>
                <span><img src="<c:url value='/images/home/index/icon6.png'/>"/><b>近30日总流水</b></span>
                <span id="monthAccount"></span>
            </li>
        </ul>
        <ol class="shape-show">
            <li>
                <div class="shape-show-top">
                    <span>
                        <img src="<c:url value='/images/home/index/shape-img1.png'/>"/>
                        <b>近7日流水数据走势图</b>
                    </span>
                    <span></span>
                </div>
                <div class="shape-show-con">
                    <div id="zhuxing"></div>
                </div>
            </li>
            <li>
                <div class="shape-show-top">
                    <span>
                        <img src="<c:url value='/images/home/index/shape-img2.png'/>"/>
                        <b>消息提醒</b>
                    </span>
                    <span>更多>></span>
                </div>
                <div class="shape-show-con">
                    <dl>
                        <dt>会员消费</dt>
                        <dd>
                                                                                    王凯　　预订了 2016/6/20 15：00-17：00　　篮球-1号场地，篮球--2号场地篮球-3号场地，篮球-4号场地
                            <span>14.00</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>普通消费</dt>
                        <dd>
                                                                                    王凯   成为了普通会员 
                            <span>星期日</span>
                        </dd>
                    </dl>
                     <dl>
                        <dt>充值购卡</dt>
                        <dd>
                                                                                    王凯   对账户充值了2000元
                            <span>2016/6/01</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>会员消费</dt>
                        <dd>
                                                                                    王凯   预定了6月18日 17:00-18:00   足球-场地2号
                            <span>2016/6/01</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>会员办理</dt>
                        <dd>
                                                                                    王凯   购买了 篮球月卡   
                            <span>2016/6/01</span>
                        </dd>
                    </dl>
                </div>
            </li>
        </ol>
        <ol class="shape-show">
            <li>
                <div class="shape-show-top">
                    <span>
                        <img src="<c:url value='/images/home/index/shape-img3.png'/>"/>
                        <b>近7日场地利用率数据走势图</b>
                    </span>
                    <span></span>
                </div>
                <div class="shape-show-con">
                    <div id="zhexian"></div>
                </div>
            </li>
            <li>
                <div class="shape-show-top">
                    <span>
                        <img src="<c:url value='/images/home/index/shape-img3.png'/>"/>
                        <b>近7日场地订单量数据走势图</b>
                    </span>
                    <span></span>
                </div>
                <div class="shape-show-con">
                    <div id="zhexian-bg"></div>
                </div>
            </li>
        </ol>
         <ol class="shape-show">
            <li>
                <div class="shape-show-top">
                    <span>
                        <img src="<c:url value='/images/home/index/shape-img3.png'/>"/>
                        <b>近7日会员消费频次走势图</b>
                    </span>
                    <span></span>
                </div>
                <div class="shape-show-con">
                    <div id="zhexian-up"></div>
                </div>
            </li>
            <li>
                <div class="shape-show-top">
                    <span>
                        <img src="<c:url value='/images/home/index/shape-img4.png'/>"/>
                        <b>近30日各类数据占比</b>
                    </span>
                    <span></span>
                </div>
                <div class="shape-show-con">
                    <div class="bingtu-pay">
                        <div id="bingtu-pay"></div>
                        <span>支付占比</span>
                    </div>
                    <div class="bingtu-pay">
                        <div id="bingtu-income"></div>
                        <span>收入占比</span>
                    </div>
                </div>
            </li>
        </ol>
    </div>
	<c:import url="/footer"></c:import>
 </body>
</html>