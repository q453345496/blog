<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
</style>
<jsp:include page="list.jsp"></jsp:include>
<div id="pagination" class="pagination">
</div>
<script type="text/javascript">
layui.use('laypage', function(){
	var laypage = layui.laypage;
	laypage.render({
	    elem: 'pagination',
	    count: 100,
	    curr: 1,
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