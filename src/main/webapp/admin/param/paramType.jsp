<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>参数类型</title>
</head>
<body>
	<table id="paramTypeDataGrid" style="padding:10px"></table>
	<div id="paramTypeDataGridToolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="paramTypeOpenDialogFunc()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="paramTypeDeleteFunc()">删除</a>
			名称:<input id="paramTypeNameQ" type="text" class="easyui-textbox"/>
			编码:<input id="paramTypeCodeQ" type="text" class="easyui-textbox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="paramTypeSearchFunc()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#paramTypeDataGridToolbar')">清空</a>
	</div>
	<div id="paramTypeDialog" class="easyui-dialog" style="width:360px;height:180px;padding:10px" data-options="closed:'true', modal:'true', buttons:'#paramTypeDialog-buttons'">
		<form id="paramTypeForm" method="post" novalidate>
			<table class="input">
				<tr>
					<td width="30%">名称:</td>
					<td>
						<input name="id" type="hidden"/>
						<input name="name" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>编码:</td>
					<td>
						<input name="code" class="easyui-textbox" value="100" data-options="required:true"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="paramTypeDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="paramTypeSaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#paramTypeDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
<script>
$(function() {
	$('#paramTypeDataGrid').datagrid({
		title : '参数类型列表',
		url : basePath + '/admin/paramType/list',
		columns : [[ 
//			{field : 'ck',	checkbox : true}, 
// 			{
// 				field : 'id',
// 				title : '编号',
// 				width : 50,
// 				align:'center'
// 			}, 
			{
				field : 'name',
				title : '名称',
				width:100,
				align:'center',
				formatter: function(value,row,index){
					return '<a href="#" onclick="paramTypeEditFunc('+index+')">'+value+'</a>';
					}
			}, 
			{
				field : 'code',
				title : '编码',
				width:100,
				align:'center'
			}
		]],
		toolbar : '#paramTypeDataGridToolbar',
		method : 'GET',
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		fit : true
	});
});

var paramTypeSubmitUrl;

function paramTypeOpenDialogFunc(){
	$('#paramTypeDialog').dialog('open').dialog('center').dialog('setTitle','添加参数类型');
	$('#paramTypeForm').form('clear');
	paramTypeSubmitUrl = basePath + "/admin/paramType/save";
}

function paramTypeEditFunc(index){
	$('#paramTypeDataGrid').datagrid('selectRow', index);
	var row = $('#paramTypeDataGrid').datagrid('getSelected');
	if (row){
		$('#paramTypeDialog').dialog('open').dialog('center').dialog('setTitle','修改参数类型');
		$('#paramTypeForm').form('load',row);
		paramTypeSubmitUrl = basePath + "/admin/paramType/update";
	}
}
function paramTypeSaveFunc(){
	$('#paramTypeForm').form('submit',{
		url: paramTypeSubmitUrl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status == 0){
				$('#paramTypeDialog').dialog('close');
				$('#paramTypeDataGrid').datagrid('reload');
			} else {
				$.messager.show({
					title: '错误',
					msg: result.msg
				});
			}
		}
	});
}
function paramTypeDeleteFunc(){
	var row = $('#paramTypeDataGrid').datagrid('getSelected');
	if (row){
		$.messager.confirm('提示','真的要删除这个参数类型吗?',function(r){
			if (r){
				$.post(basePath + '/admin/paramType/delete',{id:row.id},function(result){
					if (result.status == 0){
						$('#paramTypeDataGrid').datagrid('reload');
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

function paramTypeSearchFunc(){
	$('#paramTypeDataGrid').datagrid({
		queryParams: {
			name: $("#paramTypeNameQ").val(),
			code: $("#paramTypeCodeQ").val(),
		}
	});
}
</script>

</body>
</html>
