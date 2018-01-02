<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="blog-list">
	<div class="blog-item">
		<div class="blog-title">
			<h3>
				<a title="${blog.title }" href="/blog/${blog.id }.html"
					target="_blank">${blog.title }</a>
			</h3>
		</div>
		<div class="blog-author">
			<span class="muted"><i class="fa fa-list-alt"></i><a href="/${blog.blogType.id }">${blog.blogType.name}</a></span>
<!-- 							<span class="muted"><i class="fa fa-user"></i>作者：test</span> -->
			<span class="muted"><i class="fa fa-clock-o"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.createTime}"/></span>
			<span class="muted"><i class="fa fa-eye"></i>浏览（${blog.click }）</span>
		</div>
		<div class="blog-content">
			${blog.content }
		</div>
		<div class="keyword"></div>
	</div>
</div>
