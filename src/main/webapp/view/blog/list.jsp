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
<div class="post-list">
	<c:forEach var="blog" items="${blogs}">
	<div class="post-item">
		<div class="thumb">
			<a class="focus" href="<%=basePath %>/b/${blog.id }.html">
				<img src="${blog.thumb }" alt="${blog.title }">
			</a>
		</div>
		<div class="info">
			<div class="title">
				<h2>
					<a href="<%=basePath %>/b/${blog.id }.html" title="${blog.title }" target="_blank">${blog.title }</a>
				</h2>
			</div>
			<div class="meta">
				<span><i class="fa fa-clock-o"></i><fmt:formatDate pattern="yyyy-MM-dd" value="${blog.createTime}"/></span>
				<span><i class="fa fa-list-alt"></i><a href="<%=basePath%>/t/${blog.typeCode }">${blog.typeName}</a></span>
				<span><i class="fa fa-eye"></i>阅读(${blog.click })</span>
			</div>
			<div class="summary">
				&nbsp;&nbsp;&nbsp;&nbsp;${blog.summary }</div>
			<div class="readmore">
				<a href="<%=basePath%>/b/${blog.id }.html">阅读全文&gt;&gt;</a>
			</div>
		</div>
	</div>
	</c:forEach>
	<div id="pagination" class="pagination">
	</div>
</div>
<script type="text/javascript">
var pathname = location.pathname;
layui.use('laypage', function(){
	var laypage = layui.laypage;
	laypage.render({
	    elem: 'pagination',
	    count: ${page.total},
	    curr: ${page.current},
	    theme: '#1E9FFF',
	    first: "首页",
	    last: "尾页",
	    jump: function(obj, first){
	    	if(!first){
				var href = location.pathname + '?';
				href += '&page=' + obj.curr;
				console.log(href)
				location.href = href;
	    	}
	    }
	});
});
</script>