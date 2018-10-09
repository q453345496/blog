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
.post {
	background-color: #fff;
}
.post-header {
    margin-bottom: 20px;
    padding: 25px 0;
    border-bottom: 1px solid #eee;
    text-align: center;
}
.post-meta {
    font-size: 12px;
    color: #999;
}
.post-meta .item {
	display: inline-block;
	margin-right: 20px;
}
.post-meta .item i{
	margin-right: 4px;
}
.post-meta a{
	text-decoration: underline;
}
.post-title {
	font-size: 26px;
    line-height: 36px;
}
.post-ad-footer strong {
	color: #FD6A5E;
}
.post-ad-footer a{
	border-bottom: solid 2px #eee;
}
.post-copyright {
    background-color: #45B6F7;
    color: #fff;
    margin-top: 15px;
    margin-bottom: 30px;
    padding: 5px;
    font-size: 12px;
    text-align: center;
}
.post-copyright a{
    color: #fff;
}
.post-nav{
	margin-bottom:30px;
	display: flex;
	justify-content: space-between;
	overflow: hidden;
}
.post-nav a{
	display:block;
	position: relative;
	width: 50%;
	color: #aaa;
}
.post-nav a:hover{
	color: #45B6F7;
}
.post-nav-pre{
	text-align: left;
}
.post-nav-next{
	text-align: right;
}
.post-nav-next:before{
	content: '';
    position: absolute;
    height: 100%;
    display: block;
    width: 1px;
    background-color: #eee;
}
.post-recommend .title{
    border-bottom: 1px solid #eaeaea;
}
.post-recommend .title{
    border-bottom: 1px solid #eaeaea;
}
.post-recommend .title h3{
	font-size: 1.3rem;
}
.post-recommend ul{
	padding: 12px 20px;
	color: #bbb;
}
</style>
		
<div class="post p-4">
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
	
<!-- 	<div class="post-ad-footer"> -->
<!-- 		<b>AD：</b><strong>【专业网站开发】</strong> -->
<!-- 		<a target="_blank" href="#">7年网站开发经验 / 高端大气用户体验 / 全设备自适应兼容 / 免费靠谱工单售后</a> -->
<!-- 	</div> -->
	
	<div class="post-copyright">
		未经允许不得转载：<a href="<%=basePath%>/blog/${blog.id }.html">${blog.title }</a>
	</div>
	
	<div class="post-nav">
		<c:choose>
			<c:when test="${lastBlog == null}">
				<a class="post-nav-pre" href="javascript:;">上一篇<br>没有了</a>
			</c:when>
			<c:otherwise>
				<a class="post-nav-pre" href="<%=basePath%>/blog/${lastBlog.id }.html">上一篇<br>${lastBlog.title }</a>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${nextBlog == null}">
				<a class="post-nav-next" href="javascript:;">下一篇<br>没有了</a>
			</c:when>
			<c:otherwise>
				<a class="post-nav-next" href="<%=basePath%>/blog/${nextBlog.id }.html">下一篇<br>${nextBlog.title }</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="post-recommend">
		<div class="title">
			<h3>相关推荐</h3>
		</div>
		<ul>
			<li><a href="#">中国首届React开发者大会 8月18日 广州举行</a></li>
			<li><a href="#">HTML5-语义化</a></li>
			<li><a href="#">Windows node版本管理器–nvm</a></li>
			<li><a href="#">canvas小tip–下载二维码、图片加水印</a></li>
			<li><a href="#">分享一个生成二维码的插件–QRCode.js</a></li>
			<li><a href="#">图表库ECharts的使用</a></li>
			<li><a href="#">git常用命令</a></li>
			<li><a href="#">css print</a></li>
		</ul>
	</div>
</div><!-- post -->
<link rel="stylesheet" href="<%=path%>/resources/editor/css/editormd.min.css" />
<script src="<%=path%>/resources/editor/editormd.min.js"></script>
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
// 	$.get("/admin/blog/${blog.id }",function(res){
		
		testEditor = editormd.markdownToHTML("doc-content",{
// 			markdown: res.data.content,
			//htmlDecode : true,
			htmlDecode : "style,script,sub,sup|on*",
			emoji : true,
			taskList : true,
			tex : true, // 默认不解析
			flowChart : true, // 默认不解析
			sequenceDiagram : true, // 默认不解析
		});
// 	})
});
</script>