$(document).ready(function() {

	// 判断 新增 or 编辑
	var id = $("#id").val();
	if (id) {
		$(".title").text("编辑用户");
		$("#up_password").hide();//编辑时，隐藏密码行
		$("#u_password").val("");//防止校验出错
		$("#phone").attr("readonly","readonly");

	} else {
		$(".title").text("增加用户");
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
					<small style="display:none;padding-left: 80px;" class="text-danger passone">请输入新密码</small>';

	$('#repassword').click(function(){
		layer.confirm(
			passHTML,
			{title: '重设密码', 
			  btn: ['保存','取消'], 
			  yes: function(index,layero){
				  	$('.passone').hide();

				  	if($('#repassone').val() == ''){
				  		$('.passone').show();
				  		return;
				  	}
				  	
				  		$('.passone').hide();

				  	    var postData = {
                            id:$('input[name="id"]').val(),
                            password:$('#repassone').val(),
                        };

						$.ajax({
							url:'resetpassword',
							type:'post',
                            contentType: "application/json; charset=utf-8",
							dataType:'json',
                            data: JSON.stringify(postData),
							success:function(res){
                                if (res.code == 0) {
                                    layer.msg(
                                        res.message,
                                        {icon : 1, time:2000},
                                        function(){
								            layer.close(index);
                                        }
                                    );
                                } else {
                                    layer.msg(res.message, {icon : 2, time:2000});
                                }
							},
							error:function(err){
								layer.msg(err, {icon: 2,time:2000});
							}
						})
			  	}
			}
		);
	});
	
	$('#save').click(function() {
		othertips='';
		if(forminvalid()){
    		return;
    	}
		save();
	});
	
	// 保存
	function save() {
		var postData = $('#form').serializeJSON();

        $.ajax({
            type: "POST",
            url: "register",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(postData),
            dataType: "json",
            success: function(res){
                if (res.code == 0) {
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
            } //可选参数
        });
/**
		$.post('register', postData, function(res) {
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
 */
	}
});

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
