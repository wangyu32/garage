$(document).ready(function(){
	
	//页面表格分页统一处理
	var tables = $("table[data-tb]"); 
	if (tables.length > 0){
		for(var i=0; i<tables.length; i++){
			var table = tables[i];
			if($(table).attr("data-page-size")==undefined) {
				$(table).attr("data-page-size","15");}
			if($(table).attr("data-page-list")==undefined) 
				$(table).attr("data-page-list","[15]");
			if($(table).attr("data-pagination-first-text")==undefined) 
				$(table).attr("data-pagination-first-text","首页");
			if($(table).attr("data-pagination-pre-text")==undefined) 
				$(table).attr("data-pagination-pre-text","上一页");
			if($(table).attr("data-pagination-next-text")==undefined) 
				$(table).attr("data-pagination-next-text","下一页");
			if($(table).attr("data-pagination-last-text")==undefined) 
				$(table).attr("data-pagination-last-text","末页");
		}
	}
	
	//日期控件点击事件
	$('.Wdate').click(function(){
		WdatePicker();
	});
	/*//回车搜索
	document.onkeydown = function (e) {  
	    if (!e) e = window.event;  
	    if ((e.keyCode || e.which) == 13) {
	    	if(table){
	    		$(table).bootstrapTable('refresh');
	    		table.bootstrapTable('selectPage',1);
	    	}
	    }  
	}
	//点击更多按钮
	$('.more').click(function() {
		$('.list-search').stop().toggleClass('show');
	}); 
	//radio按钮
	$(".people").on("click",function() {
		$(this).addClass("on").siblings().removeClass("on");
	 });
	$(".people+b").on("click",function() {
		$(this).prev().addClass("on").siblings().removeClass("on");
	});
	
	//数字控件点击事件
	$('.TBNum').keyup(function(){
		this.value=this.value.replace(/[^0-9\.]/,'');
	});
	
	//按钮事件
	 $('button').mouseover(function(event) {
		 if($(this).hasClass("not-click")) return;
     	$(this).addClass('btn-hover');
     }).mouseout(function(event) {
    	 $(this).removeClass('btn-hover');
     });
	 
	//搜索框
	var search =$('.search input');
	if(search.val() == "") search.val(search.attr("data-v"));
	search.focus(function(){
		 if(this.value==search.attr("data-v")) this.value="";
	}).blur(function(event) {
	     if(this.value=="") {
	    	 this.value=search.attr("data-v");
	     }
	});*/
//	$(window).unload(function(){
//        //响应事件
//        alert("获取到了页面要关闭的事件了！"); 
//    }); 
	
});
//window.onbeforeunload = function(event) { 
//	return confirm("确定离开此页面吗？"); 
//}
/*//支付样式初始化
function payStyleInitialize(){
	$('.sweepcode').css('display','block');
    $('.phone .code').css('display','block');
	$('.phone .ar').css('display','block');
	$('.phone .payfor').css('display','block');
    $('.phone button').css('display','none');
    $('.phone .cash').css('display','none');
    $('.phone span:first-child').css('margin-top','0');
    $('.sweepcode>div').removeClass('xj-bg');
}

//现金支付样式
function cashPayStyle() {
	$('.phone button').css('display','block');
	$('.sweepcode>div').addClass('xj-bg');
	$('.phone .cash').css({'display':'block','margin':'40px 0'});
	$('.phone .code').css('display','none');
	$('.phone .ar').css('display','none');
	$('.phone .payfor').css('display','none');
	$('.phone span:first-child b').text('现金支付');
	$('.phone span:first-child').css('margin-top','40px');
	$('.phone span:first-child img').attr('src','../images/member/cash.png');
}

//微信支付样式
function wxPayStyle() {
	$('.phone span:first-child b').text('微信');
	$('.phone .payfor').text('扫微信付款');
	$('.phone span:first-child img').attr('src','../images/member/wx.png');
}

//支付宝支付样式
function aliPayStyle() {
	$('.phone span:first-child b').text('支付宝');
	$('.phone .payfor').text('扫支付宝付款');
	$('.phone span:first-child img').attr('src','../images/member/zhifu.png');
}


*//**
 * 格式显示2位小数
 * @param value
 * @returns
 *//*
function formatNum2(num){
	if(isNaN(num))
	num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
	cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + num + '.' + cents);
}

*//**
 * 显示百分比
 * @param value
 * @returns {String}
 *//*
function formatPercent2(value){
	return formatNum2(value)+ "%";
}*/

