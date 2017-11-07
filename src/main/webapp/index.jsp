<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="common/link.jsp"%>
<title>The Flower Blog</title>
<style>
#nav {
	position: relative;
	width: 100%;
	height: 40px;
	line-height: 40px;
	background: gray;
}

#nav ul {
	width: 1040px;
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
	width: 1040px;
	margin: 0 auto;
	overflow: hidden;
}

#blog-list {
	width: 760px;
	float: left;
	overflow: hidden;
}

#sidebar {
	width: 260px;
	float: right;
	overflow: hidden;
}

footer {
	width: 100%;
	background: gray;
}

footer #copyright {
	width: 1040px;
	margin: auto;
	height: 40px;
	line-height: 40px;
	text-align: center;
	overflow: hidden;
}

.readmore{
	margin: 10px;
}

.readmore a{
	color: red;
}
</style>
</head>
<body>
	<header>
		<h1>The Flower Blog</h1>
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
			<div class="list-item">
				<div class="title">
					<h2>
						<a title="腾讯“微保WeSure”上线微信 目前仅面向1%用户" href="/itnews/18269.html"
							target="_blank">腾讯“微保WeSure”上线微信 目前仅面向1%用户</a>
					</h2>
				</div>
				<div class="info">
					<p>11月2日，腾讯正式上线了旗下保险平台“微保WeSure”，这也是腾讯第一家控股保险平台。......</p>
					<p class="readmore">
						<a title="腾讯“微保WeSure”上线微信 目前仅面向1%用户" href="/itnews/18269.html"
							target="_blank">阅读全文&gt;&gt;</a>
					</p>
				</div>
				<div class="author">
					<p class="dateview">
						<span>17-11-03</span><span>编辑：test</span><span> 个人博客：[<a
							href="/itnews/">IT资讯</a>]
						</span>
					</p>
				</div>
			</div>
			<div class="list-item">
				<div class="title">
					<h2>
						<a title="谷歌即将关闭机票搜索软件QPX Express" href="/itnews/18268.html"
							target="_blank">谷歌即将关闭机票搜索软件QPX Express</a>
					</h2>
				</div>
				<div class="info">
					<p>北京时间11月3日早间消息，谷歌即将关闭软件工具QPX
						Express。该工具帮助小企业获取机票搜索信息。这可能会对在线旅行行业的新进入者造成冲击。......</p>
					<p class="readmore">
						<a title="谷歌即将关闭机票搜索软件QPX Express" href="/itnews/18268.html"
							target="_blank">阅读全文&gt;&gt;</a>
					</p>
				</div>
				<div class="author">
					<p class="dateview">
						<span>17-11-03</span><span>编辑：test</span><span> 个人博客：[<a
							href="/itnews/">IT资讯</a>]
						</span>
					</p>
				</div>
			</div>
			<div class="list-item">
				<div class="title">
					<h2>
						<a title="瑞银：比特币疯涨是郁金香泡沫破灭前的狂热" href="/itnews/18267.html"
							target="_blank">瑞银：比特币疯涨是郁金香泡沫破灭前的狂热</a>
					</h2>
				</div>
				<div class="info">
					<p>北京时间3日早间CNBC称，全球最大期货交易商芝加哥商品交易所集团（CME）计划年底前推出比特币期货，受此推动，过去两天比特币价格一路暴涨，从不到6400美元狂飙至7000美元以上，一度突破7300美元。......</p>
					<p class="readmore">
						<a title="瑞银：比特币疯涨是郁金香泡沫破灭前的狂热" href="/itnews/18267.html"
							target="_blank">阅读全文&gt;&gt;</a>
					</p>
				</div>
				<div class="author">
					<p class="dateview">
						<span>17-11-03</span><span>编辑：test</span><span> 个人博客：[<a
							href="/itnews/">IT资讯</a>]
						</span>
					</p>
				</div>
			</div>
		</div><!-- end blog-list -->
		<aside id="sidebar">
			<section>
				<header>
					<h3>Categories</h3>
				</header>
				<ul>
					<li><a href="#">Lorem ipsum dolor</a></li>
					<li><a href="#">Sit amet consectetur</a></li>
					<li><a href="#">Adipisicing elit sed</a></li>
					<li><a href="#">Do eiusmod tempor</a></li>
					<li><a href="#">Incididunt ut labore</a></li>
				</ul>
			</section>
			<section>
				<header>
					<h3>Archives</h3>
				</header>
				<ul>
					<li><a href="#">December 2008</a></li>
					<li><a href="#">January 2009</a></li>
					<li><a href="#">February 2009</a></li>
					<li><a href="#">March 2009</a></li>
					<li><a href="#">April 2009</a></li>
					<li><a href="#">May 2009</a></li>
					<li><a href="#">June 2009</a></li>
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