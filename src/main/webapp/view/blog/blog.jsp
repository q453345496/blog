<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
%>
<style>
.blog-detail{
	background:#fff;
	width: 100%;
	padding: 16px;
	margin-top: 12px;
}
.blog-detail .info {
    text-align: center;
    border-bottom: 1px solid red;
    padding-bottom: 24px;
}
.blog-detail .info .title h3{
	font-size: 20px;
	margin: 0 0 10px 0;
}
.blog-detail .content{
	padding: 10px 20px;
}
</style>
<div class="main-content">
	<div class="blog-detail">
		<div class="info">
			<div class="title">
				<h3>
					<a title="${blog.title }" href="/blog/${blog.id }.html">${blog.title }</a>
				</h3>
			</div>
			<div class="author">
				<span class="muted"><i class="fa fa-list-alt"></i><a href="/${blog.typeId }">${blog.typeName}</a></span>
	<!-- 							<span class="muted"><i class="fa fa-user"></i>作者：test</span> -->
				<span class="muted"><i class="fa fa-clock-o"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.createTime}"/></span>
				<span class="muted"><i class="fa fa-eye"></i>浏览（${blog.click }）</span>
			</div>
		</div>
		<div id="doc-content" class="content">
			<textarea style="display:none;">${blog.content }</textarea>
		</div>
		<div class="keyword"></div>
	</div>
</div>
<link rel="stylesheet" href="<%=path%>/resources/editor/css/editormd.min.css" />
<script src="<%=path%>/resources/editor/editormd.js"></script>
<script src="<%=path%>/resources/editor/lib/marked.min.js"></script>
<script src="<%=path%>/resources/editor/lib/prettify.min.js"></script>
<script src="<%=path%>/resources/editor/lib/flowchart.min.js"></script>
<script src="<%=path%>/resources/editor/lib/raphael.min.js"></script>
<script src="<%=path%>/resources/editor/lib/underscore.min.js"></script>
<script src="<%=path%>/resources/editor/lib/sequence-diagram.min.js"></script>
<script src="<%=path%>/resources/editor/lib/flowchart.min.js"></script>
<script src="<%=path%>/resources/editor/lib/jquery.flowchart.min.js"></script>

<script type="text/javascript">
    var testEditor;
    $(function () {
        testEditor = editormd.markdownToHTML("doc-content",{
// 			htmlDecode : true,
			htmlDecode : "style,script,sub,sup|on*",
            emoji           : true,
            taskList        : true,
            tex             : true,  // 默认不解析
            flowChart       : true,  // 默认不解析
            sequenceDiagram : true,  // 默认不解析
        });
    });
 </script>