$(function() {
	//分钟数
	var min = 1;
	//定时器维持session
	window.setInterval(holdSession, 1000 * 60 * min);

	var coin = $("#coin").text();
	
	//请求卡片数据
	function topdata(){
		$.ajax({
			type:"get",
		    url:"reportlist",
		    dataType:"json",
	        async:false,
	        success:function(res){
	            var data = res.data;
	            if(res.code==200){
	            	var content = '<div>\
										<p>今日新增\
											<span class="usernum">用户数</span>|<span class="anchornum">主播数</span>\
	            						</p><h6></h6>\
										<h5><span class="showdata usernum">'+formatNum(data.addusercount)+'</span> | <span class="showdata anchornum">'+formatNum(data.addanchorcount)+'</span></h5>\
									</div>\
									<div>\
										<p>累计\
										<span class="usersnum">用户数</span>|<span class="anchorsnum">主播数</span>\
										</p>\
										<h6></h6>\
										<h5><span class="showdata usersnum">'+formatNum(data.totaluser)+'</span> | <span class="showdata anchorsnum">'+formatNum(data.totalanchor)+'</span></h5>\
									</div>\
									<div>\
										<p class="chareven">今日充值数（'+coin+'）</p>\
										<h6></h6>\
										<h5><span class="showdata todayrecharge">'+formatNum(data.recharge)+'</span></h5>\
									</div>\
									<div>\
										<p class="chareven">今日消费数（'+coin+'）</p>\
										<h6></h6>\
										<h5><span class="showdata todaycomputation">'+formatNum(data.consumption)+'</span></h5>\
									</div>\
									<div>\
										<p class="chareven">用户总余额（'+coin+'）</p>\
										<h6></h6>\
										<h5><span class="showdata userscash">'+formatNum(data.usertotalbalance)+'</span></h5>\
									</div>\
									<div>\
										<p>今日直播\
										<span class="livenum">数量</span>|<span class="livelong">时长</span>\
										</p>\
										<h6></h6>\
										<h5><span class="showdata livenum">'+formatNum(data.livecount)+'</span> | <span class="showdata livelong">'+formatTimeDetail(data.liveduration)+'</span></h5>\
									</div>\
									<div>\
										<p class="chareven">本日提现申请</p>\
										<h6></h6>\
										<h5><span class="showdata userscash">'+formatNum(data.cashapply)+'</span></h5>\
									</div>\
									<div>\
										<p class="chareven">本日主播申请</p>\
										<h6></h6>\
										<h5><span class="showdata userscash">'+formatNum(data.addanchorapply)+'</span></h5>\
									</div>';
					  $('.leftdata>.caption').append(content);
					  $('.leftdata>.caption div').each(function(){
						  if($(this).find('.showdata').text() == 'undefined'){
							  $(this).remove();
						  }
					  })
	            }
	        }
		});
	}
//	topdata();
	
	var detailsize = $('.caption div').size();
	$('.caption>div').css('width',(92/detailsize).toFixed(2)+'%' );

	//star 图表
	var firstChart = echarts.init(document.getElementById('firstT')),
	    secondChart = echarts.init(document.getElementById('secondT')),
		thirdChart = echarts.init(document.getElementById('thirdT')),
		forthChart = echarts.init(document.getElementById('forthT')),
		fiveChart = echarts.init(document.getElementById('fiveT'));

	var month = new Date().getMonth()+1,times=-1,dateList=[],newyear = new Date().getFullYear();
	//X轴数据
	function chooseday(index){
		month = new Date().getMonth()+1,times=-1,dateList=[];
		for(var i = new Date().getDate();i>-1;i--){
			times++;
			if(times==index){
				break;
			}
			if(i==0){
				if(month==1){
					month = 12;
				}else{
					month = month-1;
				} 
				if([1,3,5,7,8,10,12].indexOf(month) > -1){
					i = 31;
				}else if([4,6,9,11].indexOf(month) > -1){
					i = 30;
				}else if(month==2 && (newyear%400==0 || (newyear%4==0 && newyear%100!=0))){
					i=29;
				}else{
					i=28;
				}
			}
			if(index==7){
				dateList.unshift(month+'月'+i+'日');	
			}else{
				dateList.unshift(month+'.'+i);	
			}
				
		}
	}
	chooseday(7);	
	

	//默认新增用户 走势
	var newadduser = [];
	function adduser(params){
		newadduser = [];
		$.ajax({
			type:"get",
		    url:"newaddusers",
		    data:params,
		    dataType:"json",
		    async:false,
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		newadduser.push(da.count)
	            	})
	            }
	        }
		});	
	}