/**
 * 日期处理 YYYY-MM-dd
 * @param value
 * @returns {String}
 *//*
function formatDate2(value){
	if(value==undefined) return "";
	if (value=="-") return "";
	var val = Date.parse(value);
	var date = new Date(val);	
	var str = date.getFullYear() + "-" + get2Len(date.getMonth()+1) + "-" + get2Len(date.getDate()) ;
	 
	return str ;
}*/
/**
 * 序号
 * @param value
 * @param row
 * @param index
 * @returns
 *//*
function indexFormatter(value,row,index){
	return index+1;
}*/

/**
 * 时间格式处理  HH:mm
 * @param value
 * @returns {String}
 *//*
function formatTime2(value){
	var h = parseInt( value / (1000*60*60));
	value = value - h*(1000*60*60);
	var m = parseInt(value / (1000*60));
	
	return get2Len(h) + ":" + get2Len(m);
}*/


/**
 * 初始化开关按钮
 */
function initSwitch(){
	$("[name='my-checkbox']").bootstrapSwitch({		
		onText:"启用",  
        offText:"禁用",  
        onColor:"success",  
        offColor:"warning",  
        size:"small",  
        onSwitchChange:function(event,state){  
          console.log('event,state=',event,state);
          var status;
      	  state ? status=1:status=0;
      	  var data = {u_id:$(this).attr('data-id'),a_status:status}
      	  updateStatus(data);  
        }
	});
}
/**
 * 初始化开关按钮
 */
function initSwitchVisible(){
	$("[name='visible-checkbox']").bootstrapSwitch({		
		onText:"可见",  
        offText:"不可见",  
        onColor:"success",  
        offColor:"warning",  
        size:"small",  
        onSwitchChange:function(event,state){  
          console.log('event,state=',event,state);
          var status;
      	  state ? status=0:status=1;
      	  var data = {u_id:$(this).attr('data-id'),a_status:status}
      	  updateVisible(data);  
        }
	});
}
function initSwitchAttr(){
	$("[name='attr-checkbox']").bootstrapSwitch({		
		onText:"推荐",  
        offText:"不推荐",  
        onColor:"success",  
        offColor:"warning",  
        size:"small",  
        onSwitchChange:function(event,state){  
          console.log('event,state=',event,state);
          var status;
      	  state ? status=0:status=1;
      	  var data = {u_id:$(this).attr('data-id'),a_status:status}
      	  updateAttr(data);  
        }
	});
}

/**
 * 公用的状态值转换为启用还是禁用
 * @param value - 状态值，0或1
 * @param row
 * @param index
 * @returns 0返回启用，1-返回禁用，其他返回未知
 */
function dataFormatterForCommonStatus(value, row, index){
	if(value == '0'){
		return "<span style='color:orange'>启用</span>";
	}else if(value == '1'){
		return "<span style='color:#32CD32'>禁用</span>";
	}else{
		return "<span style='color:black'>未知</span>";
	}
}

//金额处理  
function formatNumber(num) {
  var decimal = String(num).split('.')[1] || '';//小数部分
  var tempArr = [];
  var revNumArr = String(num).split('.')[0].split("").reverse();//倒序
  for (i in revNumArr){
    tempArr.push(revNumArr[i]);
    if((i+1)%3 === 0 && i != revNumArr.length-1){
      tempArr.push(',');
    }
  }
  var zs = tempArr.reverse().join('');//整数部分
  return decimal?zs+'.'+decimal:zs;
}

/**
 * 数字补位，1位数字长的补到2位
 * @param value
 * @returns
 */
function get2Len(value){
	return (value <= 9? "0"+value : value);
}

