<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<body><%
	String path = request.getContextPath();
%>
<script src="<%=path%>/resources/jquery-1.12.3.min.js"></script>
	<h2>Hello ${blog.title} !</h2>
	<p>${blog.content}</p>
	<span id="hehe"></span>
	<script type="text/javascript">  
	    $.ajax({
	        url:"<%=path%>/admin/blog/1",
	        dataType:'jsonp',  
	        jsonp:'callback',  
	        success:function(result) {  
	        	console.log(result)
	        	$("#hehe").html(result.data.content)
	        },  
	        timeout:3000  
	    });  
</script>  
</body>
</html>