//	adduser({day:7});
	
	//默认直播时长 走势
	var livelongs = [];
	function livelong(params){
		livelongs = [];
		$.ajax({
			type:"get",
		    url:"totalliveduration",
		    data:params,
		    dataType:"json",
		    async:false,
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		livelongs.push((da.count/3600).toFixed(2))
	            	})
	            }
	        }
		});	
	}
	livelong({day:7});
	
	//默认用户充值走势
	var userrecharge = [];
	function charge(params){
		userrecharge = [];
		$.ajax({
			type:"get",
		    url:"userrecharge",
		    data:params,
		    dataType:"json",
		    async:false,
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		userrecharge.push(da.count)
	            	})
	            }
	        }
		});
	}
//	charge({day:7});
	
	
	//默认充值占比
	var rechargeproportion = {},rechargearry1=[],rechargearry2=[];
	function proportion(params){
		rechargeproportion = {},rechargearry1=[],rechargearry2=[];;
		$.ajax({
			type:"get",
		    url:"rechargeproportion",
		    data:params,
		    dataType:"json",
		    async:false,
	        success:function(res){
	            if(res.code==200){
	            	rechargeproportion = res.data;
	            	if(rechargeproportion.alipay!==0){
	            		rechargearry1.push({value:rechargeproportion.alipay, name:'支付宝支付'})
	            	}
	            	if(rechargeproportion.wechatpay!==0){
	            		rechargearry1.push({value:rechargeproportion.wechatpay, name:'微信支付'})
	            	}
	            	if(rechargeproportion.applepay!==0){
	            		rechargearry1.push({value:rechargeproportion.applepay, name:'苹果支付'})
	            	}	
	            	if(rechargeproportion.backstage!==0){
	            		rechargearry1.push({value:rechargeproportion.backstage, name:'后台'})
	            	}
	            	//充值渠道
	            	if(rechargeproportion.app!==0){
	            		rechargearry2.push({value:rechargeproportion.app, name:'APP'})
	            	}
	            	if(rechargeproportion.pc!==0){
	            		rechargearry2.push({value:rechargeproportion.pc, name:'网页(PC)'})
	            	}/*
	            	if(rechargeproportion.wap!==0){
	            		rechargearry2.push({value:rechargeproportion.wap, name:'网页(wap)'})
	            	}*/
	            	if(rechargeproportion.public_number!==0){
	            		rechargearry2.push({value:rechargeproportion.public_number, name:'公众号'})
	            	}
	            }
	        }
		});
	}
	//充值占比
//	proportion({day:7});

	//默认用户消费走势
	var userconsumption = [];
	function consumption(params){
		userconsumption = [];
		$.ajax({
			type:"get",
		    url:"userconsumption",
		    data:params,
		    dataType:"json",
		    async:false,
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		userconsumption.push(da.count)
	            	})
	            }
	        }
		});
	}
//	consumption({day:7});
	
	//用户消费排名
	function userconsume(params){
		var consumedata = '';
		$('#consumerank').html('');
		$.ajax({
			type:"get",
		    url:"userconsumptionranking",
		    data:params,
		    dataType:"json",
	        success:function(res){
	            if(res.code==200){
	            	
	            	$.each(res.list,function(i,da){
	            		consumedata+= '<tr>\
					            			<td width="15%">'+(i+1)+'</td>\
											<td width="50%">'+da.nickname+'</td>\
											<td width="35%"><span>'+formatNum(da.count)+'</span></td>\
										</tr>';
	            	});
	            	$('#consumerank').append(consumedata);
	            	
	            	//数据更多            	
	            	var datasize = $('#consumerank tr').size();
	            	if(datasize > 7){
	            		$('#consumerank tr:gt(7)').remove();
	                        
	            	}
	            }else{
	            	layer.msg( '数据获取失败',{icon:2,time:2000} );
	            }
	        }
		});
	}