/**
 * 日期处理 YYYY-MM-dd 年-月-日
 * @param value
 * @returns {String}
 */
function formatDate(value){
	if(!value){
		return "";
	}
	
  var date = new Date(value);
  var y = date.getFullYear();  
  var m = date.getMonth() + 1;  
  m = m < 10 ? ('0' + m) : m;  
  var d = date.getDate();  
  d = d < 10 ? ('0' + d) : d;
  return y + '-' + m + '-' + d ;  
}
/**
 * 日期昨天处理 YYYY-MM-dd 年-月-日
 * @param value
 * @returns {String}
 */
function formatBackDate(value){
	if(!value){
		return "";
	}
	
  var date = new Date();
  date.setDate(date.getDate()-1)
  var y = date.getFullYear();  
  var m = date.getMonth() + 1;  
  m = m < 10 ? ('0' + m) : m;  
  var d = date.getDate();  
  d = d < 10 ? ('0' + d) : d;
  return y + '-' + m + '-' + d ;  
}


/**
 * 时间格式处理  HH:mm:ss
 * @param value 单位s
 * @returns {String}
 */
function formatTimeDetail(value){
	var h = parseInt( value / 3600);
	var m = parseInt(value % 3600 / 60);
	var s = value % 3600 % 60;
	h = h < 10 ? '0'+h : h;
	m = m < 10 ? '0'+m : m;
	s = s < 10 ? '0'+s : s;
	return h + ":" + m + ':' + s;
}

/**
 * 时间格式处理  HH:mm:ss
 * @param value 单位s
 * @returns {String}
 */
function formatTimeDetailLen2(value){
	var h = parseInt( value / 3600);
	var m = parseInt(value % 3600 / 60);
	var s = value % 3600 % 60;
	return get2Len(h) + ":" + get2Len(m) + ':' + get2Len(s);
}

//年-月-日 时：分：秒
function formatDateTime (value,row,index) {
    var date = new Date(value);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    // if(!value){
		// return "";
    // }
    // if(value.indexOf('.0') > -1){
		// value = value.split('.')[0];
		// return value;
    // }
    // var date = new Date(value);
    // var y = date.getFullYear();
    // var m = date.getMonth() + 1;
    // m = m < 10 ? ('0' + m) : m;
    // var d = date.getDate();
    // d = d < 10 ? ('0' + d) : d;
    // var h = date.getHours();
    // h = h < 10 ? ('0' + h) : h;
    // var mi = date.getMinutes();
    // mi = mi < 10 ? ('0' + mi) : mi;
    // var s = date.getSeconds();
    // s = s < 10 ? ('0' + s) : s;
    // return y + '-' + m + '-' + d +' '+ h + ':' + mi + ":" + s;
};

//回放时间 控制宽度
function formatDateTimeWidth (value,row,index) {	
	if(!value){
		return "";
	}
	if(value.indexOf('.0') > -1){
		value = value.split('.')[0];
		return value;
	}
	var date = new Date(value);
    var y = date.getFullYear(); 
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var mi = date.getMinutes();  
    mi = mi < 10 ? ('0' + mi) : mi;
    var s = date.getSeconds();  
    s = s < 10 ? ('0' + s) : s;
    return "<span style='width:150px;display:block;text-align:center;'>"+y+"-"+m+"-"+d+"  "+h+":"+mi+":"+s+"</span>"; 
};

// 年-月-日
function formatDateTimeYYYYMMDD (value,row,index) {	
	if(!value){
		return "";
	}
	if(value.indexOf('.0') > -1){
		value = value.split('.')[0];
		return value;
	}
	var date = new Date(value);
	var y = date.getFullYear(); 
	var m = date.getMonth() + 1;  
	m = m < 10 ? ('0' + m) : m;  
	var d = date.getDate();  
	d = d < 10 ? ('0' + d) : d;  
	return y + '-' + m + '-' + d; 
};

/**
 * 时间格式 CST 转 GMT
 * @param strDate
 * @returns {String}
 */
