<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>专题</title>
</head>
<body>
	<table id="specialTopicDataGrid" style="padding:10px"></table>
	<div id="specialTopicDataGridToolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="specialTopicOpenDialogFunc()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="specialTopicDeleteFunc()">删除</a>
			名称:<input id="specialTopicNameQ" type="text" class="easyui-textbox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="specialTopicSearchFunc()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#specialTopicDataGridToolbar')">清空</a>
	</div>
	<div id="specialTopicDialog" class="easyui-dialog" style="width:360px;height:180px;padding:10px" data-options="closed:'true', modal:'true', buttons:'#specialTopicDialog-buttons'">
		<form id="specialTopicForm" method="post" novalidate>
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
	<div id="specialTopicDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="specialTopicSaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#specialTopicDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
	<%@include file="relate_blog.jsp"%>
<script>
$(function() {
	$('#specialTopicDataGrid').datagrid({
		title : '专题列表',
		url : basePath + '/admin/specialTopic/list',
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
					return '<a href="#" onclick="specialTopicEditFunc('+index+')">'+value+'</a>';
				}
			}, 
			{
				field : 'rank',
				title : '排序',
				width:20,
				align:'center'
			},
			{
				field : 'click',
				title : '访问量',
				width:20,
				align:'center',
			}, 
			{
				field : 'relateCount',
				title : '关联个数',
				width:20,
				align:'center',
			}, 
			{
				field : 'createTime',
				title : '创建时间',
				width:40,
				align:'center'
			},
			{
				field : 'action',
				title : '操作',
				width:100,
				align:'center',
				formatter: function(value,row,index){
					return '<a href="#" onclick="openRelateBlogDialogFunc('+index+')">关联文章</a>';
				}
			}, 
		]],
		toolbar : '#specialTopicDataGridToolbar',
		method : 'GET',
		singleSelect : false,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		fit : true
	});
});

var specialTopicSubmitUrl;

function specialTopicOpenDialogFunc(){
	$('#specialTopicDialog').dialog('open').dialog('center').dialog('setTitle','添加专题');
	$('#specialTopicForm').form('clear');
	specialTopicSubmitUrl = basePath + "/admin/specialTopic/save";
}

function specialTopicEditFunc(index){
	$('#specialTopicDataGrid').datagrid('unselectAll');
	$('#specialTopicDataGrid').datagrid('selectRow', index);
	var row = $('#specialTopicDataGrid').datagrid('getSelected');
	if (row){
		$('#specialTopicDialog').dialog('open').dialog('center').dialog('setTitle','修改专题');
		$('#specialTopicForm').form('load',row);
		specialTopicSubmitUrl = basePath + "/admin/specialTopic/update";
	}
	event.stopPropagation();
}
function specialTopicSaveFunc(){
	$('#specialTopicForm').form('submit',{
		url: specialTopicSubmitUrl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status == 0){
				$('#specialTopicDialog').dialog('close');
				$('#specialTopicDataGrid').datagrid('reload');
			} else {
				$.messager.show({
					title: '错误',
					msg: result.msg
				});
			}
		}
	});
}
function specialTopicDeleteFunc(){
	var ids = concatIds('#specialTopicDataGrid');
	if (ids != ''){
		$.messager.confirm('提示','真的要删除这个专题吗?',function(r){
			if (r){
				$.post(basePath + '/admin/specialTopic/delete',{id:ids},function(result){
					if (result.status == 0){
						$('#specialTopicDataGrid').datagrid('reload');
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

function specialTopicSearchFunc(){
	$('#specialTopicDataGrid').datagrid({
		queryParams: {
			name: $("#specialTopicNameQ").val(),
		}
	});
}
</script>

</body>
</html>
