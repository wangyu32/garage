/**
 * 
 */
$(function() {
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('zhuxing'));
    // 指定图表的配置项和数据
    var option = {
        tooltip : {},
        grid: {
            left: '0%',
            right: '4%',
            bottom: '3%',
            top:'5%',
            containLabel: true
        },
        xAxis: {
            data: [],
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#515974'
                }
            }
        },
        yAxis: {
        	axisLabel: {
                show: true,
                textStyle: {
                    color: '#515974'
                }
            }
        },
        series: [{
            type: 'bar',
            data: []
        }]
    };
    var myChart1 = echarts.init(document.getElementById('zhexian'));
    // 指定图表的配置项和数据
    var option1 = {
       tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '0%',
            right: '4%',
            bottom: '3%',
            top:'8%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [],
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#515974'
                }
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#515974'
                }
            }
        },
        series: [
            {
                name:'利用率',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        textStyle : {
                            fontSize : '12',
                            color:'#515974'
                        }
                    }
                },
                itemStyle : {
                    normal : {
                        color:'#23a662',
                        lineStyle:{
                            color:'#23a662'
                        }
                    }
                },
                data:[]
            }
        ]
    };
    
    var myChart2 = echarts.init(document.getElementById('zhexian-bg'));
    // 指定图表的配置项和数据
    var option2 = {
       tooltip : {
            trigger: 'axis'
        },
        grid: {
            left: '0%',
            right: '4%',
            bottom: '3%',
            top:'8%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : [],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#515974'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#515974'
                    }
                }
            }
        ],
        series : [
            {
                name:'订单量',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        textStyle : {
                            fontSize : '12',
                            color:'#515974'
                        }
                    }
                },
                itemStyle : {
                    normal : {
                        color:'#afd5c3'
                    }
                },
                areaStyle: {normal: {}},
                data:[]
            }
        ]
    };
    
    var myChart3 = echarts.init(document.getElementById('zhexian-up'));
    // 指定图表的配置项和数据
    var option3 = {
        
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '0%',
            right: '4%',
            bottom: '3%',
            top:'8%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [],
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#515974'
                }
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#515974'
                }
            }
        },
        series: [
            {
                name:'消费频次',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        textStyle : {
                            fontSize : '12',
                            color:'#515974'
                        }
                    }
                },
                itemStyle : {
                    normal : {
                        color:'#218ad3',
                        lineStyle:{
                            color:'#218ad3'
                        }
                    }
                },
                data:[]
            }
        ]
    };
    
    var myChart4 = echarts.init(document.getElementById('bingtu-pay'));
    // 指定图表的配置项和数据
    var option4 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        color:[
                '#fe9d09',
                '#fe6835',
                '#fe0983'
            ],
        series : [
                    {
                        name: '支付占比',
                        type: 'pie',
                        radius : '90%',
                        center: [
                                    '50%', 
                                    '50%'
                                ],
                        data:[
                            {value:0, name:'微信'},
                            {value:0, name:'现金'},
                            {value:0, name:'支付宝'}
                        ],
                        label: {
    						normal: {
    							position: 'inner',
    							textStyle : {
    		                        fontSize : '14',
    		                        color:'#fff'
    		                    }
    						}
    					},
    					labelLine: {
    						normal: {
    							show: false
    						}
    					},
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
        ]
    };
    
    var myChart5 = echarts.init(document.getElementById('bingtu-income'));
    // 指定图表的配置项和数据
    var option5 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        color:[
                '#218ad3',
                '#0ac4f1',
                '#fe6835',
                '#fe0983'
            ],
        series : [
                    {
                        name: '收入占比',
                        type: 'pie',
                        radius : '90%',
                        center: [
                                    '50%', 
                                    '50%'
                                ],
                        data:[
                              {value:0, name:'场地'}, 
                              {value:0, name:'购物'},
                              {value:0, name:'充值'}, 
                              {value:0, name:'购卡'}
                             ],
                        label: {
    						normal: {
    							position: 'inner',
    							textStyle : {
    		                        fontSize : '14',
    		                        color:'#fff'
    		                    }
    						}
    					},
    					labelLine: {
    						normal: {
    							show: false
    						}
    					},
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
            ]
    };
    
    //星期的动态展示
	var days = loadWeek();
	option.xAxis.data=days;
	option1.xAxis.data=days;
	option2.xAxis[0].data=days;
	option3.xAxis.data=days;
	
	//获取图形数据
	$.ajax({
		async:false,
		url:"./getData",
		type:"post",
		cache: false,			
		dataType:"json",
		success:function(json){
			$("#totalOrder").html(json.totalOrder);
			$("#totalAccount").html(json.totalAccount.toFixed(2));
			$("#unStartedOrder").html(json.unStartedOrder);
			$("#memberCount").html(json.members.number);
			$("#memberBalance").html(json.members.account.toFixed(2));
			$("#monthAccount").html(json.homePage.monthAccount.toFixed(2));
			option.series[0].data=weekData(json.homePage.account, 2);
			option1.series[0].data=weekData(json.homePage.useRatio, 2);
			option2.series[0].data=weekData(json.homePage.orderNum, 0);
			option3.series[0].data=weekData(json.homePage.consumeNum, 0);
			setOption4Date(json.homePage.payOrder, option4);
			setOption5Date(json.homePage.incomeOrder, option5);
		},
		error:function() {
			option.series[0].data=[0,0,0,0,0,0,0];
			option1.series[0].data=[0,0,0,0,0,0,0];
			option2.series[0].data=[0,0,0,0,0,0,0];
			option3.series[0].data=[0,0,0,0,0,0,0];
		}
	}); 
    
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart1.setOption(option1);
    myChart2.setOption(option2);
    myChart3.setOption(option3);
    myChart4.setOption(option4);
    myChart5.setOption(option5);
    
    window.onresize = function(){
    	myChart.resize();
    	myChart1.resize();
    	myChart2.resize();
    	myChart3.resize();
    	myChart4.resize();
    	myChart5.resize();
    }
});

