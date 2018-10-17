<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="../common/link1.jsp"%>
<%@include file="common/css.jsp"%>
<title>The Flower Blog</title>
<style>

</style>
</head>
<body>
	<%@include file="common/header.jsp" %>
	
	<div class="layui-container">
<%-- 		<jsp:include page="common/carousel.jsp"></jsp:include> --%>
		<div class="layui-row layui-col-space20">
        	<div class="layui-col-md8">
				<jsp:include page="view/blog/search.jsp"></jsp:include>
<%-- 				<jsp:include page="view/blog/list.jsp"></jsp:include> --%>
<%-- 				<jsp:include page="./view/blog/blog.jsp"></jsp:include> --%>
        	</div><!-- main-end -->
        	
        	<aside class="layui-col-md4">
        		<%@include file="common/sidebar.jsp" %>
			</aside>
        
        </div>
	</div>
	
	<%@include file="../common/footer.jsp" %>

</body>
</html>