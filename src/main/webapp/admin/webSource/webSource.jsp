<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>网页源</title>
</head>
<body>
	<table id="webSourceDataGrid" style="padding:10px"></table>
	<div id="webSourceDataGridToolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="webSourceOpenDialogFunc()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="webSourceDeleteFunc()">删除</a>
			名称:<input id="webSourceNameQ" type="text" class="easyui-textbox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="webSourceSearchFunc()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#webSourceDataGridToolbar')">清空</a>
	</div>
	<div id="webSourceDialog" class="easyui-dialog" style="width:360px;height:300px;padding:10px" data-options="closed:'true', modal:'true', buttons:'#webSourceDialog-buttons'">
		<form id="webSourceForm" method="post" novalidate>
			<table class="input">
				<tr>
					<td width="30%">名称:</td>
					<td>
						<input name="id" type="hidden"/>
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
					<td width="30%">标题-选择器:</td>
					<td>
						<input name="titlePattern" class="easyui-textbox" data-options="required:false"/>
					</td>
				</tr>
				<tr>
					<td width="30%">标签-选择器:</td>
					<td>
						<input name="labelPattern" class="easyui-textbox" data-options="required:false"/>
					</td>
				</tr>
				<tr>
					<td width="30%">正文-选择器:</td>
					<td>
						<input name="contentPattern" class="easyui-textbox" data-options="required:false"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="webSourceDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="webSourceSaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#webSourceDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
<script>
$(function() {
	$('#webSourceDataGrid').datagrid({
		title : '网页源列表',
		url : basePath + '/admin/webSource/list',
		columns : [[ 
			{field : 'ck',	checkbox : true}, 
			{
				field : 'id',
				title : '编号',
				width : 50,
				align:'center'
			}, 
			{
				field : 'name',
				title : '名称',
				width:100,
				align:'center',
				formatter: function(value,row,index){
					return '<a href="#" onclick="webSourceEditFunc('+index+')">'+value+'</a>';
				}
			}, 
			{
				field : 'createTime',
				title : '创建时间',
				width:100,
				align:'center'
			},
		]],
		toolbar : '#webSourceDataGridToolbar',
		method : 'GET',
		singleSelect : false,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		fit : true
	});
});

var webSourceSubmitUrl;

function webSourceOpenDialogFunc(){
	$('#webSourceDialog').dialog('open').dialog('center').dialog('setTitle','添加网页源');
	$('#webSourceForm').form('clear');
	webSourceSubmitUrl = basePath + "/admin/webSource/save";
}

function webSourceEditFunc(index){
	$('#webSourceDataGrid').datagrid('unselectAll');
	$('#webSourceDataGrid').datagrid('selectRow', index);
	var row = $('#webSourceDataGrid').datagrid('getSelected');
	if (row){
		$('#webSourceDialog').dialog('open').dialog('center').dialog('setTitle','修改网页源');
		$('#webSourceForm').form('load',row);
		webSourceSubmitUrl = basePath + "/admin/webSource/update";
	}
	event.stopPropagation();
}
function webSourceSaveFunc(){
	$('#webSourceForm').form('submit',{
		url: webSourceSubmitUrl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status == 0){
				$('#webSourceDialog').dialog('close');
				$('#webSourceDataGrid').datagrid('reload');
			} else {
				$.messager.show({
					title: '错误',
					msg: result.msg
				});
			}
		}
	});
}
function webSourceDeleteFunc(){
	var ids = concatIds('#webSourceDataGrid');
	if (ids != ''){
		$.messager.confirm('提示','真的要删除这个网页源吗?',function(r){
			if (r){
				$.post(basePath + '/admin/webSource/delete',{id : ids},function(result){
					if (result.status == 0){
						$('#webSourceDataGrid').datagrid('reload');
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

function webSourceSearchFunc(){
	$('#webSourceDataGrid').datagrid({
		queryParams: {
			name: $("#webSourceNameQ").val(),
		}
	});
}
</script>

</body>
</html>