function setOption4Date(json, option4) {
	option4.series[0].data[0].value=json.wechat.toFixed(2);
	option4.series[0].data[1].value=json.cash.toFixed(2);
	option4.series[0].data[2].value=json.alipay.toFixed(2);
	if((json.wechat+json.cash+json.alipay) != 0) {
		if(json.wechat/(json.wechat+json.cash+json.alipay) < 0.05) {
			option4.series[0].data[0].name="";
		}
		if(json.cash/(json.wechat+json.cash+json.alipay) < 0.05) {
			option4.series[0].data[1].name="";
		}
		if(json.alipay/(json.wechat+json.cash+json.alipay) < 0.05) {
			option4.series[0].data[2].name="";
		}
	} 
}

function setOption5Date(json, option5) {
	option5.series[0].data[0].value=json.ordertotal.toFixed(2);
	option5.series[0].data[1].value=json.shoptotal.toFixed(2);
	option5.series[0].data[2].value=json.chargeTotal.toFixed(2);
	option5.series[0].data[3].value=json.cardTotal.toFixed(2);
	var total=json.ordertotal+json.shoptotal+json.cardTotal+json.chargeTotal;
	if(total != 0) {
		if(json.ordertotal/total < 0.05) {
			option5.series[0].data[0].name="";
		} 
		if(json.shoptotal/total < 0.05) {
			option5.series[0].data[1].name="";
		}
		if(json.chargeTotal/total < 0.05) {
			option5.series[0].data[2].name="";
		}
		if(json.cardTotal/total < 0.05) {
			option5.series[0].data[3].name="";
		}
	}
}

function weekData(json, num) {
	var day=new Date().getDay();
	var date=[];
	switch(day) {
		case 0:
			date=[json.Mon.toFixed(num), json.Tue.toFixed(num), json.Wed.toFixed(num), json.Thu.toFixed(num), json.Fri.toFixed(num), json.Sat.toFixed(num), json.Sun.toFixed(num)];
			break;
		case 1:
			date=[json.Tue.toFixed(num), json.Wed.toFixed(num), json.Thu.toFixed(num), json.Fri.toFixed(num), json.Sat.toFixed(num), json.Sun.toFixed(num), json.Mon.toFixed(num)];
			break;
		case 2:
			date=[json.Wed.toFixed(num), json.Thu.toFixed(num), json.Fri.toFixed(num), json.Sat.toFixed(num), json.Sun.toFixed(num),json.Mon.toFixed(num), json.Tue.toFixed(num)];
			break;
		case 3:
			date=[json.Thu.toFixed(num), json.Fri.toFixed(num), json.Sat.toFixed(num), json.Sun.toFixed(num),json.Mon.toFixed(num), json.Tue.toFixed(num), json.Wed.toFixed(num)];
			break;
		case 4:
			date=[json.Fri.toFixed(num), json.Sat.toFixed(num), json.Sun.toFixed(num),json.Mon.toFixed(num), json.Tue.toFixed(num), json.Wed.toFixed(num), json.Thu.toFixed(num)];
			break;
		case 5:
			date=[json.Sat.toFixed(num), json.Sun.toFixed(num),json.Mon.toFixed(num), json.Tue.toFixed(num), json.Wed.toFixed(num), json.Thu.toFixed(num), json.Fri.toFixed(num)];
			break;
		case 6:
			date=[json.Sun.toFixed(num),json.Mon.toFixed(num), json.Tue.toFixed(num), json.Wed.toFixed(num), json.Thu.toFixed(num), json.Fri.toFixed(num), json.Sat.toFixed(num)];
			break;
	}
	return date;
}

function loadWeek() {
	var day=new Date().getDay();
	var date = [];
	switch(day) {
	case 0:
		date=["星期一","星期二","星期三","星期四","星期五","星期六","今天"];
		break;
	case 1:
		date=["星期二","星期三","星期四","星期五","星期六","星期日","今天"];
		break;
	case 2:
		date=["星期三","星期四","星期五","星期六","星期日","星期一","今天"];
		break;
	case 3:
		date=["星期四","星期五","星期六","星期日","星期一","星期二","今天"];
		break;
	case 4:
		date=["星期五","星期六","星期日","星期一","星期二","星期三","今天"];
		break;
	case 5:
		date=["星期六","星期日","星期一","星期二","星期三","星期四","今天"];
		break;
	case 6:
		date=["星期日","星期一","星期二","星期三","星期四","星期五","今天"];
		break;
	}
	return date;
}