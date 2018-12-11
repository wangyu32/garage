<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.getSession().setAttribute("basePath", basePath);
%> --%>
<link rel="stylesheet" href="<c:url value='/css/comm/global.css' />"/>
<script src='<c:url value="/js/comm/jquery-1.12.0.min.js" />'></script>
<script src='<c:url value="/js/comm/hashMap.js" />'></script> 
<script src='<c:url value="/js/comm/comm.js" />'></script> 
<!-- <script type="text/javascript">
var basePath = "${basePath}";
$.ajaxSetup({  
    type: 'POST',  
    complete: function(xhr,status) {  
        var sessionStatus = xhr.getResponseHeader('sessionstatus');  
        if(sessionStatus == 'timeOut') {  
            layer.confirm(
           		'由于您长时间没有操作, 请重新登录.', 
           		{btn: ['确认'],}, 
           		function(){
           			window.top.location.href = '${basePath}';
           		}
            );
        }  
    }  
});
</script> --> 

<%

if (request.getParameter("drag") != null)
{
%>
<script src='<c:url value="/js/comm/jquery.dragsort-0.5.2.js" />'></script>
<%
}
%>

<%
//日期控件 2016-3-23 15:03 by licui
if (request.getParameter("DatePicker") != null)
{
%>
<script src="<c:url value='/component/DatePicker/WdatePicker.js' />"></script>
<script src="<c:url value='/component/DatePicker/bootstrap-datetimepicker.js' />"></script>
<link rel="stylesheet" href="<c:url value='/component/DatePicker/bootstrap-datetimepicker.css' />"/>
<%
}
%>

<%
//饼图控件 2016-4-6 17:35 by licui
if (request.getParameter("echarts") != null)
{
%>
<script src="<c:url value='/component/echarts/echarts-all.js' />"></script> 
<%
}
%>


<%
//城市二级联动
if (request.getParameter("city") != null)
{
%>
<script src="<c:url value='/component/city/jquery.city.js' />"></script> 
<%
}
%>

<%
//弹出层控件 2016-3-23 15:03 by licui
if (request.getParameter("layer") != null)
{
%>
<link rel="stylesheet" href="<c:url value='/component/layer/skin/layer.css' />"/>
<script src="<c:url value='/component/layer/layer.js' />"></script> 
<%
} 
%>

<%
//bootstrap 
if ( request.getParameter("bootstrap") != null)
{
%> 
<link rel="stylesheet" href="<c:url value='/component/bootstrap/css/bootstrap.css' />"/>
<script src="<c:url value='/component/bootstrap/js/bootstrap.min.js' />"></script>
<%
}
%>

<%
//bootstrap hover
if ( request.getParameter("bootstraphover") != null)
{
%> 
<script src="<c:url value='/component/bootstrap/js/umd/tooltip.js' />"></script>
<script src="<c:url value='/component/bootstrap/js/umd/popover.js' />"></script>
<%
}
%>

<%
//swiper 控件
if ( request.getParameter("swiper") != null)
{
%> 
<link rel="stylesheet" href='<c:url value="/component/swiper/swiper.css" />' />
<script src='<c:url value="/component/swiper/swiper.min.js" />'></script>
<%
}
%>

<%
//tree 控件
if ( request.getParameter("tree") != null)
{
%> 
<link rel="stylesheet" href="<c:url value='/component/tree/css/zTreeStyle/zTreeStyle.css' />"/>
<script src="<c:url value='/component/tree/js/jquery.ztree.core-3.5.js' />"></script>
<%
}
%>

<%
//表格包含
if ( request.getParameter("table") != null)
{
%> 
<link rel="stylesheet" href="<c:url value='/component/table/bootstrap-table.css' />"/>
<script src="<c:url value='/component/table/bootstrap-table.js' />"></script>  
<script src="<c:url value='/component/table/locale/bootstrap-table-zh-CN.min.js' />"></script>
<%
}
%>

<link rel="stylesheet" href='<c:url value="/css/comm/reset.css" />' />

<%
//控制是否使用滚动行  2016-7-29 16;19 by zhengmy
if (request.getParameter("ScrollComm") == null ||request.getParameter("ScrollComm").equals("no")==false)
{
%>  
<link rel="stylesheet" href='<c:url value="/css/comm/scrollComm.css" />'/>
<%
}
%>

<%
//form
if ( request.getParameter("form") != null)
{
%> 
<script src="<c:url value='/component/form/jquery.form.js' />"></script>
<%
}
%>

<%
//图片上传
if ( request.getParameter("uploadImg") != null)
{
%> 
<script src="<c:url value='/component/uploadimg/plupload.full.min.js' />"></script>
<script src="<c:url value='/component/uploadimg/upload.js' />"></script>
<%
}
%>

<%
//上传
if ( request.getParameter("upload") != null)
{
%> 
<script src="<c:url value='/component/uploadimg/plupload.full.min.js' />"></script>
<%
}
%>

<%
//ckplayer
if ( request.getParameter("ckplayer")!= null)	
{
%>
<script src='<c:url value="/component/ckplayer/ckplayer.js"/>'	></script>
<%
}
%>

<%
//validate表单验证
if ( request.getParameter("validate") != null)
{
%>
<script src="<c:url value='/component/validate/js/bootstrapValidator.min.js' />"></script>
<%
}
%>

<%
//dorpdownlist时间控件
if ( request.getParameter("jqwidget") != null)
{
%>
<link rel="stylesheet" href="<c:url value='/component/jqwidget/css/jqx.base.css' />"/>
<script src="<c:url value='/component/jqwidget/js/jqxcore.js' />"></script> 
<script src="<c:url value='/component/jqwidget/js/jqxbuttons.js' />"></script> 
<script src="<c:url value='/component/jqwidget/js/jqxscrollbar.js' />"></script> 
<script src="<c:url value='/component/jqwidget/js/jqxlistbox.js' />"></script> 
<script src="<c:url value='/component/jqwidget/js/jqxdropdownlist.js' />"></script> 
<%
}
%>

<%
//switch开关
if ( request.getParameter("switch") != null)
{
%>
<link rel="stylesheet" href="<c:url value='/component/switch/css/bootstrap-switch.min.css' />"/>
<script src="<c:url value='/component/switch/js/bootstrap-switch.min.js' />"></script> 
<%
}
%>

<%
//treeview
if ( request.getParameter("treeview") != null)
{
%>
<link rel="stylesheet" href="<c:url value='/component/treeview/css/bootstrap-treeview.css' />"/>
<script src="<c:url value='/component/treeview/js/bootstrap-treeview.js' />"></script> 
<%
}
%>

<%
//cookie
if ( request.getParameter("cookie") != null)
{
%>
<script src="<c:url value='/component/cookie/jquery.cookie.js' />"></script> 
<%
}
%>

<%
//page
if ( request.getParameter("page") != null)
{
%>
<script src="<c:url value='/component/page/bootstrap-paginator.min.js' />"></script> 
<%
}
%>


<%
//editable
if ( request.getParameter("editable") != null)
{
%>
<link rel="stylesheet" href="<c:url value='/component/editable/css/bootstrap-editable.css' />"/>
<script src="<c:url value='/component/editable/js/bootstrap-editable.min.js' />"></script> 
<%
}
%>