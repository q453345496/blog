<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.widget {
	background-color: #fff;
	margin-bottom: 15px;
	border: none;
	border-radius: 4px;
}
.widget a{
	text-decoration: none;
}
.widget ul{
	margin: 0px;
	padding: 0px;
	list-style: none;
}
.widget-title{
	background-color: skyblue;
	color: white;
	font-size: 15px;
	font-weight: 700;
	padding: 10px 15px;
}
.widget .item{
	overflow: hidden;
	border-radius: 4px;
}
.widget-ad-img{
	border: none;
}
.widget-ad-img img{
	max-width: 100%;
	height: 190px;/*不定义的话，有时候图片加载慢，会影响滚动显示的高度*/
}
.ad-text-01{
	display: block;
	padding: 0 15px 15px;
	border: 1px solid #eaeaea;
	border-radius: 4px;
	text-decoration: none;
}
.ad-text-01:hover{
	border-color: #428BCA;
}
.ad-text-01 strong{
	background-color: #428BCA;
	color: white;
	padding: 4px 15px;
}
.ad-text-01 h2{
    font-size: 20px;
    font-weight: normal;
    margin-top: 14px;
    color: #428BCA;
}
.ad-text-01 p{
    color: #999;
    margin-bottom: 0;
}

.ad-text-02:hover{
	border-color: #FF5E52;
}
.ad-text-02{
	display: block;
	padding: 0 15px 15px;
	border: 1px solid #eaeaea;
	border-radius: 4px;
	text-decoration: none;
}
.ad-text-02 strong{
	background-color: #FF5E52;
	color: white;
	padding: 4px 15px;
}
.ad-text-02 h2{
    font-size: 20px;
    font-weight: normal;
    margin-top: 14px;
    color: #FF5E52;
}
.ad-text-02 p{
    color: #999;
    margin-bottom: 0;
}

.widget-post{
 	border: 1px solid #eaeaea;
}

.widget-post li a{
	font-size: 14px;
	padding: 10px 15px;
	display: flex;
    border-bottom: solid 1px #eee;
}


.widget-post li a .thumb img{
	width: 120px;
	height: 80px;
}
.widget-post li a .content{
	padding: 10px;
	overflow: hidden;
	text-overflow: ellipsis;
    white-space: nowrap;
}
.widget-post li a .content .title{
    display: inline-block;
}
.widget-post li a .content .time{
	color: #999;
	font-size: 10px;
	display: block;
}
.widget-post a:hover {
    color: #45B6F7;
}
.widget-post p{
	margin-bottom: 0;
}
</style>

<!-- <div class="widget widget-ad widget-ad-img"> -->
<!-- 	<div class="item"> -->
<!-- 		<a href="http://www.daqianduan.com/go/keqqnext" target="_blank"><img src="http://www.daqianduan.com/wp-content/uploads/2018/08/next-1.png"></a> -->
<!-- 	</div> -->
<!-- </div> -->

<div class="widget widget-ad widget-ad-text">
	<div class="item">
		<a class="ad-text-01" href="http://themebetter.com/theme/dux" target="_blank">
			<strong>吐血推荐</strong>
			<h2>DUX主题 新一代主题</h2>
			<p>DUX Wordpress主题是大前端当前使用主题，是大前端积累多年Wordpress主题经验设计而成；更加扁平的风格和干净白色的架构会让网站显得内涵而出色...</p>
		</a>
	</div>
</div>

<div class="widget widget-ad widget-ad-text">
	<div class="item">
		<a class="ad-text-02" href="http://themebetter.com/theme/dux" target="_blank">
			<strong>还有谁？</strong>
			<h2>推荐云主机，建站更安全</h2>
			<p>大前端推荐站长使用阿里云主机，安全可靠、轻松上云，从此告别主机无售后、主机各种难题。点击领取1000元代金券</p>
		</a>
	</div>
</div>

<div class="widget widget-post">
	<h4 class="widget-title">最新发布</h4>
	<ul>
		<c:forEach var="blog" items="${lastBlogList }">
		<li>
			<a href="<%=basePath %>/b/${blog.id }.html">
				<div class="thumb">
					<img src="${blog.thumb }">
				</div>
				<div class="content">
					<p class="title">${blog.title }</p>
					<p class="time"><fmt:formatDate pattern="yyyy-MM-dd" value="${blog.createTime}"/></p>
				</div>
			</a>
		</li>
		</c:forEach>
	</ul>
</div>
<div class="widget widget-post">
	<h4 class="widget-title">推荐文章</h4>
	<ul>
		<c:forEach var="blog" items="${hotBlogList }">
		<li>
			<a href="<%=basePath %>/b/${blog.id }.html">
				<div class="thumb">
					<img src="${blog.thumb }">
				</div>
				<div class="content">
					<p class="title">${blog.title }</p>
					<p class="time"><fmt:formatDate pattern="yyyy-MM-dd" value="${blog.createTime}"/></p>
				</div>
			</a>
		</li>
		</c:forEach>
	</ul>
</div>

<script type="text/javascript">
// $(window).scroll(function(){
// 	var lastElement = $(".widget:last");
// 	var sideHeight = lastElement.outerHeight();//侧边栏的高度
// 	var sideWidth = lastElement.outerWidth();//侧边栏的宽度
// 	var position = lastElement.offset();
	
// 	var scrollTop = $(window).scrollTop();//滚动高度
// 	if(scrollTop > sideHeight + position.top){
// 		var ads = $(".widget-ad");
// 		var fromTop = 0;
// 		for(var i = 0 ; i < ads.length; i++){
// 			$(ads[i]).css({
// 				"position":"fixed",
// 				"left": position.left,
// 				"top": i == 0 ? 10 : 10 + fromTop,
// 				"width": sideWidth,
// 			});
// 			fromTop += $(ads[i]).height() + 15;
// 		}
// 	} else {
// 		var ads = $(".widget-ad");
// 		for(var i = 0 ; i < ads.length; i++){
// 			$(ads[i]).css({
// 				"position":"static",
// 			});
// 		}
// 	}
// })
</script>