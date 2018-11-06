<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
</style>
<header class="layui-header header">
	<div class="layui-container header-wrap">
		<h1 class="logo">
			<a href="<%=basePath%>/index.html">
				<img src="<%=basePath%>/resources/images/logo.png" alt="" class="logo-img">
			</a>
		</h1>
		<div class="header-nav">
		<ul class="layui-nav">
			<li class="layui-nav-item">
				<a href="<%=basePath%>/index.html">首页</a>
			</li>
			<c:forEach var="nav" items="${navList }">
				<li class="layui-nav-item">
					<a href="<%=basePath%>/t/${nav.code }">${nav.name }</a>
					<c:if test="${not empty nav.subs }">
						<dl class="layui-nav-child">
								<c:forEach var="sub" items="${nav.subs }">
									<dd><a href="<%=basePath%>/t/${sub.code }">${sub.name }</a></dd>
								</c:forEach>
						</dl>
					</c:if>
				</li>
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
		<form class="layui-form search-form" action="<%=path%>/search">
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