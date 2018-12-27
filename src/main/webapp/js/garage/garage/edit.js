$(document).ready(function() {

	// 判断 新增 or 编辑
	var id = $("#id").val();
	if (id) {
		$(".title").text("编辑车库");
		$("#phone").attr("readonly","readonly");

	} else {
		$(".title").text("增加车库");
	};
	
	// 点击取消返回列表页
	$("#back").click(function() {
		window.location.href = 'list';
		return false;
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
            url: "save",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(postData),
            dataType: "json",
            success: function(res){
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
