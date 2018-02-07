<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>流水类型</title>
</head>
<body>
	<table id="journalCategoryTreeGrid" style="padding:10px"></table>
	<div id="journalCategoryMenu" class="easyui-menu" style="width:120px;">
		<div onclick="journalCategoryOpenDialogFunc()" data-options="iconCls:'icon-add'">添加</div>
		<div onclick="journalCategoryDeleteFunc()" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	<div id="journalCategoryDialog" class="easyui-dialog" style="width:360px;height:180px;padding:10px" data-options="closed:'true', buttons:'#journalCategoryDialog-buttons'">
		<form id="journalCategoryForm" method="post" novalidate>
			<table class="input">
				<tr>
					<td width="30%">名称:</td>
					<td>
						<input id="journalCategoryId" name="id" type="hidden"/>
						<input id="journalCategoryParentId" name="parentId" type="hidden"/>
						<input name="name" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>排序:</td>
					<td>
						<input name="rank" class="easyui-numberbox" value="100" data-options="required:true,min:0"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="journalCategoryDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="journalCategorySaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#journalCategoryDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
<script>
$(function() {
	$('#journalCategoryTreeGrid').treegrid({
		title : '流水类型列表',
		url : basePath + '/admin/journalCategory/tree',
		idField:'id',
		treeField:'name',
		columns : [[ 
			{
				field : 'name',
				title : '名称',
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
					var html = '<a href="javascript:void(0)" class="journalCategory-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fa fa-pencil-square-o\'" onclick="journalCategoryEditFunc(\''+ row.id +'\');">编辑</a>'
					return html;
				}
			}
		]],
		onLoadSuccess:function(data){
			$('.journalCategory-easyui-linkbutton-edit').linkbutton({text:'编辑'});
		},
		method : 'GET',
		onContextMenu: function(e, row){
			e.preventDefault();
			if(!row){
				return;
			}
			$(this).treegrid('select', row.id);
			$('#journalCategoryMenu').menu('show',{
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

var journalCategorySubmitUrl;

function journalCategoryOpenDialogFunc(){
	$('#journalCategoryDialog').dialog('open').dialog('center').dialog('setTitle','添加流水类型');
	$('#journalCategoryForm').form('clear');
	var row = $('#journalCategoryTreeGrid').treegrid('getSelected');
	$('#journalCategoryParentId').val(row.id)
	journalCategorySubmitUrl = basePath + "/admin/journalCategory/save";
}

function journalCategoryEditFunc(index){
	var row = $('#journalCategoryTreeGrid').treegrid('find', index);
	if (row){
		$('#journalCategoryDialog').dialog('open').dialog('center').dialog('setTitle','修改流水类型');
		$('#journalCategoryForm').form('load',row);
		journalCategorySubmitUrl = basePath + "/admin/journalCategory/update";
	}
}
function journalCategorySaveFunc(){
	$('#journalCategoryForm').form('submit',{
		url: journalCategorySubmitUrl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status == 0){
				$('#journalCategoryDialog').dialog('close');
				$('#journalCategoryTreeGrid').treegrid('reload');
			} else {
				$.messager.show({
					title: '错误',
					msg: result.msg
				});
			}
		}
	});
}
function journalCategoryDeleteFunc(){
	var row = $('#journalCategoryTreeGrid').treegrid('getSelected');
	if (row){
		$.messager.confirm('提示','真的要删除这个流水类型吗?',function(r){
			if (r){
				$.post(basePath + '/admin/journalCategory/delete',{id:row.id},function(result){
					if (result.status == 0){
						$('#journalCategoryTreeGrid').treegrid('reload');
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
