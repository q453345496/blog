<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
%>
<style>
.f404 {
	text-align: center;
}
</style>
<div class="f404">
	<img src="http://www.daqianduan.com/wp-content/themes/dux/img/404.png">
	<h1>404 . Not Found</h1>
	<h2>沒有找到你要的内容！</h2>
	<p>
		<a class="btn btn-primary" href="/">返回首页</a>
	</p>
</div>