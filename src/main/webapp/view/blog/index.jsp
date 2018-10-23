<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style>
.index-content{
	background-color: #fff;
	box-shadow: 0px 0px 3px #ccc;
	margin-bottom: 10px;
}
.index-content>*{
	padding: 10px;
}
.cat-title{
	color: #0099cc;
	border-bottom: 1px solid #e8e8e8;
	font-size: 16px;
	padding-bottom: 5px;
}

.index-content .title h2{
	font-size: 1.1rem;
	font-weight: 300;
}
.index-content .title h2 a{
	color: #555;
}
.index-content .title h2 a:hover{
	color: #45B6F7;
}
.index-content .meta{
	font-size:12px;
	color: #aaa;
}
.index-content .meta span{
	margin-right: 20px;
}
.index-content .meta i{
	margin-right: 4px;
}

.first-post .thumb{
	margin-bottom: 10px;
}
.first-post .thumb img{
	width: 100%;
	height: 200px;
}
.first-post .summary{
	margin-bottom: 10px;
	font-size:12px;
	text-align: justify;
}
.other-post{
	display: flex;
	margin-bottom: 10px;
}
.other-post .thumb{
	margin-right: 12px;
}
.other-post .thumb a {
	margin-right: 12px;
}
.other-post .thumb img{
	width: 100px;
	height: 100px;
}

</style>
<div class="layui-row index-content" >
	<h3 class="cat-title">推荐阅读</h3>
	<div class="layui-col-md6">
	<c:forEach end="0" var="blog" items="${hotBlogList }">
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
			<c:forEach begin="1" end="4" var="blog" items="${hotBlogList }">
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