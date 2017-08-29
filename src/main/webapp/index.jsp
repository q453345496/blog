<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>blog Template</title>

<link href="<%=path%>/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<script src="<%=path%>/resources/jquery-1.12.3.min.js"></script>
<script src="<%=path%>/resources/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
body{
	padding-top: 5px;
	padding-bottom: 40px;
}
</style>
</head>

<body>
	<div class="container">
		<jsp:include page="./common/menu.jsp" />
		<div class="row">
			<div class="col-md-9">222</div>
			<div class="col-md-3">333</div>
		</div>
		<a href="<%=path%>/hello/blog/2.html">222</a>
		<jsp:include page="./common/footer.jsp" />
	</div>
</body>
</html>