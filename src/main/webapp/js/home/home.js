/*!
 * Auth: zhengmy
 * Create:2016-07-25 17:06
 * Func:平台主界面相关的函数
 * Copyright 2016-2016 天津九萱公司 版权所有
 * 
 */

$(function(){
	 
	//定时器刷新session
	var timerSession = function(){
		$.ajax({
			async:false,
			url:"./timerSession",  
			cache:false,
			dataType:"json",
			success:function(json) { 
				 if (json.haslogin == false) {
					 top.location.href="./";
					 return ;
				 }
				 var a = Math.random()*10;
				 if (a > 2 ) json.msgnums = parseInt(a) ;
				 
				 //显示消息数
				 if (json.msgnums > 9) {
					 $(".message b").css('display','block');
					 $(".message b").html("9+");
				 }else if (json.msgnums > 0) {
					 $(".message b").html(json.msgnums);
					 $(".message b").css('display','block');
				 }else
					 $(".message b").css('display','none');
			}
		});
	}
	
	//定义定时器，定时器刷新session
	//var ntimersession = window.setInterval(timerSession,1000);
	
	/*//场地订单搜索按钮
	$("#search_order").next().click(function() {
		searchOrder();
	});
	
	//场地订单搜索回车事件
	$("#search_order").keydown(function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==13){ 
			searchOrder();
       }
	});*/
	
})

function searchOrder() {
	var content=$("#search_order").val();
	if(content==$("#search_order").attr("data-v")) content="";
	var obj=$(".functree span[dataLink='venues/orderM']");
	obj.attr("dataLink", "venues/orderM?phone="+content);
	obj.click();
	obj.attr("dataLink", "venues/orderM");
	$("#search_order").val($("#search_order").attr("data-v"));
}