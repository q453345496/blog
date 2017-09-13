<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/link.jsp"></jsp:include>
<title>参数管理</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">

		<div data-options="region:'west',split:true,title:'参数类型'" style="width: 150px;">
			<table id="paramTypeDataGrid" style="padding:10px"></table>
		</div>

		<div data-options="region:'center',split:true,title:'参数列表'">
			<table id="paramDataGrid" style="padding:10px"></table>
			<div id="paramDataGridToolbar">
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="paramOpenDialogFunc()">添加</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="paramDeleteFunc()">删除</a>
					名称:<input id="paramKeyQ" type="text" class="easyui-textbox"/>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="paramSearchFunc()">查询</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#paramDataGridToolbar')">清空</a>
			</div>
			<div id="paramDialog" class="easyui-dialog" style="width:360px;height:200px;padding:10px" data-options="closed:'true', buttons:'#paramDialog-buttons'">
				<form id="paramForm" method="post" novalidate>
					<table class="input">
						<tr>
							<td width="30%">参数key:</td>
							<td>
								<input name="id" type="hidden"/>
								<input name="key" class="easyui-textbox" data-options="required:true"/>
							</td>
						</tr>
						<tr>
							<td>参数值:</td>
							<td>
								<input name="value" class="easyui-textbox" data-options="required:true"/>
							</td>
						</tr>
						<tr>
							<td>简介:</td>
							<td>
								<textarea  name="summary" class="easyui-textbox" data-options="required:true,multiline:true" style="width:100%"></textarea>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="paramDialog-buttons">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="paramSaveFunc()" style="width:90px">保存</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#paramDialog').dialog('close')" style="width:90px">关闭</a>
			</div>
		</div>

	</div>
	<script>
		$(function(){
			$('#paramTypeDataGrid').datagrid({
				url : basePath + '/admin/paramType/list',
				columns : [[ 
					{
						field : 'name',
						title : '名称',
						width:100,
						align:'center',
					}
				]],
				method : 'GET',
				singleSelect : true,
				pagination : false,
				rownumbers : false,
				fitColumns : true,
				fit : true,
				onLoadSuccess: function(data){
					$(this).datagrid("selectRow",0);
				},
				onSelect: function(rowIndex, rowData) {
					paramTypeCode = rowData ? rowData.code : '';
					$("#paramDataGrid").datagrid({
						url : basePath + '/admin/param/list',
						queryParams : {
							typeCode : paramTypeCode,
						},
						method : 'GET',
						columns : [[ 
							{
	  							field : 'key',
	  							title : '参数key',
	  							width:100,
	  							align:'center',
	  							formatter: function(value,row,index){
	  								return '<a href="#" onclick="paramEditFunc('+index+')">'+value+'</a>';
	  							}
	  						},
							{
	  							field : 'value',
	  							title : '参数值',
	  							width:100,
	  							align:'center',
	  						},
							{
	  							field : 'summary',
	  							title : '简介',
	  							width:100,
	  							align:'center',
	  						},
						]],
						toolbar : '#paramDataGridToolbar',
						singleSelect : true,
						pagination : true,
						rownumbers : true,
						fitColumns : true,
						fit : true,
					});
				}
			});
		});
		var paramSubmitUrl;

		function paramOpenDialogFunc(){
			$('#paramDialog').dialog('open').dialog('center').dialog('setTitle','添加参数');
			$('#paramForm').form('clear');
			paramSubmitUrl = basePath + "/admin/param/save";
		}

		function paramEditFunc(index){
			$('#paramDataGrid').datagrid('selectRow', index);
			var row = $('#paramDataGrid').datagrid('getSelected');
			if (row){
				$('#paramDialog').dialog('open').dialog('center').dialog('setTitle','修改参数');
				$('#paramForm').form('load',row);
				paramSubmitUrl = basePath + "/admin/param/update";
			}
		}
		function paramSaveFunc(){
			$('#paramForm').form('submit',{
				url: paramSubmitUrl,
				onSubmit: function(){
					return $(this).form('validate');
				},
				queryParams : {
					typeCode : paramTypeCode,
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.status == 0){
						$('#paramDialog').dialog('close');
						$('#paramDataGrid').datagrid('reload');
					} else {
						$.messager.show({
							title: '错误',
							msg: result.msg
						});
					}
				}
			});
		}
		function paramDeleteFunc(){
			var row = $('#paramDataGrid').datagrid('getSelected');
			if (row){
				$.messager.confirm('提示','真的要删除这个参数吗?',function(r){
					if (r){
						$.post(basePath + '/admin/param/delete',{id:row.id},function(result){
							if (result.status == 0){
								$('#paramDataGrid').datagrid('reload');
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

		function paramSearchFunc(){
			$('#paramDataGrid').datagrid({
				queryParams: {
					key : $("#paramKeyQ").val(),
					typeCode : paramTypeCode,
				}
			});
		}
	</script>
</body>
</html>
