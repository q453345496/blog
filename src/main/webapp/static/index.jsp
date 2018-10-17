<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="../common/link1.jsp"%>
<title>The Flower Blog</title>
<style>
body { 
 	background-color: #f6f6f6; 
    color: #555; 
} 
.post-pagination{
	margin-top: 20px;
}
.post-pagination .pagination{
	justify-content: center;
}

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