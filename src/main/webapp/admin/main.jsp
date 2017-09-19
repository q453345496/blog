<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/link.jsp"></jsp:include>
<title>时间就是金钱的地盘</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">

	<div data-options="region:'north',border:false" style="height: 50px;margin: 5px">
		<h3 style="color:#0099FF;">在我的地盘就要听我的</h3>
	</div>
	
	<div data-options="region:'west',split:true,title:'菜单导航'" style="width: 150px;">
	 	<div class="easyui-accordion" data-options="fit:true,border:false">
	 		<div title="系统管理" style="padding:10px">
				<a href="#" onclick="openTab('参数管理','<%=path%>/admin/param/param.jsp','fa fa-tags')" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-tags'">参数管理</a>
				<a href="#" onclick="openTab('参数类型管理','<%=path%>/admin/param/paramType.jsp','fa fa-tags')" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-tags'">参数类型管理</a>
			</div>
			<div title="权限管理" style="padding:10px">
				<a href="#" onclick="openTab('角色管理','<%=path%>/admin/role/role.jsp','fa fa-tags')" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-tags'">角色管理</a>
			</div>
			<div title="理财管理" style="padding:10px">
				<a href="#" onclick="openTab('支出分类','<%=path%>/admin/journalCategory/journalCategory.jsp','fa fa-tags')" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-tags'">支出分类</a>
				<a href="#" onclick="openTab('收入分类','<%=path%>/admin/incomeCategoryManager.jsp','fa fa-tags')" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-tags'">收入分类</a>
			</div>
			<div title="博客管理" style="padding:10px">
				<a href="#" onclick="openTab('文章列表','<%=path%>/admin/blog/blogManager.jsp','fa fa-file-word-o')" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-file-word-o'">文章列表</a>
				<a href="#" onclick="openTab('文章分类管理','<%=path%>/admin/blogType/blogTypeManager.jsp','fa fa-tags')" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-tags'">文章分类管理</a>
			</div>
			
			<div title="帮助" style="padding:10px">
				<p>ing on a panel heaers can define a panel to be selected.</p>         
			</div>
		</div>
	</div>
	
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="首页" data-options="iconCls:'fa fa-home fa-fw'">
<!-- 				<div align="center" style="padding-top: 100px"><font color="red" size="10">在最需要奋斗的年华里...</font></div> -->
			</div>
		</div>
	</div>
	
	<div data-options="region:'south',border:false" style="height: 40px; margin: 5px;" align="center">
		<p>
			Copyright © 2014-2016 <a href="#" title="时间就是金钱" target="_blank">时间就是金钱</a>版权所有
		</p>
	</div>
</div>
<script>
	
</script>
</body>
</html>
