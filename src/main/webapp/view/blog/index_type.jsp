<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style>
.category-first .title{
	line-height: 30px;
	padding: 0 20px;
	white-space: nowrap;
	word-wrap: normal;
	text-overflow: ellipsis;
	overflow: hidden;
}
.first-info{
	display: flex;
	padding: 5px 20px;
}
.first-info .thumb{
	margin-right: 12px;
}
.first-info .thumb img{
	width: 186px;
	height: 140px;
}
.first-info .summary{
	color:#999;
}
.category-other{
	padding: 5px 20px;
}
.other-info{
	display: flex;
	justify-content: space-between;
}
.other-info a:hover{
	color: #45B6F7;
}
.other-info .fa-angle-right{
	width: 12px;
}

</style>
<c:forEach var="cat" items="${catList }">
<div class="layui-row index index-category">
	<h3 class="cat-title">
		${cat.name }<a href="<%=basePath%>/t/${cat.code }"><span>更多</span><i class="fa fa-arrow-circle-right fa-lg"></i></a>
	</h3>
	<div>
		<c:forEach end="0" var="blog" items="${cat.blogs }">
		<div class="category-first">
			<h2 class="title">
				<a href="#" title="${blog.title }" target="_blank">${blog.title }</a>
			</h2>
			<div class="first-info">
				<div class="thumb">
					<a class="focus" href="#">
						<img src="../resources/images/koala.jpg" alt="${blog.title }">
					</a>
				</div>
				<div class="summary">
					&nbsp;&nbsp;&nbsp;&nbsp;${blog.summary }
				</div>
			</div>
		</div>
		</c:forEach>
		<div class="category-other">
			<ul>
				<c:forEach begin="1" end="4" var="blog" items="${cat.blogs }">
				<li>
					<div class="other-info">
						<a href="<%=basePath %>/b/${blog.id }.html"><i class="fa fa-angle-right fa-lg"></i>${blog.title }</a>
						<span><fmt:formatDate pattern="MM-dd" value="${blog.createTime}"/></span>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
</c:forEach>