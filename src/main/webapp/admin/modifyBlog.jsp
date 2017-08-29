<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../common/link.jsp"></jsp:include>
<script src="<%=path%>/resources/ueditor/ueditor.config.js"></script>
<script src="<%=path%>/resources/ueditor/ueditor.all.min.js"> </script>
<script src="<%=path%>/resources/my-ueditor.js"> </script>
<script src="<%=path%>/resources/ueditor/lang/zh-cn/zh-cn.js"> </script>
<title>写博客</title>
<style>
.blog{
	padding:10px;
	text-align:right;
	display:inline-block;
	width:60px;
}
</style>
</head>
<body>
<div id="blogDetail" class="easyui-panel" title="文章内容" data-options="fit:true" style="padding: 20px;">
	<div>
		<input id="blogId" name="id" type="hidden" />
		<span class="blog">文章标题：</span>
		<input id="title" name="title" type="text" class="easyui-textbox" data-options="prompt:'文章标题'" />
		<span class="blog">关键字：</span>
		<input id="keyWord" name="keyWord" type=text class="easyui-textbox" />
	</div>
	<div>
		<span class="blog">分类：</span>
		<input id="blogTypeId" name="blogType.id" type="text" />
		<span class="blog">权限：</span>
		<select id="rightType" name="rightType" class="easyui-combobox" data-options="editable:false">
			<option value="0">公开</option>
			<option value="1">自己可见</option>
		</select>
	</div>

	<div>
		<script id="content" name="content" style="width:98%;height:240px"></script>
	</div>
	<div align="center" style="padding:5px">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="saveBlog()" style="width:90px">保存</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="closeBlog()" style="width:90px">关闭</a>
	</div>

</div>
<script>
$(function() {
    $.getJSON("<%=path%>/admin/blogType/list",function(result){
    	if(result.rows.length > 0){
    		result.rows[0].selected=true
    	}
        $('#blogTypeId').combobox({
            valueField : 'id',
            textField : 'name',
            data : result.rows,
            editable : false
        });
    });
    
    <%if(id!=null){%>
   		ue.ready(function(){
	    	var id = <%=id%>
	    	$("#blogId").val(id)
	        $.getJSON("<%=path%>/admin/blog/"+id,function(result){
	        	var blog = result.data;
	        	$("#title").textbox('setValue',blog.title);
	        	$("#keyWord").textbox('setValue',blog.keyWord);
	        	$("#blogTypeId").combobox("setValue",blog.blogType.id);
	        	$("#rightType").combobox("setValue",blog.rightType);
	        	ue.setContent(blog.content);
	        });
	    });
    <%}%>
});

var ue = UE.getEditor('content',{});


function saveBlog(){
	var title = $("#title").val();
	var blogTypeId = $("#blogTypeId").combobox("getValue");
	var rightType = $("#rightType").combobox("getValue");
	var content = UE.getEditor('content').getContent()
	var keyWord=$("#keyWord").val();
	
	if(title==null || title==''){
		alert("请输入标题！");
	}else if(blogTypeId==null || blogTypeId==''){
		alert("请选择博客类别！");
	}else if(content==null || content==''){
		alert("请填写内容！");
	}else{
		$.post("<%=path%>/admin/blog/save",
				{	
					'id': $("#blogId").val(),
					'title' : title,
					'blogType.id' : blogTypeId,
					'rightType' : rightType,
					'contentNoTag' : UE.getEditor('content').getContentTxt(),
					'content' : content,
					'summary' : UE.getEditor('content').getContentTxt().substr(0,200),
					'keyWord' : keyWord
				},
				function(result){
					if(result.status==200){
						alert("博客发布成功！");
						closeBlog();
					}else{
						alert("博客发布失败！");
					}
				}, "json");
	}
}
	
function closeBlog(){
	window.parent.refreshTab("文章列表");
	window.parent.closeTab("写博客");
}

</script>

</body>
</html>
