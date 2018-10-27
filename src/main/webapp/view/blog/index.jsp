<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style>
</style>
<div class="layui-row index-content" >
	<h3 class="cat-title">今日推荐</h3>
	<div class="layui-col-md6">
	<c:forEach end="0" var="blog" items="${todayBlogList }">
		<div class="first-post">
			<div class="thumb">
				<a class="focus" href="<%=basePath %>/b/${blog.id }.html">
					<img src="${blog.thumb }" alt="${blog.title }">
				</a>
			</div>
			<div class="title">
				<h2>
					<a href="#" title="${blog.title }" target="_blank">${blog.title }</a>
				</h2>
			</div>
			<div class="meta">
				<span><i class="fa fa-clock-o"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.createTime}"/></span>
				<span><i class="fa fa-eye"></i>阅读(${blog.click })</span>
			</div>
			<div class="summary">&nbsp;&nbsp;&nbsp;&nbsp;${blog.summary }</div>
			<div class="readmore">
				<a href="<%=basePath%>/b/${blog.id }.html">阅读全文&gt;&gt;</a>
			</div>
		</div>
	</c:forEach>
	</div>
	<div class="layui-col-md6">
		<div class="last-post-list">
			<c:forEach begin="1" end="4" var="blog" items="${todayBlogList }">
				<div class="other-post">
					<div class="thumb">
						<a class="focus" href="<%=basePath %>/b/${blog.id }.html">
							<img src="${blog.thumb }" alt="${blog.title }">
						</a>
					</div>
					<div class="info">
						<div class="title">
							<h2>
								<a href="#" title="${blog.title }" target="_blank">${blog.title }</a>
							</h2>
						</div>
						<div class="meta">
							<span><i class="fa fa-clock-o"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.createTime}"/></span>
							<span><i class="fa fa-eye"></i>阅读(${blog.click })</span>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript">
</script>