$(function(){

	function resize() {
		var w = document.body.clientWidth;
		$('.login-left img').css({
			'width' : w - 300,
			'height' : 'auto'
		});
	}
	window.onresize = resize;// 页面窗口大小改变时 触发
	resize();// 页面刚刚打开的时候初始化
    
	$('#verifycode').css("cursor","pointer");
	//生成验证码
	$("#verifycode").click(function() {
		var timestamp = (new Date()).valueOf();
		this.src = "Verify/Code?a=" + timestamp;
		$("#checkcode").val("");
		$("#checkcode").focus();
	});
    
	//监听回车事件，触发登录按钮
	$(".login_inner_container input").bind("keydown", function(e) {
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			// 回车执行查询
			$("#btnlogin").click();
		}
	});
    
    // 登录的错误信息
    var errorMsg = $("#alert")[0].innerText;
    if(errorMsg != ''){
    	var recordAccountReturn = $("#recordAccountReturn").val();
    	if(recordAccountReturn == '1'){
    		$("#recordAccount").attr("checked",true);
    	}
    	return;
    }
    
    //获取cookie,记住用户名
	var username = getCookie('star-bbm');
	
	if(username  != ''){
		$("#recordAccount").attr("checked",true);
		$("#username").val(username);
	}else{
		$("#recordAccount").attr("checked",false);
		$("#username").val("");
		$("#userpwd").val("");
	}
	
	$("#btnlogin").click(function(){
		$("#alert")[0].innerText = "";
		var username = $("#username").val().trim();
		if(username == ''){
			return errorMessage("登录名不能为空");
		}
		var password = $("#password").val().trim();
		if(password == ''){
			return errorMessage("密码不能为空");
		}
		var checkcode = $("#checkcode").val().trim();
		if(checkcode == ''){
			return errorMessage("验证码不能为空");
		}
		
		var postData = $('#loginForm').serializeArray();
		$.post('userlogin', postData, function(res) {
			if(res.code == 200){
				window.location.href = 'home';
			}else{
				$("#verifycode").click();
				errorMessage(res.message);
			}
		}, 'json')
		.error(function(){
			errorMessage('请求错误!');
      	});
	});
});

//提示错误信息
function errorMessage(msg){
	$("#alert")[0].innerText = "";
	$("#alert")[0].innerText = msg;
	return false;
}

//从cookie中获取登录名
function getCookie(c_name){
	if (document.cookie.length > 0) {
		var usernameStr = document.cookie.replace(/\"/g, "");
		var c_start = usernameStr.indexOf(c_name + "=");
		var c_end = null;
		var username = null;
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;
			var c_end = usernameStr.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = usernameStr.length;
			}
			username = unescape(usernameStr.substring(c_start, c_end));
			return username;
		}
	}
	return ""
}