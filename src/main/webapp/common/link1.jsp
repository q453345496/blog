<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/layui/css/layui.css">
<script src="<%=path%>/resources/jquery-1.12.3.min.js"></script>
<script src="<%=path%>/resources/layui/layui.js"></script>
<script src="<%=path%>/resources/js/jqpaginator.min.js"></script>
<script type="text/javascript">
	var basePath = "<%=path%>";
	$(function(){
		function addLink() {
		    var body_element = document.getElementsByTagName('body')[0];
		    var selection;
			if(window.getSelection){//DOM,FF,Webkit,Chrome,IE10
				selection = window.getSelection();
				alert("文字复制成功！转载请注明出处：" + document.location.href);
	
			}else if(document.getSelection){//IE10
				selection= document.getSelection();
				alert("文字复制成功！转载请注明出处：" + document.location.href);
	
			}else if(document.selection){//IE6+10-
				selection= document.selection.createRange().text;
				alert("文字复制成功！转载请注明出处："  +document.location.href);
			}else{
				selection= "";
				alert("浏览器兼容问题导致复制失败！");
			}
		    var pagelink = "<br /><br /> 转载请注明来源: <a href='" + document.location.href + "'>" + document.location.href + "</a>"; 
		    var copy_text = selection + pagelink;
		    var new_div = document.createElement('div');
		    new_div.style.left='-99999px';
		    new_div.style.position='absolute';
		    body_element.appendChild(new_div );
		    new_div.innerHTML = copy_text ;
		    selection.selectAllChildren(new_div );
		    window.setTimeout(function() {
		        body_element.removeChild(new_div );
		    },0);
		}
		document.body.oncopy = addLink;
	});
</script>
