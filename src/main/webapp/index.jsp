<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="common/link1.jsp"%>
<%@include file="common/css.jsp"%>
<title>
<c:choose>
	<c:when test="${not empty blog }">
	${blog.title }
	</c:when>
	<c:when test="${not empty kw }">
	${kw }_站内搜索
	</c:when>
	<c:otherwise>
	时间就是金钱
	</c:otherwise>
</c:choose>
</title>
<style>
</style>
</head>
<body>
	<%@include file="common/header.jsp" %>	
	<div class="layui-container">
<%-- 	    <c:if test="${isIndex }"> --%>
<%-- 		<jsp:include page="common/carousel.jsp"></jsp:include> --%>
<%-- 	    </c:if> --%>
		<div class="layui-row layui-col-space20">
        	<div class="layui-col-md8">
				<jsp:include page="${mainPage }"></jsp:include>
        	</div><!-- main-end -->
        	
        	<aside class="layui-col-md4">
        		<%@include file="common/sidebar.jsp" %>
			</aside>
        
        </div>
	</div>
	
	<%@include file="common/footer.jsp" %>

</body>
</html>