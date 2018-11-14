<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>代理服务器</title>
</head>
<body>
	<table id="proxyServerDataGrid" style="padding:10px"></table>
	<div id="proxyServerDataGridToolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="proxyServerOpenDialogFunc()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="proxyServerDeleteFunc()">删除</a>
			IP:<input id="proxyServerIPQ" type="text" class="easyui-textbox"/>
			状态:
				<select id="proxyServerStateQ" name="state" class="easyui-combobox" data-options="editable:false">
					<option value="" selected="selected">请选择</option>
					<option value="1">可用</option>
					<option value="0">不可用</option>
				</select>
			协议:
				<select id="proxyServerProtocolQ" name="protocol" class="easyui-combobox" data-options="editable:false">
					<option value="" selected="selected">请选择</option>
					<option value="0">HTTP</option>
					<option value="1">HTTPS</option>
				</select>
			匿名度:
				<select id="proxyServerTypeQ" name="type" class="easyui-combobox" data-options="editable:false">
					<option value="" selected="selected">请选择</option>
					<option value="0">透明</option>
					<option value="1">匿名</option>
					<option value="2">高匿</option>
				</select>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="proxyServerSearchFunc()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#proxyServerDataGridToolbar')">清空</a>
	</div>
	<div id="proxyServerDialog" class="easyui-dialog" style="width:360px;height:400px;padding:10px" data-options="closed:'true', modal:'true', buttons:'#proxyServerDialog-buttons'">
		<form id="proxyServerForm" method="post" novalidate>
			<table class="input">
				<tr>
					<td width="30%">IP:</td>
					<td>
						<input name="id" type="hidden"/>
						<input name="ip" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>端口:</td>
					<td>
						<input name="port" class="easyui-numberbox" value="100" data-options="required:true,min:0"/>
					</td>
				</tr>
				<tr>
					<td>协议类型:</td>
					<td>
						<select id="protocol" name="protocol" class="easyui-combobox" data-options="editable:false">
							<option value="0" selected="selected">HTTP</option>
							<option value="1">HTTPS</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>匿名度:</td>
					<td>
						<select id="type" name="type" class="easyui-combobox" data-options="editable:false">
							<option value="0" selected="selected">透明</option>
							<option value="1">匿名</option>
							<option value="2">高匿</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>状态:</td>
					<td>
						<select id="state" name="state" class="easyui-combobox" data-options="editable:false">
							<option value="1" selected="selected">可用</option>
							<option value="0">不可用</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="proxyServerDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="proxyServerSaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#proxyServerDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
<script>
$(function() {
	$('#proxyServerDataGrid').datagrid({
		title : '代理服务器列表',
		url : basePath + '/admin/proxyServer/list',
		columns : [[ 
			{field : 'ck',	checkbox : true}, 
			{
				field : 'id',
				title : '编号',
				width : 50,
				align:'center'
			}, 
			{
				field : 'ip',
				title : 'IP',
				width:100,
				align:'center',
				formatter: function(value,row,index){
					return '<a href="#" onclick="proxyServerEditFunc('+index+')">'+value+'</a>';
				}
			}, 
			{
				field : 'port',
				title : '端口',
				width:100,
				align:'center'
			},
			{
				field : 'protocol',
				title : '协议类型',
				width:100,
				align:'center',
				formatter: function(value,row,index){
					switch(value){
						case 0 : return "HTTP";
						case 1 : return "HTTPS";
						default: return value;
					}
				}
			},
			{
				field : 'type',
				title : '匿名度',
				width:100,
				align:'center',
				formatter: function(value,row,index){
					switch(value){
						case 0 : return "透明";
						case 1 : return "匿名";
						case 2 : return "高匿";
						default: return value;
					}
				}
			},
			{
				field : 'responeTime',
				title : '响应时间(毫秒)',
				width:100,
				align:'center'
			},
			{
				field : 'score',
				title : '评分',
				width:100,
				align:'center'
			},
			{
				field : 'state',
				title : '状态',
				width:100,
				align:'center',
				formatter: function(value,row,index){
					switch(value){
						case 0 : return '<span class="s-1">不可用</span>';
						case 1 : return '<span class="s1">可用</span>';
						default: return value;
					}
				}
			}
		]],
		toolbar : '#proxyServerDataGridToolbar',
		method : 'GET',
		singleSelect : false,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		fit : true
	});
});

var proxyServerSubmitUrl;

function proxyServerOpenDialogFunc(){
	$('#proxyServerDialog').dialog('open').dialog('center').dialog('setTitle','添加代理服务器');
	$('#proxyServerForm').form('clear');
	$('#protocol').combobox('setValue',0);
	$('#type').combobox('setValue',2);
	$('#state').combobox('setValue',0);
	proxyServerSubmitUrl = basePath + "/admin/proxyServer/save";
}

function proxyServerEditFunc(index){
	$('#proxyServerDataGrid').datagrid('unselectAll');
	$('#proxyServerDataGrid').datagrid('selectRow', index);
	var row = $('#proxyServerDataGrid').datagrid('getSelected');
	if (row){
		$('#proxyServerDialog').dialog('open').dialog('center').dialog('setTitle','修改代理服务器');
		$('#proxyServerForm').form('clear');
		$('#proxyServerForm').form('load',row);
		proxyServerSubmitUrl = basePath + "/admin/proxyServer/update";
	}
	event.stopPropagation();
}
function proxyServerSaveFunc(){
	$('#proxyServerForm').form('submit',{
		url: proxyServerSubmitUrl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status == 0){
				$('#proxyServerDialog').dialog('close');
				$('#proxyServerDataGrid').datagrid('reload');
			} else {
				$.messager.show({
					title: '错误',
					msg: result.msg
				});
			}
		}
	});
}
function proxyServerDeleteFunc(){
	var ids = concatIds('#proxyServerDataGrid');
	if (ids != ''){
		$.messager.confirm('提示','真的要删除这个代理服务器吗?',function(r){
			if (r){
				$.post(basePath + '/admin/proxyServer/delete',{id : ids},function(result){
					if (result.status == 0){
						$('#proxyServerDataGrid').datagrid('reload');
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

function proxyServerSearchFunc(){
	$('#proxyServerDataGrid').datagrid({
		queryParams: {
			ip: $("#proxyServerIPQ").val(),
			type : $("#proxyServerTypeQ").combobox("getValue"),
			state : $("#proxyServerStateQ").combobox("getValue"),
			protocol : $("#proxyServerProtocolQ").combobox("getValue"),
		}
	});
}
</script>

</body>
</html>
