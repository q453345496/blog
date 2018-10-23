<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style>
</style>
		
<div class="post">
	<div class="post-header">
		<h2 class="post-title">${blog.title }</h2>
		<div class="post-meta">
			<div class="item">
				<i class="fa fa-calendar"></i><span>发布时间：<fmt:formatDate pattern="yyyy-MM-dd" value="${blog.createTime}"/></span>
			</div>
			<div class="item">
				<i class="fa fa-folder-open"></i><span>分类：<a href="<%=basePath%>/t/${blog.typeCode }">${blog.typeName }</a></span>
			</div>
			<div class="item">
				<i class="fa fa-eye"></i><span>阅读(${blog.click })</span>
			</div>
		</div>
	</div><!-- post-header -->
	<div id="doc-content" class="post-content">
		<textarea style="display:none;">${fn:replace(blog.content, "&#124;", "&amp;#124;") }</textarea>
	</div><!-- post-content -->
	
	<div class="post-copyright">
		未经允许不得转载：<a href="<%=basePath%>/b/${blog.id }.html">${blog.title }</a>
	</div>
	
	<div class="post-nav">
		<c:choose>
			<c:when test="${lastBlog == null}">
				<a class="post-nav-pre" href="javascript:;">上一篇<br>没有了</a>
			</c:when>
			<c:otherwise>
				<a class="post-nav-pre" href="<%=basePath%>/b/${lastBlog.id }.html">上一篇<br>${lastBlog.title }</a>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${nextBlog == null}">
				<a class="post-nav-next" href="javascript:;">下一篇<br>没有了</a>
			</c:when>
			<c:otherwise>
				<a class="post-nav-next" href="<%=basePath%>/b/${nextBlog.id }.html">下一篇<br>${nextBlog.title }</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="post-recommend">
		<div class="title">
			<h3>相关推荐</h3>
		</div>
		<ul>
			<c:forEach var="blog" items="${relateBlogList }">
				<li><a href="<%=basePath %>/b/${blog.id }.html">${blog.title }</a></li>
			</c:forEach>
		</ul>
	</div>
</div><!-- post -->
<script src="<%=path%>/resources/editor/editormd.min.js"></script>
<script src="<%=path%>/resources/editor/lib/all.js"></script>
<%-- <script src="<%=path%>/resources/editor/lib/flowchart.min.js"></script> --%>
<%-- <script src="<%=path%>/resources/editor/lib/jquery.flowchart.min.js"></script> --%>
<%-- <script src="<%=path%>/resources/editor/lib/marked.min.js"></script> --%>
<%-- <script src="<%=path%>/resources/editor/lib/prettify.min.js"></script> --%>
<%-- <script src="<%=path%>/resources/editor/lib/raphael.min.js"></script> --%>
<%-- <script src="<%=path%>/resources/editor/lib/underscore.min.js"></script> --%>
<%-- <script src="<%=path%>/resources/editor/lib/sequence-diagram.min.js"></script> --%>

<script type="text/javascript">
var testEditor;
$(function () {
	editormd.defaults.path = '<%=path%>/resources/editor/lib/';//坑爹的路径
	testEditor = editormd.markdownToHTML("doc-content",{
		//markdown: res.data.content,
		//htmlDecode : true,
		htmlDecode : "style,script,sub,sup|on*",
		emoji : true,
		taskList : true,
		tex : true, // 默认不解析
		flowChart : true, // 默认不解析
		sequenceDiagram : true, // 默认不解析
	});
});
</script>