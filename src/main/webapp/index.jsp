<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="common/link.jsp"%>
<title>The Flower Blog</title>
<style>
ul {
	padding: 0;
}

body {
	background: #f5f5f5;
	min-width: 1200px;
	width: 100%;
}

#main-header {
	width: 100%;
	max-width: 1200px;
	margin: auto;
}

#nav {
	position: relative;
	width: 100%;
	height: 40px;
	line-height: 40px;
	background: gray;
	margin-bottom: 10px;
}

#nav ul {
	width: 1200px;
	margin: 0 auto;
	list-style: none;
}

#nav ul li {
	float: left;
	width: 100px;
	text-align: center;
	display: inline-block;
}

#nav ul li a {
	display: block;
	color: #FFF;
}

#nav ul li a:hover {
	color: #00F;
}

#nav ul li.selected a {
	color: #00F;
}

.content-wrap {
	width: 1200px;
	margin: 0 auto;
	overflow: hidden;
}

.main-content {
	width: 900px;
	float: left;
	overflow: hidden;
}

.blog-item {
	background:#fff;
	width: 100%;
	padding: 16px;
	margin-top: 12px;
	display: inline-block;
}

.blog-item .thumb {
	float: left;
	width: 25%;
	height: 165px;
}

.blog-item .thumb img {
	width: 100%;
	height: 100%;
	overflow: auto;
}

.blog-item .info {
	float: right;
	width: 73%;
	height: 165px;
}

.blog-item .info .title h3{
	font-size: 20px;
	margin: 0 0 10px 0;
}

.blog-item .summary {
	min-height: 113px;
}

.blog-item .summary p{
	margin: 0;
}

.blog-item .summary .readmore a {
	color: red;
	text-decoration: underline;
}

.author {
	color: #999;
}

.author .muted {
	margin-right: 14px;
}

.author .muted i{
	margin-right: 5px;
}

#sidebar {
	width: 290px;
	float: right;
	overflow: hidden;
}

#sidebar .widget {
	background: #FFF;
	padding: 16px;
	margin-top: 12px;
}

#sidebar .widget ul {
	list-style: none;
}

#sidebar .widget ul li a {
	display: block;
	line-height: 28px;
	height: 28px;
}

footer {
	width: 100%;
	background: gray;
}

footer #copyright {
	width: 1200px;
	margin: auto;
	height: 40px;
	line-height: 40px;
	text-align: center;
	overflow: hidden;
}

</style>
</head>
<body>
	<header id="main-header">
		<h1>The Blog</h1>
	</header>
	<nav id="nav">
		<ul>
			<li class="selected"><a href="#">首页</a></li>
			<li><a href="1">Python</a></li>
			<li><a href="3">C++</a></li>
			<li><a href="#">资讯</a></li>
			<li><a href="#">关于我</a></li>
		</ul>
	</nav><!-- end nav -->
	<div class="content-wrap">
		<jsp:include page="${mainPage }"></jsp:include>
		<aside id="sidebar">
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
		</aside><!-- end sidebar -->
	</div><!-- end content-wrap -->
	<footer>
		<div id="copyright">
			<span>Copyright © 2014-2016 <a href="#" title="test"
				target="_blank">test</a>版权所有
			</span>
		</div>
	</footer>
</body>
</html>