//	userconsume({"limit":8,"offset":0,day:7});
	
	//热门直播
	function hot_live(params){
		var hotlivedata = '';
		$('#hotlive').html('');
		$.ajax({
			type:"get",
		    url:"hotlive",
		    data:params,
		    dataType:"json",
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		hotlivedata+= '<tr>\
											<td width="35%"><ul><li><span title="'+da.title+'">'+da.title+'</span></li></ul></td>\
											<td width="20%"><ul><li><span title="'+da.nickname+'">by '+da.nickname+'</span></li></ul></td>\
											<td width="15%"><span>'+selfdate(da.time)+'</span></td>\
											<td width="20%"><span>'+formatNum(da.count)+'次观看</span></td>\
										</tr>';
	            	});
	            	$('#hotlive').append(hotlivedata);
	            	
	            	//数据更多            	
	            	var datasize = $('#hotlive tr').size();
	            	if(datasize > 7){
	            		$('#hotlive tr:gt(7)').remove();
//	            		$('#hotlive').append('<a href="user/list">查看更多</a>');
	                        
	            	}
	            }else{
	            	layer.msg( '数据获取失败',{icon:2,time:2000} );
	            }
	        }
		});
	}
	hot_live({"limit":8,"offset":0,day:7})
	
	//热门回放
	function hot_back(params){
		var hotrecordata = '';
		$('#hotreback').html('');
		$.ajax({
			type:"get",
		    url:"hotrecord",
		    data:params,
		    dataType:"json",
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		hotrecordata+= '<tr>\
					            			<td width="35%"><ul><li><span title="'+da.title+'">'+da.title+'</span></li></ul></td>\
											<td width="20%"><ul><li><span title="'+da.nickname+'">by '+da.nickname+'</span></li></ul></td>\
											<td width="15%"><span>'+selfdate(da.time)+'</span></td>\
											<td width="20%"><span>'+formatNum(da.count)+'次观看</span></td>\
										</tr>';
	            	})
	            	$('#hotreback').append(hotrecordata);
	            	//数据更多            	
	            	var datasize = $('#hotreback tr').size();
	            	if(datasize > 7){
	            		$('#hotreback tr:gt(7)').remove();
	                        
	            	}
	            }else{
	            	layer.msg( '数据获取失败',{icon:2,time:2000} );
	            }
	        }
		});
	}
	hot_back({"limit":8,"offset":0,day:7})
	
	//主播时长排名
	function anchor_long(params){
		var anchorrankdata = '';
		$('#anchorlong_ranking').html('');
		$.ajax({
			type:"get",
		    url:"anchorranking",
		    data:params,
		    dataType:"json",
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		anchorrankdata+= '<tr>\
											<td width="15%">'+(i+1)+'</td>\
											<td width="50%">'+da.nickname+'</td>\
											<td width="35%"><span>'+formatTimeDetail(da.duration)+'</span></td>\
										</tr>';
	            	})
	            	$('#anchorlong_ranking').append(anchorrankdata);
	            	//数据更多            	
	            	var datasize = $('#anchorlong_ranking tr').size();
	            	if(datasize > 7){
	            		$('#anchorlong_ranking tr:gt(7)').remove();
	                        
	            	}
	            }else{
	            	layer.msg( '数据获取失败',{icon:2,time:2000} );
	            }
	        }
		});
	}
	anchor_long({"limit":8,"offset":0,day:7})
	
	//主播收入排名
	function anchor_income(params){
		var rechargeincomerank = '';
		$('#anchorincome_ranking').html('');
		$.ajax({
			type:"get",
		    url:"anchorincomeranking",
		    data:params,
		    dataType:"json",
	        success:function(res){
	            if(res.code==200){
	            	$.each(res.list,function(i,da){
	            		rechargeincomerank+= '<tr>\
												<td width="15%">'+(i+1)+'</td>\
												<td width="50%">'+da.nickname+'</td>\
												<td width="35%"><span>'+formatNum(da.count)+'</span></td>\
											</tr>';
	            	})
	            	$('#anchorincome_ranking').append(rechargeincomerank);
	            	//数据更多            	
	            	var datasize = $('#anchorincome_ranking tr').size();
	            	if(datasize > 7){
	            		$('#anchorincome_ranking tr:gt(7)').remove();
	                        
	            	}
	            }else{
	            	layer.msg( '数据获取失败',{icon:2,time:2000} );
	            }
	        }
		});
	}
	anchor_income({"limit":8,"offset":0,day:7});	
	//折线图
    var option = {
//    		  title: {
//    		       text: firstTitle,
//    		       x: "left",
//    		       textStyle:{
//    		    	   fontSize:14  
//    		       }
//    		   },
    		   tooltip: {
    		       trigger: "item",
    		       formatter: "{a} <br/>{b} : {c}"
    		   },
    		   xAxis: [
    		       {
    		    	   type : 'category',
    		           splitLine: {
    		        	   show: true,
    		    		   lineStyle:{
    		    			    color: ['#dcdde1'],
    		    			    width: 1,
    		    			    type: 'solid'
    		    			}
    		        	},
    		           boundaryGap: true,
        			   splitNumber:6,
    		           data: dateList,
        			   axisLabel:{
        				   interval:0,
        				   rotate:0
        			   },
        			   axisLine:{
        				   lineStyle:{
        					   type:'solid',
        					   color: '#fff',
        				   }
        			   },
        			   axisTick:{
        				   lineStyle:{
        					   color:'#fff'
        				   }
        			   },
        			   splitArea:{
        				   color: [
        				           'rgba(250,250,250,0.3)',
        				           'rgba(200,200,200,0.3)'
        				       ]
        			   }
        			   
    		       }
    		   ],
    		    grid: { // 控制图的大小，调整下面这些值就可以，
    	             x: 60,
    	             y:30,
    	             x2: 40,
    	             y2: 50,
    	         },
    		   yAxis: [
    		       {
    		    	   type : 'value',
    		    	   splitLine:{
    		    		   show:true,
    		    		   lineStyle:{
    		    			    color: ['#dcdde1'],
    		    			    type: 'dashed'
    		    			} 
    		    	   },
    		           name: $('#coin').text(),
    		           nameTextStyle:{
    		        	   color:'#000'
    		           },
    		           splitNumber:6,
    		           axisLabel:{
        				   interval:0
        			   },
        			   axisLine:{
        				   lineStyle:{
        					   type:'solid',
        					   color: '#dcdde1',
        				   }
        			   },
        			   axisTick:{
        				   lineStyle:{
        					   color:'#fff'
        				   }
        			   }
        			   
    		       }
    		   ],
    		   calculable: false,
    		   series: [
    		       {
    		           name: "用户充值",
    		           type: "line",
    		           data: userrecharge,
    		           itemStyle : {  
                           normal : {
                        	   color:'#6495ed',
                        	   areaStyle:{
                        		   color:(function (){
                                       var zrColor = zrender.tool.color;
                                       return zrColor.getLinearGradient(
                                           0, 100, 0, 400,
                                           [[0, 'rgba(212,242,253,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
                                       )
                                   })(),
                                   shadowColor : 'rgba(0,0,0,0.5)',
                                   shadowBlur: 10,
                                   shadowOffsetX: 8,
                                   shadowOffsetY: 8
                        	   }
                           }  
                       }

    		       }
    		   ]
    }
    //饼状图
    var option4 = {
//    	    title : {
//    	        text: '支付方式',
//    	        x:'left',
// 		       textStyle:{
//		    	   fontSize:14,
//		    	   fontWeight:'normal'  
//		       }
//    	    },
    	    color:['#ff7f50', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
    	           '#ff69b4', '#ba55d3', '#40e0d0', '#ffa500', '#30e0e0'],
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        orient : 'horizontal',
    	        x : 'center',
    	        y:'bottom',
    	        itemWidth:10,
    	        itemHeight:10,
    	        data:['支付宝支付','微信支付','苹果支付','后台','APP','网页(PC)','公众号']/*'网页(wap)',*/
    	    },
    	    calculable : false,
    	    series : [
    	        {
    	            name:'支付方式',
    	            type:'pie',
    	            radius : '40%',
    	            center: ['28%', '50%'],
    	    	    itemStyle: {
    	    	        normal: {
    	    	            label:{
    		            	    show: true,
    		            	    formatter:'{c}',
    		            	    textStyle:{
    		            	    	fontSize:8,
    		            	    	color:'#000'
    		            	    }
    	    	            },
			    	        labelLine: {
			    	            show: true,
			    	            length:15,
			    	            lineStyle:{
			    	            	color:'#cacbcf'
			    	            }
			    	        }   	            
    	    	        }
    	    	    },
    	            data:rechargearry1
    	        },
    	        {
    	            name:'充值渠道',
    	            type:'pie',
    	            radius : '40%',
    	            center: ['75%', '50%'],
    	            itemStyle: {
    	    	        normal: {
    	    	            label:{
    		            	    show: true,
    		            	    formatter:'{c}',
    		            	    textStyle:{
    		            	    	fontSize:8,
    		            	    	color:'#000'
    		            	    }
    	    	            },
			    	        labelLine: {
			    	            show: true,
			    	            length:15,
			    	            lineStyle:{
			    	            	color:'#cacbcf'
			    	            }
			    	        }
    	    	        }
    	    	    },
    	            data:rechargearry2
    	        }
    	    ]
    	};
                 
	firstChart.setOption(option);

	option.yAxis[0].name='小时';
	option.series[0].name='直播时长';
    option.series[0].data=livelongs;
	fiveChart.setOption(option);
    
	option.yAxis[0].name=$('#coin').text();
    option.series[0].name='用户消费';
    option.series[0].data=userconsumption;
	secondChart.setOption(option);
	
	option.yAxis[0].name='个';
	option.yAxis[0].splitNumber=3;
	option.series[0].data = newadduser,
	option.series[0].name='用户新增';
	thirdChart.setOption(option);
	forthChart.setOption(option4);
	window.onresize = echarts.resize
	

	//点击用户新增走势7日 30日数据
	$('.chart>div:eq(0) small>span').click(function(){
		if(!$(this).hasClass('choosdata')){
			$(this).siblings().removeClass('choosdata');
			$(this).addClass('choosdata');
		}
		var index = $(".chart>div:eq(0) small>span").index(this);
		thirdChart = echarts.init(document.getElementById('thirdT'));	
		
		option.yAxis[0].splitNumber=3;
		option.series[0].name='用户新增';
		option.yAxis[0].name='个';
		if(index == 0){
			chooseday(7);
			adduser({day:7});
			option.xAxis[0].axisLabel.rotate = 0;
			option.xAxis[0].data = dateList;
			option.series[0].data = newadduser;
			thirdChart.setOption(option);
		}
		if(index == 1){
			chooseday(30);
			adduser({day:30});
			option.xAxis[0].axisLabel.rotate = 45;
			option.xAxis[0].data = dateList;
			option.series[0].data = newadduser;
			thirdChart.setOption(option);
		}
	})
	//点击直播时长走势7日 30日数据
	$('.chart>div:eq(1) small>span').click(function(){
		if(!$(this).hasClass('choosdata')){
			$(this).siblings().removeClass('choosdata');
			$(this).addClass('choosdata');
		}
		var index = $(".chart>div:eq(1) small>span").index(this);
		fiveChart = echarts.init(document.getElementById('fiveT'));	
		
		option.yAxis[0].splitNumber=6;
		option.series[0].name='直播时长';
		option.yAxis[0].name='小时';
		if(index == 0){
			chooseday(7);
			livelong({day:7});
			option.xAxis[0].axisLabel.rotate = 0;
			option.xAxis[0].data = dateList;
			option.series[0].data = livelongs;
			fiveChart.setOption(option);
		}
		if(index == 1){
			chooseday(30);
			livelong({day:30});
			option.xAxis[0].axisLabel.rotate = 45;
			option.xAxis[0].data = dateList;
			option.series[0].data = livelongs;
			fiveChart.setOption(option);
		}
	})
	
	//点击用户充值走势7日 30日数据
	$('.chart>div:eq(2) small>span').click(function(){
		if(!$(this).hasClass('choosdata')){
			$(this).siblings().removeClass('choosdata');
			$(this).addClass('choosdata');
		}
		var index = $(".chart>div:eq(2) small>span").index(this);
		firstChart = echarts.init(document.getElementById('firstT'));
		
		
		option.yAxis[0].splitNumber=6;
		option.series[0].name='用户充值';
		option.yAxis[0].name=$('#coin').text();
		if(index == 0){
			chooseday(7);
			charge({day:7});
			option.xAxis[0].axisLabel.rotate = 0;
			option.xAxis[0].data = dateList;
			option.series[0].data = userrecharge;
			firstChart.setOption(option);
		}
		if(index == 1){
			chooseday(30);
			charge({day:30});
			option.xAxis[0].axisLabel.rotate = 45;
			option.xAxis[0].data = dateList;
			option.series[0].data = userrecharge;
			firstChart.setOption(option);
		}
	})	
	
	//点击用户充值占比 7日 30日数据
	$('.chart>div:eq(3) small>span').click(function(){
		if(!$(this).hasClass('choosdata')){
			$(this).siblings().removeClass('choosdata');
			$(this).addClass('choosdata');
		}
		var index = $(".chart>div:eq(3) small>span").index(this);
		forthChart = echarts.init(document.getElementById('forthT'));
		
		if(index == 0){	
			proportion({day:7});
			option4.series[0].data = rechargearry1;
			option4.series[1].data = rechargearry2;
			forthChart.setOption(option4);
		}
		if(index == 1){
			proportion({day:30});
			option4.series[0].data = rechargearry1;
			option4.series[1].data = rechargearry2;
			forthChart.setOption(option4);
		}
	})
	
	//点击用户消费7日 30日数据
	$('.chart>div:eq(4) small>span').click(function(){
		if(!$(this).hasClass('choosdata')){
			$(this).siblings().removeClass('choosdata');
			$(this).addClass('choosdata');
		}
		var index = $(".chart>div:eq(4) small>span").index(this);
		secondChart = echarts.init(document.getElementById('secondT'));	
		
		option.yAxis[0].splitNumber=6;
		option.series[0].name='用户消费';
		option.yAxis[0].name=$('#coin').text();
		if(index == 0){
			chooseday(7);
			consumption({day:7});
			option.xAxis[0].axisLabel.rotate = 0;
			option.xAxis[0].data = dateList;
			option.series[0].data = userconsumption;
			secondChart.setOption(option);
		}
		if(index == 1){
			chooseday(30);
			consumption({day:30});
			option.xAxis[0].axisLabel.rotate = 45;
			option.xAxis[0].data = dateList;
			option.series[0].data = userconsumption;
			secondChart.setOption(option);
		}
	})
	
	//点击表格  排名 热门
	$('.contentChart small>span').click(function(){
		if(!$(this).hasClass('choosdata')){
			$(this).siblings().removeClass('choosdata');
			$(this).addClass('choosdata');
		}
		var index = $(".contentChart small>span").index(this);
		switch (index){
			//用户消费排名
			case 0:
				userconsume({"limit":8,"offset":0,day:7});
			break;
			case 1:
				userconsume({"limit":8,"offset":0,day:30});
			break;
			case 2:
				userconsume({"limit":8,"offset":0,day:31});
			break;
			
			//热门直播
			case 3:
				hot_live({"limit":8,"offset":0,day:7});
			break;
			case 4:
				hot_live({"limit":8,"offset":0,day:30});
			break;
			
			//热门回放
			case 5:
				hot_back({"limit":8,"offset":0,day:7});
			break;
			case 6:
				hot_back({"limit":8,"offset":0,day:30});
			break;
			
			//主播时长
			case 7:
				anchor_long({"limit":8,"offset":0,day:7});
			break;
			case 8:
				anchor_long({"limit":8,"offset":0,day:30});
			break;
			
			//主播收入
			case 9:
				anchor_income({"limit":8,"offset":0,day:7});
			break;
			case 10:
				anchor_income({"limit":8,"offset":0,day:30});
			break;
			
		}
		
	})
	
	
	
	
	
})

