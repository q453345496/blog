<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style>
.result-status{
	background-color: #fff;
    padding: 10px 20px 4px;
    font-size: 12px;
    border-bottom: 1px solid #E5E5E5;
}
.result-error{
	background-color: #fff;
	padding: 20px;
    text-align: center;
}
.result-item{
	background-color: #fff;
	padding: 10px 20px 10px;
	color: #999;
}
.result-item h3 a{
	font-size: 20px;
    text-decoration: none;
    font-weight: normal;
    color: #00C;
    vertical-align: middle;
}
.result-item h3 a:hover{
	color: #dd4b39;
}
.result-item .url{
	color: #40AA53;
	font-size: 12px;
}
.result-item .summary{
	color: #555;
}
.result-item .date{
	font-size: 12px;
}

.result-item .highlight{
	color: #dd4b39;
}
.result-item p{
	margin: 0;
}
</style>
<div class="search-result">
	<div class="result-status">
		为您找到相关记录<strong>24</strong>条
	</div>
	<c:choose>
		<c:when test="${empty blogs }">
			<div class="result-error">
				未查询到结果，请换个关键字试试看！
			</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="blog" items="${blogs }">
			<div class="result-item">
				<h3><a href="<%=basePath%>/blog/${blog.id }.html" title="HTML5-语义化" target="_blank">${blog.title }</a></h3>
				<p class="url"><%=basePath%>/blog/${blog.id }.html</p>
				<p class="summary">${blog.summary }</p>
				<p class="date">发布于&nbsp;2018-09-29</p>
			</div>
			</c:forEach>
			<div class="post-pagination">
				<ul id="pagination" class="pagination">
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript">
$('#pagination').jqPaginator({
    //totalPages: 100,
    pageSize: 10,
    totalCounts: 11,
    visiblePages: 10,
    currentPage: 1,
    first: '<li class="page-item"><a class="page-link" href="/s?page={{page}}">首页</a></li>',
    last: '<li class="page-item"><a class="page-link" href="/s?&page={{page}}">尾页</a></li>',
    prev: '<li class="page-item"><a class="page-link" href="/s?&page={{page}}">上一页</a></li>',
    next: '<li class="page-item""><a class="page-link" href="/s?&page={{page}}">下一页</a></li>',
    page: '<li class="page-item""><a class="page-link" href="/s?&page={{page}};">{{page}}</a></li>',
    onPageChange: function (num, type) {
    }
});
</script>