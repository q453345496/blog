<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/jquery-easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/jquery-easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/font-awesome/css/font-awesome.min.css">
<script src="<%=path%>/resources/jquery-1.12.3.min.js"></script>
<script src="<%=path%>/resources/jquery-easyui/jquery.easyui.min.js"></script>
<script src="<%=path%>/resources/jquery-easyui/easyui-lang-zh_CN.js"></script>
<script src="<%=path%>/resources/easyui-common.js"></script>
<script type="text/javascript">
var basePath = "<%=path%>";

$(document).keydown(function(e) {
	e = e || window.event;
	var code = e.keyCode ? e.keyCode : e.which;
	if (code == 27) {
		var dialog = $(".easyui-dialog:visible:last");
		if(dialog.length > 0) {		
			dialog.dialog("close");
		}
	}
});
</script>
<style>
table.input tr td:nth-child(1){
	text-align: right;
}
[class*="fa-"] {
/* 	color: #0088cc; */
	font-size: 16px;
	background-image:none!important;
	overflow: visible!important;
}

.s0 {
}
.datagrid-row-selected .s0{
}
.s1 {
	color: green;
}
.datagrid-row-selected .s1 {
	color: #fff;
}
.s-1 {
	color: red;
}
.datagrid-row-selected .s-1 {
	color: #fff;
}
.img-preview{
	height: 180px;
	width: 200px;
	padding-top: 10px
}
</style>
