<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main-content">
	<c:forEach var="blog" items="${blogs}">
		<div class="blog-item">
			<div class="thumb">
					<a title="${blog.title }">
						<img src="${blog.thumb }" alt="${blog.title }">
					</a>
			</div>
			<div class="info">
				<div class="title">
					<h3>
						<a title="${blog.title }" href="/blog/${blog.id }.html"
							target="_blank">${blog.title }</a>
					</h3>
				</div>
				<div class="summary">
					<p>${blog.summary }
					</p>
					<div class="readmore">
						<a title="${blog.title }" href="/blog/${blog.id }.html"
							target="_blank">阅读全文&gt;&gt;</a>
					</div>
				</div>
				<div class="author">
					<span class="muted"><i class="fa fa-list-alt"></i><a href="/${blog.blogType.id }">${blog.blogType.name}</a></span>
<!-- 							<span class="muted"><i class="fa fa-user"></i>作者：test</span> -->
					<span class="muted"><i class="fa fa-clock-o"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.createTime}"/></span>
					<span class="muted"><i class="fa fa-eye"></i>浏览（${blog.click }）</span>
				</div>
			</div>
		</div>
	</c:forEach>
</div><!-- end blog-list -->
