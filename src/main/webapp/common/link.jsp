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
.l-btn {
	text-align: left /*按钮样式默认是居中*/
}
</style>
