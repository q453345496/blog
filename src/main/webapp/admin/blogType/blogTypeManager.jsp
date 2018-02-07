<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>文章类型</title>
</head>
<body>
	<table id="blogTypeDataGrid" style="padding:10px"></table>
	<div id="blogTypeDataGridToolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="blogTypeOpenDialogFunc()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="blogTypeDeleteFunc()">删除</a>
			名称:<input id="blogTypeNameQ" type="text" class="easyui-textbox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="blogTypeSearchFunc()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#blogTypeDataGridToolbar')">清空</a>
	</div>
	<div id="blogTypeDialog" class="easyui-dialog" style="width:360px;height:180px;padding:10px" data-options="closed:'true', modal:'true', buttons:'#blogTypeDialog-buttons'">
		<form id="blogTypeForm" method="post" novalidate>
			<table class="input">
				<tr>
					<td width="30%">名称:</td>
					<td>
						<input name="id" type="hidden"/>
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
	<div id="blogTypeDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="blogTypeSaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#blogTypeDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
<script>
$(function() {
	$('#blogTypeDataGrid').datagrid({
		title : '分类列表',
		url : basePath + '/admin/blogType/list',
		columns : [[ 
//			{field : 'ck',	checkbox : true}, 
			{
				field : 'id',
				title : '编号',
				width : 50,
				align:'center'
			}, 
			{
				field : 'name',
				title : '分类名称',
				width:100,
				align:'center',
				formatter: function(value,row,index) {
					return '<a href="#" onclick="blogTypeEditFunc('+index+')">'+value+'</a>';
				}
			}, 
			{
				field : 'rank',
				title : '排序',
				width:100,
				align:'center'
			}
		]],
		toolbar : '#blogTypeDataGridToolbar',
		method : 'GET',
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		fit : true
	});
});

var blogTypeSubmitUrl;

function blogTypeOpenDialogFunc(){
	$('#blogTypeDialog').dialog('open').dialog('center').dialog('setTitle','添加文章分类信息');
	$('#blogTypeForm').form('clear');
	blogTypeSubmitUrl = basePath + "/admin/blogType/save";
}

function blogTypeEditFunc(index){
	$('#blogTypeDataGrid').datagrid('selectRow', index);
	var row = $('#blogTypeDataGrid').datagrid('getSelected');
	if (row){
		$('#blogTypeDialog').dialog('open').dialog('center').dialog('setTitle','修改文章分类信息');
		$('#blogTypeForm').form('load',row);
		blogTypeSubmitUrl = basePath + "/admin/blogType/update";
	}
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
				$('#blogTypeDataGrid').datagrid('reload');
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
	var row = $('#blogTypeDataGrid').datagrid('getSelected');
	if (row){
		$.messager.confirm('提示','真的要删除这个分类吗?',function(r){
			if (r){
				$.post(basePath + '/admin/blogType/delete',{id:row.id},function(result){
					if (result.status == 0){
						$('#blogTypeDataGrid').datagrid('reload');
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

function blogTypeSearchFunc(){
	$('#blogTypeDataGrid').datagrid({
		queryParams: {
			name: $("#blogTypeNameQ").val(),
		}
	});
}
</script>

</body>
</html>
