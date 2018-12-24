$(document).ready(function() {

	// 判断 新增 or 编辑
	var id = $("#u_id").val();
	if (id) {
		$(".title").text("编辑操作员");
		$("#up_password").hide();//编辑时，隐藏密码行
		$("#u_password").val("1");//防止校验出错
	} else {
		$(".title").text("增加操作员");
		$("#repassword").hide();//增加时，隐藏重设密码按
	};
	
	// 点击取消返回列表页
	$("#back").click(function() {
		window.location.href = 'list';
		return false;
	});

	//重设密码
	var passHTML = '<div class="form-group clearfix pass1">\
					    <label class="col-sm-4 control-label text-right">新密码：</label>\
					    <div class="col-sm-8">\
					      <input type="password" class="form-control" placeholder="" id="repassone" name="repassone" value="">\
					    </div>\
					</div>\
					<div class="form-group clearfix pass2">\
					    <label class="col-sm-4 control-label text-right">确认密码：</label>\
					    <div class="col-sm-8">\
					      <input type="password" class="form-control" placeholder="" id="repasstwo" name="repasstwo" value="">\
					    </div>\
					</div>\
					<small style="display:none;padding-left: 80px;" class="text-danger passone">请输入新密码</small>\
					<small style="display:none;padding-left: 80px;" class="text-danger passtow">两次输入不一致，请重新输入！</small>';
	
	$('#repassword').click(function(){
		layer.confirm(
			passHTML,
			{title: '重设密码', 
			  btn: ['保存','取消'], 
			  yes: function(index,layero){
				  	$('.passone').hide();
			  		$('.passtow').hide();
			  		
				  	if($('#repassone').val() == ''){
				  		$('.passone').show();
				  		return;
				  	}
				  	
				  	if($('#repassone').val() == $('#repasstwo').val()){
				  		$('.passone').hide();
				  		$('.passtow').hide();
						$.ajax({
							url:'resetpassword',
							type:'post',
							dataType:'json',
							data:{
								u_id:$('input[name="u_id"]').val(),
								u_password:$('#repasstwo').val()
							},
							success:function(json){
								layer.msg('保存成功', {icon: 1,time:2000});
								layer.close(index);
							},
							error:function(err){
								layer.msg(err, {icon: 2,time:2000});
							}
						})
					}else{
						$('.passone').hide();
						$('.passtow').show();
					}
			  	}
			}
		);
	});
	
	// 表单验证不能为空
//	$('#form').bootstrapValidator({
//		debug : false,
//		submitHandler : function(validator, form, submitButton) {
//			save();
//		}
//	});

	$('#save').click(function() {
		othertips='';
		if(getRoleIdsArray().length == 0){
			othertips +='● 请至少选择一个角色！\n';
		}
		if(forminvalid()){
    		return;
    	}
		save();
	});
	
	// 保存
	function save() {
		var postData = $('#form').serializeArray();
		
		var r_id_array = getRoleIdsArray();
		var object1 = new Object;
		object1.name = "r_id_array";
		object1.value = r_id_array;
		postData.push(object1);
		
		$.post('save', postData, function(res) {
			if (res.code == 200) {
				layer.msg(
						res.message, 
						{icon : 1, time:2000},
						function(){
							window.location.href = 'list';
						}
				);
			} else {
				layer.msg(res.message, {icon : 2, time:2000});
			}
		}, 'json');
	}
});

//分配角色
//获取选择的角色id,数组形式
function getRoleIdsArray(){
	var ids = [];
	var obj = $("#roleTable").bootstrapTable('getAllSelections');
	if(obj.length > 0){
		for(var i = 0; i<obj.length; i++){
			var r_id = obj[i].r_id;
			ids.push(r_id);
		}
	}
	return ids;
}

//请求数据
function getData(params){
	var u_id = $("#u_id").val();
	$.ajax({
		type:"get",
	    url:"queryuserrolechecked",
	    data:{
	    	"u_id":u_id,
//	    	"r_status":"0",
	    	"sort":params.sort,
	    	"order":params.order,
	    },
	    dataType:"json",
	        async:false,
	        success:function(res){
	            data = res;
	        }
	    });
	return data;
}

function ajaxRequest(params) {
	var json = getData(params.data);
	var code = json.code;
	var message = json.message;
	if(code == 200){
		params.success({
		// 分配数据
			total : json.total,
			rows : json.list
		});
	} else {
		layer.msg(message,{icon:2,time:2000});
		return;
	}
}

//勾选
function formatChecked(value,row,index){
	if(row.checked){
		$(this).prop("checked","checked");
	} else {
		$(this).attr('checked',false);
	}
	return this;
};
