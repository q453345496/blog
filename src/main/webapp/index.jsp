<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="common/link.jsp"%>
<title>The Flower Blog</title>
<style>
body {
	background-color: #f6f6f6;
	font: 14px/1.8 'Microsoft Yahei','\5FAE\8F6F\96C5\9ED1',Arial,'Hiragino Sans GB','\5B8B\4F53';
    color: #555;
}
a {
	color: #666;
	text-decoration: none;
}
a:hover {
	color: #45B6F7;
	text-decoration: none;
}
.container {
	padding-right: 0px;
	padding-left: 0px;
}
.col-md-8, .col-md-4{
	padding-left: 10px;
	padding-right: 10px;
}


</style>
</head>
<body>
	<%@include file="common/header.jsp" %>
	
	<div role="main" class="container">
		<div class="row">
        	<div class="col-md-8">
        		<c:if test="${isIndex }">
				<jsp:include page="common/carousel.jsp"></jsp:include>
        		</c:if>
				<jsp:include page="${mainPage }"></jsp:include>
        	</div><!-- main-end -->
        	
        	<aside class="col-md-4">
        		<%@include file="common/sidebar.jsp" %>
			</aside>
        
        </div>
	</div>
	
	<%@include file="common/footer.jsp" %>

</body>
</html>