$(document).ready(function(){
	
    var table = $('#dataTable');
	var searchInput = $('#search-input');
	var searchMore = $("#search-more-wrapper");

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


})
//请求数据
function getData(params){
	var current             = $('.search-current');
	var name                = current.find(".name").val();
	var sex                 = current.find(".sex").val();
	var phone               = current.find(".phone").val();
	var type                = current.find(".type").val();
	var status              = current.find(".status").val();
    var intimeStart         = current.find(".intimeStart").val();
    var intimeEnd           = current.find(".intimeEnd").val();
    var outtimeStart        = current.find(".outtimeStart").val();
    var outtimeEnd          = current.find(".outtimeEnd").val();

	var sort                = params.sort;
	if(sort == undefined) sort = "intime";
	
	$.ajax({
		type:"get",
	    url:"datalist",
	    data:{
	    	"name":name,
	    	"sex":sex,
	    	"phone":phone,
	    	"type":type,
	    	"status":status,
	    	"intimeStart":intimeStart,
	    	"intimeEnd":intimeEnd,
	    	"outtimeStart":outtimeStart,
	    	"outtimeEnd":outtimeEnd,
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

function formatterStatus(value, row, index) {
    if(value == 0){
        var s = "<span style='color:#FF0000;'> 已入库</span>";
    	// return "已入库";
    	return s;
    }else if(value == 1){
        return '已出库';
    }else{
		return "-";
	}
};

function formatterPrice(value, row, index) {
    if(!row.outtime){
    	return "-";
    }
    return value + "元";
};

function formatOutTime(value, row, index) {
	if(!row.outtime){
		return "-";
	} else {
		return formatDateTime(value, row, index);
    }
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