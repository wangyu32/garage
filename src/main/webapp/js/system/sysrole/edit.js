$(document).ready(function(){

	//判断  新增 or 编辑
	var id = $("#id").val();
	var title =  $(".title").text();
	if(id != ''){
		$(".title").text("编辑操作员角色");
	}else{
		$(".title").text("增加操作员角色");
	};
	
	//点击取消返回列表页
	$("#back").click(function(){
		 window.location.href = 'list';
		 return false;
	});
	
	//id为空，是添加时查询；id不为空，是修改时查询
	var treeData = getTreeData(id);

	$('#treeview').treeview({
		data : treeData.list,
		showCheckbox : true,
		levels : 1,
		onNodeChecked : function(event, data) {
			$('#treeview').treeview('selectNode', [ data.nodeId, { silent: true } ]);
			
			// 选中父节点，则自动选择子节点
			if (data.nodes != null) {
				var arrayInfo = data.nodes;
				for (var i = 0; i < arrayInfo.length; i++) {
					$('#treeview').treeview('checkNode', [ arrayInfo[i].nodeId, {silent : true} ]);
				}
			}
			
			if (data.parentId != null){
				$('#treeview').treeview('checkNode', [ data.parentId, {silent : true} ]);
			}
		},
		onNodeUnchecked : function(event, data) {
			$('#treeview').treeview('selectNode', [ data.nodeId, { silent: true } ]);
			// 取消选中父节点，则自动取消选择子节点
			if (data.nodes != null) {
				var arrayInfo = data.nodes;
				for (var i = 0; i < arrayInfo.length; i++) {
					$('#treeview').treeview('uncheckNode', [ arrayInfo[i].nodeId, {silent : true} ]);
				}
			}
			
			if (data.parentId != null){
				var parent = $('#treeview').treeview('getParent', data);
				var arrayInfo = parent.nodes;
				var allUnchecked = true;
				for (var i = 0; i < arrayInfo.length; i++) {
					if(arrayInfo[i].state.checked == true){
						allUnchecked = false;
						break;
					}
				}
				if(allUnchecked){
					$('#treeview').treeview('uncheckNode', [ parent.nodeId, {silent : true} ]);
				}
			}
		}
	});
	
	//表单验证不能为空
	$('#form').bootstrapValidator({
        debug : false,
        submitHandler: function (validator, form, submitButton) {
        	
        	var objs = $('#treeview').treeview('getChecked','');
        	var mm_id_array = [];
        	for(var i=0; i<objs.length; i++){
        		var id = objs[i].id;
        		var ids = id.split("_");
        		if(ids[0] == 2){//菜单
        			mm_id_array.push(ids[1]);
        		}
        	}
        	
        	var postData = $('#form').serializeArray();
    		
    		var object1 = new Object;
    		object1.name = "mmIdArray";
    		object1.value = mm_id_array;
    		postData.push(object1);
        	
        	$.post('save', postData ,function( res ){
      	  		if( res.code == 200 ){
      	  			layer.msg(
  	  					res.message,
  	  					{icon:1,time:2000},
  	  					function(){
  	  						window.location.href = 'list';
  	  					}
      	  			);
      	  		}else{
      	  			layer.msg( res.message ,{icon:2,time:2000} );
      	  		}
            },'json');
        },
	});
	 
});


function getTreeData(id){
	$.ajax({
		type:"get",
	    url:"queryrolemenu",
	    data:{
	    	"id":id
	    },
	    dataType:"json",
	        async:false,
	        success:function(res){
	            if (res.code == 200) {
	            	data = res;
				} else {
					layer.msg(res.message,{icon:2},{time:2000} );
				}
	        }
	    });
	return data;
}

function dosome(num){
	if(num == 1){
		$('#treeview').treeview('checkAll', { silent: true });//全选
	}else if(num == 2){
		$('#treeview').treeview('uncheckAll', { silent: true });//全不选
	}else if(num == 3){
		$('#treeview').treeview('collapseAll', { silent: true });//折叠
	}else if(num == 4){
		$('#treeview').treeview('expandAll', { levels: 2, silent: true });//展开所有二级节点
	}
}