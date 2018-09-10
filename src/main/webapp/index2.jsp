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

.footer {
	padding: 2.5rem 0;
	background-color: #f9f9f9;
	text-align: center;
}

</style>
</head>
<body>
	<%@include file="common/header.jsp" %>
	<div role="main" class="container">
		<div class="row">
        	<div class="col-md-8">
				<jsp:include page="view/blog/blog1.jsp"></jsp:include>
        	</div><!-- main-end -->
        	
        	<aside class="col-md-4">
        		<%@include file="common/sidebar1.jsp" %>
			</aside>
        
        </div>
	</div>
	
	<footer class="footer">
		<p>
			Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a>
			by <a href="https://twitter.com/mdo">@mdo</a>.
		</p>
		<p>
			<a href="#">Back to top</a>
		</p>
	</footer>

</body>
</html>