//数字三位加逗号
function formatNum(str){
	if(str){
		str = str.toString();
		
		var newStr = "";
		var count = 0;
		if(str.indexOf(".")==-1){
		   for(var i=str.length-1;i>=0;i--){
			 if(count % 3 == 0 && count != 0){
			   newStr = str.charAt(i) + "," + newStr;
			 }else{
			   newStr = str.charAt(i) + newStr;
			 }
			 count++;
		   }
		   str = newStr ; //自动补小数点后两位
		}
		else
		{
		   for(var i = str.indexOf(".")-1;i>=0;i--){
			 if(count % 3 == 0 && count != 0){
			   newStr = str.charAt(i) + "," + newStr;
			 }else{
			   newStr = str.charAt(i) + newStr; //逐个字符相接起来
			 }
			 count++;
		   }
		   str = newStr + (str + "00").substr((str + "00").indexOf("."),3);
		 }
		return str;
	}else{
		return str;
	}
	
}

//日期格式 转换  yyyy-MM-dd M月d日
function selfdate(time){
	var datalist = time.split('-');
	return datalist[1]+'月'+datalist[2]+'日';
}


//维持session
function holdSession() {
	var timestamp = (new Date()).valueOf();
	$.ajax({
		async : false,
		url : "holdsession?a=" + timestamp,
		data : {},
		type : "get",
		cache : false,
		dataType : "json",
		success : function(json) {
			if (json == null) top.location.href = "./";
			if (json.code != 200) top.location.href = "./";
			if (json.code == 200) return ;
		},
		error:function(json){
			top.location.href= "./";
		}
	});
}