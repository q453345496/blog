<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="common/link.jsp"%>
<title>The Flower Blog</title>
<style>
</style>
</head>
<body>
	<%@include file="common/header.jsp" %>
	<%@include file="common/nav.jsp" %>

	<div class="content-wrap">
		<jsp:include page="${mainPage }"></jsp:include>
		<jsp:include page="common/sidebar.jsp"></jsp:include>
	</div>
	
	<%@include file="common/footer.jsp" %>
</body>
</html>