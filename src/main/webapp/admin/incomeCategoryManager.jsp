<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../common/adminLink.jsp"></jsp:include>
<script src="<%=path%>/resources/ueditor/ueditor.config.js"></script>
<script src="<%=path%>/resources/ueditor/ueditor.all.min.js"> </script>
<script src="<%=path%>/resources/my-ueditor.js"> </script>
<script src="<%=path%>/resources/ueditor/lang/zh-cn/zh-cn.js"> </script>
<title>分类管理</title>
<style>
</style>
<script type="text/javascript">
var ue = UE.getEditor('content', {toolbars: [['insertimage']]});
ue.ready(function(){
// 	ue.setDisabled();
	this.hide();//隐藏UE框体
	this.addListener('beforeInsertImage',function(t, arg){
		console.log(t)
		console.log(arg)
		alert(arg[0].src);//arg就是上传图片的返回值，是个数组，如果上传多张图片，请遍历该值。
		$("#abccc").attr("value", arg[0].src);
	});
});
function upImage(){
	var dialog = ue.getDialog('insertimage');
	dialog.render();
	dialog.open();
}
</script>
</head>
<body>
	<input id="abccc" type="text"/>
	<input type="button" id="myEditorImagesss" onclick="upImage();" value="上传图片"/>
	<script id="content" type="text/plain" style="display: none"></script>
</body>

</html>
