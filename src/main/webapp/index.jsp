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

#main-content {
	width: 1200px;
	margin: 0 auto;
	overflow: hidden;
}

#blog-list {
	width: 900px;
	float: left;
	overflow: hidden;
}

.blog-item {
	background:#fff;
	width: 100%;
	padding: 16px;
	margin-bottom: 12px;
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
	height: 113px;
}

.blog-item .summary .readmore {
}

.blog-item .summary .readmore a {
	color: red;
	text-decoration: underline;
}

.blog-item .author {
	color: #999;
}

.blog-item .author .muted {
	margin-right: 14px;
}

.blog-item .author .muted i{
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
	margin-bottom: 12px;
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
			<li><a href="#">文章</a></li>
			<li><a href="#">读书</a></li>
			<li><a href="#">资讯</a></li>
			<li><a href="#">关于我</a></li>
		</ul>
	</nav><!-- end nav -->
	<div id="main-content">
		<div id="blog-list">
			<div class="blog-item">
				<div class="thumb">
					<a title="腾讯“微保WeSure”上线微信 目前仅面向1%用户">
						<img src="<%=path%>/resources/images/koala.jpg" alt="腾讯“微保WeSure”上线微信 目前仅面向1%用户">
					</a>
				</div>
				<div class="info">
					<div class="title">
						<h3>
							<a title="腾讯“微保WeSure”上线微信 目前仅面向1%用户" href="/itnews/18269.html"
								target="_blank">腾讯“微保WeSure”上线微信 目前仅面向1%用户</a>
						</h3>
					</div>
					<div class="summary">
						<p>11月2日，腾讯正式上线了旗下保险平台“微保WeSure”，这也是腾讯第一家控股保险平台。......
						</p>
						<p class="readmore">
							<a title="腾讯“微保WeSure”上线微信 目前仅面向1%用户" href="/itnews/18269.html"
								target="_blank">阅读全文&gt;&gt;</a>
						</p>
					</div>
					<div class="author">
						<span class="muted"><i class="fa fa-list-alt"></i><a href="/itnews/">IT资讯</a></span>
						<span class="muted"><i class="fa fa-user"></i>作者：test</span>
						<span class="muted"><i class="fa fa-clock-o"></i>17-11-03</span>
						<span class="muted"><i class="fa fa-eye"></i>浏览（22）</span>
					</div>
				</div>
			</div>
			<div class="blog-item">
				<div class="thumb">
					<a title="谷歌即将关闭机票搜索软件QPX Express">
						<img src="<%=path%>/resources/images/cat.jpg" alt="谷歌即将关闭机票搜索软件QPX Express">
					</a>
				</div>
				<div class="info">
					<div class="title">
						<h3>
							<a title="谷歌即将关闭机票搜索软件QPX Express" href="/itnews/18268.html"
								target="_blank">谷歌即将关闭机票搜索软件QPX Express</a>
						</h3>
					</div>
					<div class="summary">
						<p>北京时间11月3日早间消息，谷歌即将关闭软件工具QPX
							Express。该工具帮助小企业获取机票搜索信息。这可能会对在线旅行行业的新进入者造成冲击。......</p>
						<p class="readmore">
							<a title="谷歌即将关闭机票搜索软件QPX Express" href="/itnews/18268.html"
								target="_blank">阅读全文&gt;&gt;</a>
						</p>
					</div>
					<div class="author">
						<span class="muted"><i class="fa fa-list-alt"></i><a href="/itnews/">IT资讯</a></span>
						<span class="muted"><i class="fa fa-user"></i>作者：test</span>
						<span class="muted"><i class="fa fa-clock-o"></i>17-11-03</span>
						<span class="muted"><i class="fa fa-eye"></i>浏览（22）</span>
					</div>
				</div>
			</div>
			<div class="blog-item">
				<div class="thumb">
					<a title="瑞银：比特币疯涨是郁金香泡沫破灭前的狂热">
						<img src="<%=path%>/resources/images/koala.jpg" alt="瑞银：比特币疯涨是郁金香泡沫破灭前的狂热">
					</a>
				</div>
				<div class="info">
					<div class="title">
						<h3>
							<a title="瑞银：比特币疯涨是郁金香泡沫破灭前的狂热" href="/itnews/18267.html"
								target="_blank">瑞银：比特币疯涨是郁金香泡沫破灭前的狂热</a>
						</h3>
					</div>
					<div class="summary">
						<p>北京时间3日早间CNBC称，全球最大期货交易商芝加哥商品交易所集团（CME）计划年底前推出比特币期货，受此推动，过去两天比特币价格一路暴涨，从不到6400美元狂飙至7000美元以上，一度突破7300美元。......</p>
						<p class="readmore">
							<a title="瑞银：比特币疯涨是郁金香泡沫破灭前的狂热" href="/itnews/18267.html"
								target="_blank">阅读全文&gt;&gt;</a>
						</p>
					</div>
					<div class="author">
						<span class="muted"><i class="fa fa-list"></i><a href="/itnews/">IT资讯</a></span>
						<span class="muted"><i class="fa fa-user"></i>作者：test</span>
						<span class="muted"><i class="fa fa-clock-o"></i>17-11-03</span>
						<span class="muted"><i class="fa fa-eye"></i>浏览（22）</span>
					</div>
				</div>
			</div>
		</div><!-- end blog-list -->
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
					<li><a href="#">JAVA</a></li>
					<li><a href="#">HTML/CSS</a></li>
					<li><a href="#">Javascript</a></li>
					<li><a href="#">MySQL</a></li>
					<li><a href="#">Python</a></li>
				</ul>
			</section>
		</aside><!-- end sidebar -->
	</div><!-- end main-content -->
	<footer>
		<div id="copyright">
			<span>Copyright © 2014-2016 <a href="#" title="test"
				target="_blank">test</a>版权所有
			</span>
		</div>
	</footer>
</body>
</html>