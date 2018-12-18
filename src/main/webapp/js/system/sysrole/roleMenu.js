$(function() {
	
	var r_id = $("#r_id").val();
	
	var treeData = getTreeData(r_id);

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
				console.log(1);
			}
		}
	});
});

function getTreeData(r_id){
	$.ajax({
		type:"get",
	    url:"queryrolemenu",
	    data:{
	    	"r_id":r_id
	    },
	    dataType:"json",
	        async:false,
	        success:function(res){
	            if (res.code == 200) {
	            	data = res;
				} else {
					layer.msg(res.message,{icon:2},{time:1000} );
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

//保存
function save(){
	var r_id = $("#r_id").val();
	var objs = $('#treeview').treeview('getChecked','');
	var menuidArray = [];
	for(var i=0; i<objs.length; i++){
		var id = objs[i].id;
		var ids = id.split("_");
		if(ids[0] == 2){//菜单
			menuidArray.push(ids[1]);
		}
	}
	
	$.ajax({
		type:"post",
	    url:"saverolemenu",
	    data:{
	    	"roleid":r_id,
	    	"menuidArray":menuidArray
	    },
	    traditional :true,
	    dataType:"json",
        async:false,
        success:function(res){
        	if (res.code == 200) {
				layer.msg("保存成功！",{icon:1}, {time:2000});
				window.location.href = 'list';
			} else {
				layer.msg(res.message,{icon:2},{time:2000} );
			}
        }
    });
}

//取消
function back(){
	 window.location.href = 'list';
}