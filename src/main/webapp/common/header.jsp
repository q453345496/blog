<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.pull-right{
	float: right !important;
}

.pull-left{
	float: left !important;
}
/* start header */
.header{
	background-color: #FFF;
 	position:relative;
 	margin-bottom: 15px;
    border-bottom: 1px solid #EAEAEA;
}

.header-wrap{
	display: flex;
	align-items: center;
	justify-content: flex-start;
}

.header-nav{
	margin-right: auto;
}

.header-nav li.layui-this:after,
.header-nav .layui-nav-bar{
	background-color: #C1E4FF;
}
.header-nav .layui-nav-more{
	border-top-color: #000;
}

.header-nav .layui-nav-mored{
	border-color: transparent transparent #000;
}
.header-nav ul{
	background:transparent;
}
.header-nav .layui-nav a {
	font-size: 16px;
	color: #000;
}
.header-nav .layui-nav a:hover {
	color: #000;
}
/* end header */

/* start search-form */
.search-form{
	display: flex;
}

.search-form input{
    margin-right: .5rem!important;
}

.search-form input:focus{
    color: #495057;
    background-color: #fff;
    border-color: #80bdff;
    outline: 0;
    box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25);
}
/* end search-form */

</style>
<header class="layui-header header">
	<div class="layui-container header-wrap">
		<h1 class="logo">
			<a href="index.html">
				<img src="../resources/images/logo.png" alt="" class="logo-img">
			</a>
		</h1>
		<div class="header-nav">
		<ul class="layui-nav">
			<li class="layui-nav-item">
				<a href="index.html">首页</a>
			</li>
			<c:forEach var="type" items="${typeList }">
				<li class="layui-nav-item">
					<a href="<%=basePath%>/t/${type.code }">${type.name }</a>
				</li>
				<c:if test="${not empty type.subs }">
					<dl class="layui-nav-child">
							<c:forEach var="sub" items="${type.subs }">
								<dd><a href="<%=basePath%>/t/${sub.code }">${sub.name }</a></dd>
							</c:forEach>
					</dl>
				</c:if>
			</c:forEach>
			  <li class="layui-nav-item">
			  	<a href="message.html">留言</a>
				<dl class="layui-nav-child"> <!-- 二级菜单 -->
				  <dd><a href="">移动模块</a></dd>
				  <dd><a href="">后台模版</a></dd>
				  <dd><a href="">电商平台</a></dd>
				</dl>
			  </li>
			  <li class="layui-nav-item">
			  	<a href="about.html">关于</a>
			  </li>
			</ul>
		</div>
		<form class="layui-form search-form" action="${path}/search">
		     <input type="text" name="kw" lay-verify="kw" autocomplete="off" class="layui-input" placeholder="请输入关键字" value="${kw}">
		    <button class="layui-btn layui-btn-normal" type="submit">搜索</button>
		</form>
	</div>
</header>
<script>
layui.use(['element', 'form'], function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

});
</script>