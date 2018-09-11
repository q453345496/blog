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
<div class="post-item">
	<div class="thumb">
		<a class="focus" href="#">
			<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
		</a>
	</div>
	<div class="info">
		<div class="title">
			<h2>
				<a href="#" title="HTML5-语义化">chrome  transition闪烁BUG</a>
			</h2>
		</div>
		<div class="meta">
			<span><i class="fa fa-clock-o"></i>2018-09-10</span>
			<span><i class="fa fa-list-alt"></i>html</span>
			<span><i class="fa fa-eye"></i>阅读</span>
		</div>
		<div class="summary">
			&nbsp;&nbsp;&nbsp;&nbsp;前段时间写鼠标悬停元素上移效果时，当鼠标恰好放在元素边缘时，chrome出现一直上下移动的问题，其他浏览器表现正常。原因尚不知，可能是实现方式不对吧（PS：使用top实现），虽然不知道原因，但...		</div>
	</div>
</div>
<div class="post-item">
	<div class="thumb">
		<a class="focus" href="#">
			<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
		</a>
	</div>
	<div class="info">
		<div class="title">
			<h2>
				<a href="#" title="HTML5-语义化">HTML5-语义化</a>
			</h2>
		</div>
		<div class="meta">
			<span><i class="fa fa-clock-o"></i>2018-09-10</span>
			<span><i class="fa fa-list-alt"></i>html</span>
			<span><i class="fa fa-eye"></i>阅读</span>
		</div>
		<div class="summary">
			&nbsp; &nbsp; 距HTML5标准规范制定完成并公开发布已经有好些年了，面试时也少不了要问对HTML5语义化得理解。但是在实际运用时，真正使用HTML5标签来开发的似乎不是很多（ps：查看了几个巨头公司网站推论），可能一部分原因是仍有部分用户使用在使...
		</div>
	</div>
</div>
<div class="post-pagination">
	<ul id="pagination" class="pagination">
	</ul>
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