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
			ids.push(obj[i].u_id);  
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
	var u_logname           = current.find(".u_logname").val();
	var u_realname          = current.find(".u_realname").val();
	var u_email             = current.find(".u_email").val();
	var u_mobilephone       = current.find(".u_mobilephone").val();
	var u_status            = current.find(".u_status").val();
	
	var sort                = params.sort;
	if(sort == undefined) sort = "u_logname";
	
	$.ajax({
		type:"get",
	    url:"datalist",
	    data:{
	    	"u_logname":u_logname,
	    	"u_realname":u_realname,
	    	"u_email":u_email,
	    	"u_mobilephone":u_mobilephone,
	    	"u_status":u_status,
	    	"sort":sort,
	    	"order":params.order,
	    	"offset":params.offset,
	    	"limit":params.limit,
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
function formatName(value,row,index){
	var id = row.u_id;
	var p_domain = row.p_domain;
    var action = '<a href = "edit?id='+id+'" style="color:#428bca;">' + value + '</a>';
  return action;
};