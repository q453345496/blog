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
<div class="search-result">
	<div class="result-status">
		为您找到相关记录<strong>${page.total}</strong>条
	</div>
	<c:choose>
		<c:when test="${empty blogs }">
			<div class="result-error">
				未查询到结果，请换个关键字试试看！
			</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="blog" items="${blogs }">
			<div class="result-item">
				<h3><a href="<%=basePath%>/b/${blog.id }.html" title="HTML5-语义化" target="_blank">${blog.title }</a></h3>
				<p class="url"><%=basePath%>/b/${blog.id }.html</p>
				<p class="summary">${blog.summary }</p>
				<p class="date">发布于&nbsp;2018-09-29</p>
			</div>
			</c:forEach>
			<div id="search-page" class="pagination"></div>
		</c:otherwise>
	</c:choose>
</div>
<c:if test="${page.total!=0 }">
<script type="text/javascript">
layui.use('laypage', function(){
	  var laypage = layui.laypage;
	  laypage.render({
		    elem: 'search-page',
		    count: ${page.total},
		    curr: ${page.current},
		    theme: '#1E9FFF',
		    first: "首页",
		    last: "尾页",
		    jump: function(obj, first){
		    	if(!first){
					var href = location.pathname +'?kw=${kw}';
					href += '&page=' + obj.curr;
					console.log(href)
					location.href = href;
		    	}
		    }
	  });
	});
</script>
</c:if>