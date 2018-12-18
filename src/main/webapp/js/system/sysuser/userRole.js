$(document).ready(function(){
	
	var dataTable = $('#dataTable');

    //搜索按钮
	$("#search").click(function(){
		$('#dataTable').bootstrapTable('refresh');
	});
 
	//搜索角色
	$("#searchRole").click(function(){
		$('#roleTable').bootstrapTable('refresh');
	});
	
	//增加
    $('#addBtn').click(function(){
//      window.location.href = 'edit';
    	layer.open({
    		type: 1,
    		title: "角色列表",
    		shadeClose: true, //点击遮罩关闭层
    		area: ['800px',  '480px'],
    		content: $("#roleDiv")
    	});
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
			ids.push(obj[i].r_id);  
		};
		
		layer.confirm('您确认禁用该记录？', {
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
							layer.msg("删除成功",{icon:1,time:2000});
							window.location.href = 'list';
						} else {
							layer.msg(res.message,{icon:2,time:2000});
						}
					}
				});
			}
		});
		
	});
    
})

//请求数据
function getData(params){
	var u_id = $("#u_id").val();
	
	$.ajax({
		type:"get",
	    url:"queryuserrole",
	    data:{
	    	"u_id":u_id
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
	var id = row.r_id;
    var action = '<a href = "edit?id='+id+'" style="color:#428bca;">'+value+'</a>';
  return action;
};

//请求数据
function getDataRole(params){
	var r_id = $("#r_id").val();
	var r_name = $("#r_name").val();
	var r_status = $("#r_status").val();
	
	var sort = params.sort;
	if(sort == undefined) sort = "r_name";
	
	$.ajax({
		type:"get",
	    url:"queryrole",
	    data:{
	    	"r_id":r_id,
	    	"r_name":r_name,
	    	"r_status":r_status,
	    	
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

function ajaxRequestRole(params) {
	var json = getDataRole(params.data);
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