function getTaskTime(strDate) {	
    if(null==strDate || ""==strDate){
        return "";
    }
    var dateStr=strDate.trim().split(" ");
    var strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";
    var date = new Date(Date.parse(strGMT));
    var y = date.getFullYear();
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();  
    minute = minute < 10 ? ('0' + minute) : minute;
    var second = date.getSeconds();
    second = second < 10 ? ('0' + second) : second;
    
    return y+"-"+m+"-"+d+" "+h+":"+minute+":"+second;
};

//活动时间
function getTaskTimeGood(strDate) {	
    if(null==strDate || ""==strDate){
        return "";
    }
    var dateStr=strDate.trim().split(" ");
    var strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";
    var date = new Date(Date.parse(strGMT));
    var y = date.getFullYear();
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    var minute = date.getMinutes();  
    minute = minute < 10 ? ('0' + minute) : minute;
    
    return y+"-"+m+"-"+d+" "+h+":"+minute;
};
//红包时间显示 (年-月-日-时-分)
function formatRedDateTime (value,row,index) {
	if(!value){
		return "";
	}
	
  var date = new Date(value);
  var y = date.getFullYear();  
  var m = date.getMonth() + 1;  
  m = m < 10 ? ('0' + m) : m;  
  var d = date.getDate();  
  d = d < 10 ? ('0' + d) : d;  
  var h = date.getHours();
  h = h < 10 ? ('0' + h) : h;
  var mi = date.getMinutes();  
  mi = mi < 10 ? ('0' + mi) : mi;
  return y + '-' + m + '-' + d +' '+ h + ':' + mi;  
}; 

//直播回放列表时间
function formatDateTimeHHMMSS (value,row,index) {	
	if(!value){
		return "";
	}
	if(value.indexOf('.0') > -1){
		value = value.split('.')[0];
		return value;
	}
	var date = new Date(value);
    var y = date.getFullYear(); 
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var mi = date.getMinutes();  
    mi = mi < 10 ? ('0' + mi) : mi;
    var s = date.getSeconds();  
    s = s < 10 ? ('0' + s) : s;
    return h + ':' + mi + ":" + s; 
};

//监听下拉框
function reselect(){
	$('#table').bootstrapTable('selectPage',1);
}
//图片上传
function imgUpload(upload){
	upload.init();
	upload.bind('FilesAdded',function(uploader,files){			
		uploader.start(); //开始上传			
	});
	upload.bind('BeforeUpload',function(uploader,file){
		uploader.settings.multipart_params.size = file.size;
		
	});
	upload.bind('UploadComplete',function(uploader,files){
		//console.log("uploadcompleted");
	});
	//绑定文件上传进度事件
	upload.bind('UploadProgress',function(uploader,file){
		$('.progress').show();
		if(file.percent == 100){
			file.percent = 90;
		}
		$('.progress-bar').width(file.percent + '%');
		$('.progress-bar').html(file.percent + '%');//控制进度条
		$('.progress-bar').css('background-color','#5bc0de');
		
	});
	//发生错误
	upload.bind('Error',function(code,file,message){
		if(file.code == -600){
			layer.msg('图片超出规定大小，请重新上传',{icon:0,time:4000})
		}else if(file.message){
			layer.msg(file.code+'：'+file.message,{icon:0,time:4000})
		}else{
			layer.msg(file.status+'：服务发生错误，请联系管理员',{icon:0,time:4000})
		}
		
	});
}

/**
 * 验证表单输入数据
 * @param data
 */
