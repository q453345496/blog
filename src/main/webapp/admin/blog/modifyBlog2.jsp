<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<link rel="stylesheet" href="<%=path%>/resources/editor/css/editormd.min.css" />
<script src="<%=path%>/resources/editor/editormd.js"></script>
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
		<input id="blogId" name="id" type="hidden" value="${id}"/>
		<input id="status" name="status" type="hidden"/>
		<span class="blog">文章标题：</span>
		<input id="title" name="title" type="text" class="easyui-textbox" data-options="prompt:'文章标题'" />
		<span class="blog">关键字：</span>
		<input id="keyWord" name="keyWord" type=text class="easyui-textbox" />
	</div>
	<div>
		<span class="blog">分类：</span>
		<input id="blogTypeId" name="typeId" type="text" />
		<span class="blog">权限：</span>
		<select id="rightType" name="rightType" class="easyui-combobox" data-options="editable:false">
			<option value="0">公开</option>
			<option value="1">自己可见</option>
		</select>
	</div>

	<div id="content">
<!-- 		<script id="content" name="content" style="width:98%;height:240px"></script> -->
	</div>
	<div align="center" style="padding:5px">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="saveBlog()" style="width:90px">保存</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="closeBlog()" style="width:90px">关闭</a>
	</div>

</div>
<script>
$(function() {
    $.getJSON("<%=path%>/admin/blogType/listAll",function(result){
    	if(result.data.length > 0){
    		result.data[0].selected=true
    	}
        $('#blogTypeId').combobox({
            valueField : 'id',
            textField : 'name',
            data : result.data,
            editable : false
        });
    });
   	testEditor = editormd("content", {
		width : "90%",
		height : 640,
		markdown : "",
		path : '<%=path%>/resources/editor/lib/',
		htmlDecode : "style,script,sub,sup|on*",
		saveHTMLToTextarea : true,
		flowChart: false,//开启流程图支持，默认关闭
		tex: true,// 开启科学公式TeX语言支持，默认关闭
		sequenceDiagram: false,//开启时序/序列图支持，默认关闭,
		imageUpload : true,
		imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp" ],
		imageUploadURL : "<%=path%>/editorMd/uploadImage?bizId=${id}&bizType=blog",
		onload : function() {
			var that = this;
		    var id = $("#blogId").val();
		   	if(id){
		        $.getJSON("<%=path%>/admin/blog/"+id,function(result){
		        	var blog = result.data;
		        	if(blog){
		        		$("#status").val(blog.status);
			        	$("#title").textbox('setValue',blog.title);
			        	$("#keyWord").textbox('setValue',blog.keyWord);
			        	if(blog.typeId){
				        	$("#blogTypeId").combobox("setValue",blog.typeId || "");
			        	}
			        	$("#rightType").combobox("setValue",blog.rightType);
			        	testEditor.setMarkdown(blog.content)
		        	}
		        });
		   	}
		}
	});
});

var testEditor;

function saveBlog(){
	var title = $("#title").val();
	var blogTypeId = $("#blogTypeId").combobox("getValue");
	var rightType = $("#rightType").combobox("getValue");
	var content = testEditor.getMarkdown();
	console.log(testEditor.getHTML())
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
					'status': $("#status").val(),
					'title' : title,
					'typeId' : blogTypeId,
					'rightType' : rightType,
					'content' : content,
					'keyWord' : keyWord
				},
				function(result){
					if(result.status == 0){
						alert("博客发布成功！");
						closeBlog();
					}else{
						alert("博客发布失败！");
					}
				}, "json");
	}
}
	
function closeBlog(){
	parentRefreshTab("文章列表");
	parentCloseTab("写博客")
}

</script>

</body>
</html>
