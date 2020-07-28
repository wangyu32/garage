$(document).ready(function(){
	
	var dataTable = $('#dataTable');
    //清除搜索所传参数
    function clearSearch(){
    	$('#list-search').find("input[type='text']").each(function(i){
    		$(this).val('');
    	});
    	$("input[type='radio']").each(function(i){
			$(this).attr("checked",false);
		})
    }
    
    //刷新
    $("#refreshBtn").click(function(){
    	clearSearch();
    	$('#dataTable').bootstrapTable('refresh');
    });

    //搜索按钮
	$("#search").click(function(){
		$('#dataTable').bootstrapTable('refresh');
	});
 
	//增加
    $('#addBtn').click(function(){
        window.location.href = 'edit';
    });
    
    //分配菜单
    $('#menuBtn').click(function(){
    	var obj = $('#dataTable').bootstrapTable('getAllSelections');
		if (obj.length == 0) {
			layer.msg('请至少选择一个条记录！',{icon:0,time:2000});
			return;
		}
		if (obj.length > 1) {
			layer.msg('只能选择一个条记录！',{icon:0,time:2000});
			return;
		}
		var param = "id=" + obj[0].id;
    	window.location.href = 'addrolemenu?' + param;
    });
    
	// 删除按钮
	$("#delBtn").click(function() {
		var obj = dataTable.bootstrapTable('getAllSelections');
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
									{icon:1,time:2000},
									function(){
										window.location.href = 'list';
									}
							);
						} else {
							layer.msg(res.message,{icon:2,time:2000});
						}
					}
				});
			}
		});
	});
    
    // 启用按钮
	$("#enableBtn").click(function() {
		var obj = dataTable.bootstrapTable('getAllSelections');
		if (obj.length == 0) {
			layer.msg('请至少选择一个条记录！',{icon:0,time:2000});
			return;
		}
		if (obj.length > 1) {
			layer.msg('只能选择一个条记录！',{icon:0,time:2000});
			return;
		}
		var param = "id=" + obj[0].id;
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
		var obj = dataTable.bootstrapTable('getAllSelections');
		if (obj.length == 0) {
			layer.msg('请至少选择一个条记录！',{icon:0,time:2000});
			return;
		}
		if (obj.length > 1) {
			layer.msg('只能选择一个条记录！',{icon:0,time:2000});
			return;
		}
		var param = "id=" + obj[0].id;
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
	var name = $("#name").val();
	var status = $("#status").val();
	
	$.ajax({
		type:"get",
	    url:"datalist",
	    data:{
	    	"name":name,
	    	"status":status,
	    	
	    	"sort":params.sort,
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
	var id = row.id;
    var action = '<a href = "edit?id='+id+'" style="color:#428bca;">'+value+'</a>';
  return action;
};