var othertips='',focusflag=false;
function forminvalid(data){

	$('form').bootstrapValidator('disableSubmitButtons', false); 
	var emptytips = '',emailtip = '',phonetip='',lengthtip='',inttip='',urltip='',pwdtip_1='',pwdtip_2='',invalidloginname='',loginnametip='',
		invalidtips='',invalidemail='',invalidphone='',invalidlength='';
	   //判断登录名
	  $.each($('[data-loginname]'),function(i,node){
		  invalidloginname = $('[data-loginname]').val().match(/^[A-Za-z0-9_-]*$/g);
		  if($('[data-loginname]').val()!='' && !invalidloginname){
			  focusflag = isfocus($(this),focusflag);
			  loginnametip='● '+'登录名格式不正确，请重新输入!\n';
			  return false;
		  }
	  })
	  //判断是否为空
	  $.each($('[data-noempty]'),function(i,node){
		  if($(this).val().trim() == ''){
			  focusflag = isfocus($(this),focusflag);
			  var emptytip = $(this).attr('data-noempty');
			  emptytips +='● '+emptytip+'不能为空，请重新输入!'+'\n';
		  }
	  })
	  //判断整数	
	   $.each($('[data-int]'),function(i,node){
		 if(!isInteger(Number($('[data-int]').val()))){
			 focusflag = isfocus($(this),focusflag);
			 var name = $(this).attr('data-int');
			 inttip='● '+name+'只能为整数，请重新输入!\n';
			 return false;
		 }
	  })
	  //判断邮箱格式
	  $.each($('[data-email]'),function(i,node){
		  invalidemail = $('[data-email]').val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
		  if($('[data-email]').val()!='' && !invalidemail){
			  focusflag = isfocus($(this),focusflag);
			  emailtip='● '+'邮箱格式不正确，请重新输入!\n';
			  return false;
		  }
	  })
	  //判断手机格式
	 $.each($('[data-phone]'),function(i,node){
		 invalidphone = $('[data-phone]').val().match(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/);
		 if($('[data-phone]').val()!='' && !invalidphone){
			 focusflag = isfocus($(this),focusflag);
			 phonetip='● '+'手机号码格式不正确，请重新输入!\n';
			 return false;
		 }
	  })
	  //判断网址格式
	  $.each($('[data-url]'),function(i,node){
		  
		  var url=$(this).val().match(/((https|http|ftp|rtsp|mms):\/\/)?(([0-9a-z_!~*'().&=+$%-]+:)?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)/g);
		  if(!url){
			  focusflag = isfocus($(this),focusflag);
			  urltip='● '+'网址格式不正确，请重新输入!\n';
			  return false; 
		  }			  

	  })
	  //判断number长度
	  $.each($('[data-length]'),function(i,node){
		  invalidlength = $(this).attr('data-length');
		 if($('[data-length]').val() > Number(invalidlength)){
			 focusflag = isfocus($(this),focusflag);
			 var name = $(this).attr('data-int');
			 lengthtip='● '+name+'最大不超过'+invalidlength+'，请重新输入!\n';
			 return false;
		 }
	  })
//	  if(typeof $('#up_password input').val()=='string'){
//		  if(/^[a-zA-Z0-9]{6,10}$/.exec($('#up_password input').val())==null || $('#up_password input').val().length < 6){
//			  pwdtip_2='● 密码请输入6-20位数字+字母组合！\n';
//		  }
//	  }
//	  
//	  if($('#up_password input').val() != $('#up_re_password input').val()){
//		  pwdtip_1='● 两次密码输入不一致，请重新输入！\n';
//  	 }
	 if(emptytips!='' || (!invalidemail&&emailtip!='') || (!invalidphone&&phonetip!='') || inttip!='' || pwdtip_1!='' || pwdtip_2!='' || othertips!='' || urltip!='' || loginnametip!='' || lengthtip !=''){
		 invalidtips = loginnametip + emptytips + emailtip + phonetip + lengthtip + inttip + pwdtip_1 + pwdtip_2 + othertips + urltip;
		 alert('提示：\n'+invalidtips);		
	 }
	 
	 if(invalidtips!=''){
   		 return true;
   	 }else{
   		 return false;
   	 } 
}
//是否存在焦点
function isfocus(data,focusflag){
	if(!focusflag){
		data.focus();
		return true;
	}else{
		return true
	}
}
//是否是整数
function isInteger(obj) {
	 return obj%1 === 0
}

//性别 0=男 1=女 2=中性
function formatterSex(value,row,index){
	if(value == 0){
//		return '<span class="text-success">男</span>';
		return '男';
	} else if(value == 1){
//		return '<span class="text-danger">女</span>';
		return '女';
	} else {
		return '-';
	}
}



