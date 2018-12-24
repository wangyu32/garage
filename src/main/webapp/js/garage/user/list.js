$(document).ready(function(){
	
    var table = $('#dataTable');
	var searchInput = $('#search-input');
	var searchMore = $("#search-more-wrapper");

	//增加
    $('#addBtn').click(function(){
        window.location.href = 'edit';
    });

    //增加
    $('#roleBtn').click(function(){
    	var obj = $('#dataTable').bootstrapTable('getAllSelections');
		if (obj.length == 0) {
			layer.msg('请至少选择一个条记录！',{icon:0,time:2000});
			return;
		}
		if (obj.length > 1) {
			layer.msg('只能选择一个条记录！',{icon:0,time:2000});
			return;
		}
		var param = "u_id=" + obj[0].u_id;
    	window.location.href = 'adduserrole?' + param;
    });
    
    // 删除按钮
	$("#delBtn").click(function() {
		var obj = $('#dataTable').bootstrapTable('getAllSelections');
		if (obj.length == 0) {
			layer.msg('请至少选择一个条记录！',{icon:0,time:2000});
			return;
		}
		
		//数组
		var ids = new Array();  
		for (var i = 0; i < obj.length; i++) {
			ids.push(obj[i].id);
		};
		
		layer.confirm('您确认删除记录？', {
			btn : [ '确认', '取消' ],
			yes : function(index, layero) {
				$.ajax({
					type : 'post',
					url : "delete",
					traditional :true, //传统方式
					data:{'ids': ids},
					dataType : "json",
					success : function(res) {
						if (res.code == 200) {
							layer.msg(
								"删除成功",
								{icon:1, time:2000},
								function(){
									window.location.href = 'list';
								}
							);
						} else {
							layer.msg(res.message,{icon:2, time:2000});
						}
					}
				});
			}
		});
	});
    
    //展示数据
	function refresh(){
		table.bootstrapTable('refresh');
	}
	
	//拷贝数据
	function transferData(from,to){
		var fromObj = from.find('input,select');
		var toObj   = to.find('input,select');
		fromObj.each(function(){
			var name = $(this).attr('name');
			var tmp = to.find('[name="'+name+'"]');
			if(tmp){
				tmp.val($(this).val());
			}
		});
	}
	
	//搜索按钮
	$(".search-btn").click(function(){
		table.bootstrapTable('refresh');
		if( $('#search-more-wrapper').hasClass("search-current") ){
			transferData(searchMore,searchInput);
			layer.closeAll();
		}
	});
	
	//点击更多按钮
	$('#more').click(function(){
		searchMore.addClass('search-current');
		searchInput.removeClass('search-current');
		transferData(searchInput,searchMore);
		layer.open({
			type: 1,
			title: "更多搜索",
			shadeClose: true, //点击遮罩关闭层
			area: ['600px',  '270px'],
			content: searchMore,
			end:function(){
				searchMore.removeClass('search-current');
				searchInput.addClass('search-current');
			}
		});
	});
    
    // 启用按钮
	$("#enableBtn").click(function() {
		var obj = table.bootstrapTable('getAllSelections');
		if (obj.length == 0) {
			layer.msg('请至少选择一个条记录！',{icon:0,time:2000});
			return;
		}
		if (obj.length > 1) {
			layer.msg('只能选择一个条记录！',{icon:0,time:2000});
			return;
		}
		var param = "u_id=" + obj[0].u_id;
		$.ajax({
			type : 'post',
			url : "enable?" + param,
			dataType : "json",
			success : function(res) {
				if (res.code == 200) {
					layer.msg(res.message,{icon:1,time:2000} );
					$('#dataTable').bootstrapTable('refresh');
				} else {
					layer.msg(res.message,{icon:2,time:2000} );
				}
			}
		});
	});
	
	// 禁用按钮
	$("#disableBtn").click(function() {
		var obj = table.bootstrapTable('getAllSelections');
		if (obj.length == 0) {
			layer.msg('请至少选择一个条记录！',{icon:0,time:2000});
			return;
		}
		if (obj.length > 1) {
			layer.msg('只能选择一个条记录！',{icon:0,time:2000});
			return;
		}
		var param = "u_id=" + obj[0].u_id;
		$.ajax({
			type : 'post',
			url : "disable?" + param,
			dataType : "json",
			success : function(res) {
				if (res.code == 200) {
					layer.msg(res.message,{icon:1,time:2000} );
					$('#dataTable').bootstrapTable('refresh');
				} else {
					layer.msg(res.message,{icon:2,time:2000} );
				}
			}
		});
	});

})
//请求数据
function getData(params){
	var current             = $('.search-current');
	var name                = current.find(".name").val();
	var sex                 = current.find(".sex").val();
	var phone               = current.find(".phone").val();
	var type                = current.find(".type").val();
    var startTime           = current.find(".startTime").val();
    var endTime             = current.find(".endTime").val();

	var sort                = params.sort;
	if(sort == undefined) sort = "createtime";
	
	$.ajax({
		type:"get",
	    url:"datalist",
	    data:{
	    	"name":name,
	    	"sex":sex,
	    	"phone":phone,
	    	"type":type,
	    	"startTime":startTime,
	    	"endTime":endTime,
	    	"sort":sort,
	    	"order":params.order,
	    	"offset":params.offset,
	    	"limit":params.limit,
	    	"pageNumber": params.offset / params.limit + 1,
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
		layer.msg(message,{icon:2,time:2000} );
		return;
	}
}

//编辑
function formatName(value, row, index) {
    var id = row.id;
    var action = '<a href = "edit?id=' + id + '" style="color:#428bca;">' + value + '</a>';
    return action;
};

function formatterPrice(value, row, index) {
    return value + "元";
};

//用户类型
function formatterType(value,row,index){
    if(value == 0){
        return '临时用户';
    } else if(value == 1){
        return '会员';
    } else if(value == 2){
        return '管理员';
    } else {
        return '-';
    }
}