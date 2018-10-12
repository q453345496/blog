<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.pull-right{
	float: right !important;
}

.pull-left{
	float: left !important;
}

.header{
 	background-color: #1E9FFF!important;
 	position:relative;
}
/* .logo{ */
/*     position: absolute; */
/*     left: 0; */
/*     top: 16px; */
/* } */

.search-box{
	position:relative;
	margin-left: 0; 
    min-height: auto;
}
.search-box i{
	position: absolute;
	color:#fff;
	width: 25px;
	height: 27px;
	line-height:27px;
	top: 9px;
	left: 15px;
	font-size: 20px;
	cursor: pointer;
}


.mobile-nav{
	display:none;
}
.pop-nav{
	width:100%;
	line-height:60px;
	position:absolute;
	left:0px;
	top:100px;
	text-align:center;
	background:#1e9fff;
	display:none;
	z-index: 2;
}
.pop-nav  li{
/* 	border-top: 2px solid rgba(255,255,255,0.08); */
}
.pop-nav li a{
/* 	display:block; */
/* 	height:100%; */
/* 	width:100%; */
/* 	font-size:20px; */
/* 	color:#fff; */
}
@media only screen and (max-width:  750px) {
	.header-nav{
		display:none;
	}
	.mobile-nav{
		display:block;
	}
	.header-wrap{
/* 		padding:0 20px 0 40px; */
	}
	.pop-nav{
		display:none;
	}
}
</style>
<%-- 	    <form class="form-inline my-2 my-lg-0" action="${path}/search"> --%>
<%-- 	      <input name="kw" class="form-control mr-sm-2" type="search" placeholder="请输入关键字" aria-label="Search" value="${kw }"> --%>
<!-- 	      <button class="btn btn-primary my-2 my-sm-0" type="submit">搜索</button> -->
<!-- 	    </form> -->
<header class="layui-header header">
	<div class="header-wrap">
		<h1 class="logo pull-left">
			<a href="index.html">
				<img src="../res/static/images/logo.png" alt="" class="logo-img">
				<img src="../res/static/images/logo-text.png" alt="" class="logo-text">
			</a>
		</h1>
		<form class="layui-form pull-left" action="${path}/search">
			<div class="layui-form-item search-wrap">
			    <div class="layui-input-block search-box">
			      <i class="layui-icon layui-icon-search"></i>
			      <input type="text" name="title" lay-verify="title" autocomplete="off"  class="layui-input">
			    </div>
			</div>
		</form>
		<div class="header-nav pull-right">
			<ul class="layui-nav pull-left">
			  <li class="layui-nav-item layui-this"><a href="index.html">首页</a></li>
			  <li class="layui-nav-item"><a href="message.html">留言</a></li>
			  <li class="layui-nav-item"><a href="about.html">关于</a></li>
			</ul>
		</div>
		<div class="mobile-nav pull-right">
			<a href="javascript:;">
				<i class="layui-icon layui-icon-more"></i>
			</a>
		</div>
	</div>
	<ul class="pop-nav">
		<li><a href="index.html">首页</a></li>
		<li><a href="message.html">留言</a></li>
		<li><a href="about.html">关于</a></li>
	</ul>
</header>
<script>
layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
});
</script>