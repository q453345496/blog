<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>文章分类</title>
</head>
<body>
	<table id="blogTypeTreeGrid" style="padding:10px"></table>
	<div id="blogTypeMenu" class="easyui-menu" style="width:120px;">
		<div onclick="blogTypeOpenDialogFunc()" data-options="iconCls:'icon-add'">添加</div>
		<div onclick="blogTypeDeleteFunc()" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	<div id="blogTypeDialog" class="easyui-dialog" style="width:360px;height:400px;padding:10px" data-options="closed:'true', modal:'true', buttons:'#blogTypeDialog-buttons'">
		<form id="blogTypeForm" method="post" enctype="multipart/form-data" novalidate>
			<table class="input">
				<tr>
					<td width="30%">名称:</td>
					<td>
						<input id="blogTypeId" name="id" type="hidden"/>
						<input id="blogTypeParentId" name="parentId" type="hidden"/>
						<input name="name" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td width="30%">编码:</td>
					<td>
						<input name="code" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>排序:</td>
					<td>
						<input name="rank" class="easyui-numberbox" value="100" data-options="required:true,min:0"/>
					</td>
				</tr>
				<tr>
					<td>图片:</td>
					<td>
						<input id="img" name="img" type="file" onchange="setImagePreviews('img','img_preview')"/>
						<img id="img_preview" class="img-preview">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="blogTypeDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="blogTypeSaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#blogTypeDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
<script>
$(function() {
	$('#blogTypeTreeGrid').treegrid({
		title : '分类列表',
		url : basePath + '/admin/blogType/tree',
		idField:'id',
		treeField:'name',
		columns : [[ 
			{
				field : 'name',
				title : '名称',
				width:50,
			}, 
			{
				field : 'code',
				title : '编码',
				width:50,
			}, 
			{
				field : 'rank',
				title : '排序',
				width:50,
				align:'center'
			},
			{
				field : 'action',
				title : '操作',
				width:100,
				align:'center',
				formatter: function(value, row, index){
					var html = '<a href="javascript:void(0)" class="blogType-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fa fa-pencil-square-o\'" onclick="blogTypeEditFunc(\''+ row.id +'\');">编辑</a>'
					return html;
				}
			}
		]],
		onLoadSuccess:function(node, data){
			$('.blogType-easyui-linkbutton-edit').linkbutton({text:'编辑'});
			if(data){
        		var root = $('#blogTypeTreeGrid').treegrid('getRoot');
				$('#blogTypeTreeGrid').treegrid('expand', root.id);
        	}
		},
		method : 'GET',
		onContextMenu: function(e, row){
			e.preventDefault();
			if(!row){
				return;
			}
			$(this).treegrid('select', row.id);
			$('#blogTypeMenu').menu('show',{
				left: e.pageX,
				top: e.pageY
			});
		},
		singleSelect : true,
		pagination : false,
		rownumbers : true,
		fitColumns : true,
		fit : true
	});
});

var blogTypeSubmitUrl;

function blogTypeOpenDialogFunc(){
	$('#blogTypeDialog').dialog('open').dialog('center').dialog('setTitle','添加博客类型');
	$('#blogTypeForm').form('clear');
	$('#img_preview').html('');
	$('#img_preview').attr('src', basePath + '/resources/images/default.png');
	var row = $('#blogTypeTreeGrid').treegrid('getSelected');
	$('#blogTypeParentId').val(row.id)
	blogTypeSubmitUrl = basePath + "/admin/blogType/save";
}

function blogTypeEditFunc(index){
	var row = $('#blogTypeTreeGrid').treegrid('find', index);
	if (row){
		$('#blogTypeDialog').dialog('open').dialog('center').dialog('setTitle','修改博客类型');
		$('#blogTypeForm').form('load',row);
		$('#img_preview').html('');
		if(row.imgPathURL){
			$('#img_preview').attr('src',row.imgPathURL);
		}else{
			$('#img_preview').attr('src', basePath + '/resources/images/default.png');
		}
		blogTypeSubmitUrl = basePath + "/admin/blogType/update";
	}
	event.stopPropagation();
}
function blogTypeSaveFunc(){
	$('#blogTypeForm').form('submit',{
		url: blogTypeSubmitUrl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status == 0){
				$('#blogTypeDialog').dialog('close');
				$('#blogTypeTreeGrid').treegrid('reload');
			} else {
				$.messager.show({
					title: '错误',
					msg: result.msg
				});
			}
		}
	});
}
function blogTypeDeleteFunc(){
	var row = $('#blogTypeTreeGrid').treegrid('getSelected');
	if (row){
		$.messager.confirm('提示','真的要删除这个分类吗?',function(r){
			if (r){
				$.post(basePath + '/admin/blogType/delete',{id:row.id},function(result){
					if (result.status == 0){
						$('#blogTypeTreeGrid').treegrid('reload');
					} else {
						$.messager.show({
							title: '错误',
							msg: result.msg
						});
					}
				},'json');
			}
		});
	}
}

</script>

</body>
</html>
