<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.post-item{
	background-color: #fff;
	display: flex;
	padding: 20px;
	color: #999;
	border: 1px solid #eaeaea;
	margin-bottom: -1px;
}
.post-item .thumb{
	margin-right: 12px;
}
.post-item .thumb img{
	width: 220px;
	min-width: 220px;
	height: 150px;
}
.post-item .title{
	margin-bottom: 10px;
}
.post-item .title h2{
	font-size: 1.2rem;
    font-weight: 600;
}
.post-item .title h2 a{
	color: #555;
}
.post-item .title h2 a:hover{
	color: #45B6F7;
}
.post-item .meta{
	margin-bottom: 10px;
	font-size:12px;
}
.post-item .meta i{
	margin-right: 4px;
}
.post-item .meta span{
	margin-right: 20px;
}
.post-item .summary{
	margin-bottom: 10px;
	font-size:12px;
	text-align: justify;
}
.post-pagination{
	margin-top: 20px;
}
.post-pagination .pagination{
	justify-content: center;
}
</style>
<c:forEach var="blog" items="${blogs}">
<div class="post-item">
	<div class="thumb">
		<a class="focus" href="/blog/${blog.id }.html">
			<img src="${blog.thumb }" alt="${blog.title }">
		</a>
	</div>
	<div class="info">
		<div class="title">
			<h2>
				<a href="/blog/${blog.id }.html" title="${blog.title }">${blog.title }</a>
			</h2>
		</div>
		<div class="meta">
			<span><i class="fa fa-clock-o"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.createTime}"/></span>
			<span><i class="fa fa-list-alt"></i><a href="/${blog.typeCode }">${blog.typeName}</a></span>
			<span><i class="fa fa-eye"></i>阅读(${blog.click })</span>
		</div>
		<div class="summary">
			&nbsp;&nbsp;&nbsp;&nbsp;${blog.summary }</div>
	</div>
</div>
</c:forEach>
<div class="post-pagination">
	<ul id="pagination" class="pagination">
	</ul>
</div>
<script type="text/javascript">
var pathname = location.pathname;
$('#pagination').jqPaginator({
    //totalPages: 100,
    pageSize: ${page.size},
    totalCounts: ${page.total},
    visiblePages: 10,
    currentPage: ${page.current},
    first: '<li class="page-item"><a class="page-link" href="' + pathname + '?page={{page}}">首页</a></li>',
    last: '<li class="page-item"><a class="page-link" href="' + pathname + '?&page={{page}}">尾页</a></li>',
    prev: '<li class="page-item"><a class="page-link" href="' + pathname + '?&page={{page}}">上一页</a></li>',
    next: '<li class="page-item""><a class="page-link" href="' + pathname + '?&page={{page}}">下一页</a></li>',
    page: '<li class="page-item""><a class="page-link" href="' + pathname + '?&page={{page}}">{{page}}</a></li>',
    onPageChange: function (num, type) {
    }
});
</script>