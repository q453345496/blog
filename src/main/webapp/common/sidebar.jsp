<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.sidebar .author{

}

.sidebar .widget .widget-title{
	font-size: 20px;
	font-weight: bold;
}
.sidebar .widget .widget-content{
	padding: 16px;
}
</style>
<aside class="sidebar">
			<section class="author">
				<header class="author-title">
					<div>个人信息</div>
				</header>
				<div>
					<p>网名:moretime</p>
					<p>主页:localhost</p>
					<p>现居:深圳</p>
				</div>
			</section>
			<section class="widget">
				<header class="widget-title">
					<div>文章分类</div>
				</header>
				<ul class="widget-content">
					<li><a href="1">Python</a></li>
					<li><a href="3">C++</a></li>
					<li><a href="#">Javascript</a></li>
					<li><a href="#">MySQL</a></li>
					<li><a href="#">Python</a></li>
				</ul>
			</section>
			<section class="widget">
				<header class="widget-title">
					<div>阅读最多</div>
				</header>
				<ul class="widget-content">
					<c:forEach var="blog" items="${hotBlogList}">
					<li><a title="${blog.title }" href="/blog/${blog.id}.html">${blog.title }</a>
					</c:forEach>
				</ul>
			</section>
			<section class="widget">
				<header class="widget-title">
					<div>最新文章</div>
				</header>
				<ul class="widget-content">
					<c:forEach var="blog" items="${lastBlogList}">
					<li><a title="${blog.title }" href="/blog/${blog.id}.html">${blog.title }</a>
					</c:forEach>
				</ul>
			</section>
</aside><!-- end sidebar -->