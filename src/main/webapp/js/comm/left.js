var oldimg = null;

$(function() { 
	var jericho = {
        showLoader: function() {
            $('#divMainLoader').css('display', 'block');
        },
        removeLoader: function() {
            $('#divMainLoader').css('display', 'none');
        },
        buildTree: function() {
            $('.functree li').each(function() {
                    $(this).addClass('collapse');
            });//建立左侧树状解构
            $("li.collapse>span:not('.func')").click(function() {
            	if (oldimg != null){ 
	        		$(oldimg).attr("src",$(oldimg).attr("src").replace("-show.png",".png"));
	        	}
            	
        		$(this).parent().toggleClass('current').siblings().removeClass('current');
        		$("li.collapse>span:not('.func')").removeClass('select');
        		
        		$(this).addClass('select');
        		 oldimg = $(this).find("img");
 	            var imgurl = oldimg.attr("src").replace(".png","-show.png"); 
 	             $(this).find("img").attr("src",imgurl);
 	             
                $(this).parent().siblings('li').find('ul').slideUp();
                $(this).next('ul').slideToggle('fast', function() {
                    //展开完成
                })
            });
            $('span.func').css({ 'cursor': 'pointer',}).hover(function() {
                $(this).css( 'text-decoration','none' );
            }).click(function() {
                $.fn.jerichoTab.addTab({
                    tabFirer: $(this),
                    title: $(this).text(),
                    closeable: true,
                    iconImg: $(this).attr('iconImg'),
                    data: {
                        dataType: $(this).attr('dataType'),
                        dataLink: $(this).attr('dataLink')
                    }
                }).showLoader().loadData();
            });
        },
        buildTabpanel: function() {
            $.fn.initJerichoTab({
                renderTo: '.divRight',
                uniqueId: 'myJerichoTab',
                contentCss: { 'height': $('.divRight').height()-50},
                tabs: [{
                    title: '首页',
                    closeable: false,
                    data: { dataType: 'iframe', dataLink: 'index' },
                    onLoadCompleted: function(h) {
                        //切换完成
                    },
                    loader:'righttag'
                }],
                activeTabIndex: 1,
                loadOnce: true
            });
        }
    };
    $().ready(function() {
    	
        d1 = new Date().getTime();
        jericho.showLoader();
        var w = $(document).width();

        $('.divLeft').css({ width: 230,  'display': 'block' });
        $('.divRight').css({ width: w - 230, 'display': 'block'});

        jericho.buildTree();
        jericho.buildTabpanel();
        jericho.removeLoader();

        $('.func').click(function(event) {
        	$('.func').css('color','#9a9999');
        	$(this).css('color','#428bca');
            $('.func s').removeClass('show');
            $(this).children('s').addClass('show');
        });
        $('.admin').mouseover(function(event) {
            $('.admin ul').css('display', 'block');
        }).mouseout(function(event) {
            $('.admin ul').css('display', 'none');
        });
        $('#home').click();
        $('.con-top-right .message').click(function(event) {
            $('#message').click();
        });//弹出层消息界面 
        $('#exit').click(function(){
        	layer.confirm('你确定要退出吗？', {
        	  title: ['退出系统', 'font-size:16px;'],
        	  btn: ['确定','取消'] //按钮
        	}, function(){
        		location.href="./logout";
        	}, function(){
        	  layer.msg({
        	    time: 1000, //1s后自动关闭
        	  });
        	});
        });//退出事件
        $('.closed').mouseover(function(event) {
        	$('.closed').children('s').addClass('current');
            $('.closed ul').css('display', 'block');
        }).mouseout(function(event) {
        	$('.closed').children('s').removeClass('current');
        	$('.closed ul').css('display', 'none');
        });//关闭按钮弹窗出现
        var interval1;
        $('#closeAll').click(function () {
            $('.tab_close a').mouseup();
        });//关闭所有
        $('#closeCurrent').click(function () {
            $('.tab_selected .tab_close>a').mouseup();
        });//关闭当前
        $('#loadCurrent').click(function () {
        	top.frames[$('.curholder iframe').attr("name")].location.reload();
        });//刷新当前
        $('#alert').click(function(event) {
        	$('#alert').removeClass("none");
        	var index = layer.open({
        		title: ['关于...', 'font-size:16px;'],
        		type: 1,
        		area: ['700px', '360px'], //宽高
        		shadeClose: true,
                content: $('#divabout')
            });
        	//layer.style(index,{width:'1000px'});
        });
        $('#editpas').click(function(event) {
        	$('#editpas').removeClass("none");
        	$("#currentPas").val("");
			$("#newPas").val("");
			$("#confirmPas").val("");
			$("#text").html("");
        	layer.open({
        		title: ['修改密码', 'font-size:16px;'],
        		type: 1,
        		area: ['450px', '250px'], //宽高
        		shadeClose: true,
        		content: $('#changepwd')
            });
        });
        
    });
   function funcHeight(){
	   var h = $(document).height();
       $('.functree').css('height',h-61);
   }
    	
   funcHeight();
    $(window).resize(function() {
        var w = $(document).width();
        $('.divRight').css({ width: w - 230});
        funcHeight();
    });
    
});

//修改密码
function changePassword() {
	if($("#currentPas").val() == "") {
		$("#text").text("请输入当前密码！");
		return;
	}
	if($("#newPas").val() == "") {
		$("#text").text("请输入1~16个字符的新密码！");
		return;
	} else {
		if($("#newPas").val().indexOf(" ") > -1) {
			$("#text").text("新密码不允许有空格，请重新输入！");
			return;
		}
	}
	if($("#confirmPas").val() != $("#newPas").val()) {
		$("#text").text("两次输入的新密码不一致，请重新输入！");
		return;
	}
	 $.ajax({
			async:false,
			url:"user/updatepassword",
			data:{
				"oldPassword":$("#currentPas").val(), 
				"newPassword":$("#newPas").val(),
				"newPasswordConfirm":$("#confirmPas").val(),
				},
			type:"post",
			cache: false,			
			dataType:"json",
			success:function(json){
				if(json.code==200) {
					layer.closeAll();
					layer.msg(json.message,{icon:1,time:2000});
				} else {
					$("#text").text(json.message);
				}
			}
	 }); 
}
    
