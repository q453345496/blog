<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="sidebar">
			<section class="widget">
				<header>
					<h3>个人信息</h3>
				</header>
				<div>
					<p>网名:moretime</p>
					<p>主页:localhost</p>
					<p>现居:深圳</p>
				</div>
			</section>
			<section class="widget">
				<header>
					<h3>文章分类</h3>
				</header>
				<ul>
					<li><a href="1">Python</a></li>
					<li><a href="3">C++</a></li>
					<li><a href="#">Javascript</a></li>
					<li><a href="#">MySQL</a></li>
					<li><a href="#">Python</a></li>
				</ul>
			</section>
			<section class="widget">
				<header>
					<h3>最新文章</h3>
				</header>
				<ul>
					<c:forEach var="blog" items="${lastBlogList}">
					<li><a title="${blog.title }" href="/blog/${blog.id}.html">${blog.title }</a>
					</c:forEach>
				</ul>
			</section>
</aside><!-- end sidebar -->