<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>栏目</title>
</head>
<body>
	<table id="columnTreeGrid" style="padding:10px"></table>
	<div id="columnMenu" class="easyui-menu" style="width:120px;">
		<div onclick="columnOpenDialogFunc()" data-options="iconCls:'icon-add'">添加</div>
		<div onclick="columnDeleteFunc()" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	<div id="columnDialog" class="easyui-dialog" style="width:400px;height:220px;padding:10px" data-options="closed:'true', modal:'true', buttons:'#columnDialog-buttons'">
		<form id="columnForm" method="post" novalidate>
			<table class="input">
				<tr>
					<td width="30%">名称:</td>
					<td>
						<input id="columnId" name="id" type="hidden"/>
						<input id="columnParentId" name="parentId" type="hidden"/>
						<input name="name" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>编码:</td>
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
					<td>状态:</td>
					<td>
						<select id="column_status" name="status" class="easyui-combobox" style="width:100%">
							<option value="1" selected="selected">可用</option>
							<option value="0">禁用</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="columnDialog-buttons">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="columnSaveFunc()" style="width:90px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#columnDialog').dialog('close')" style="width:90px">关闭</a>
	</div>
	<%@include file="relate_blogType.jsp"%>
	<%@include file="relate_blog.jsp"%>
<script>
$(function() {
	$('#columnTreeGrid').treegrid({
		title : '栏目列表',
		url : basePath + '/admin/column/tree',
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
					var html = '<a href="javascript:void(0)" class="column-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fa fa-pencil-square-o\'" onclick="columnEditFunc(\''+ row.id +'\');">编辑</a>'
					html += '&nbsp;&nbsp;<a href="#" onclick="openRelateBlogTypeDialogFunc(\''+row.id+'\')">关联分类</a>';
					html += '&nbsp;&nbsp;<a href="#" onclick="openColumnBlogRelateDialogFunc(\''+row.id+'\')">关联博客</a>';
					return html;
				}
			}
		]],
		onLoadSuccess:function(node, data){
			$('.column-easyui-linkbutton-edit').linkbutton({text:'编辑'});
        	if(data){
        		var root = $('#columnTreeGrid').treegrid('getRoot');
				$('#columnTreeGrid').treegrid('expand', root.id);
        	}
		},
		method : 'GET',
		onContextMenu: function(e, row){
			e.preventDefault();
			if(!row){
				return;
			}
			$(this).treegrid('select', row.id);
			$('#columnMenu').menu('show',{
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

var columnSubmitUrl;

function columnOpenDialogFunc(){
	$('#columnDialog').dialog('open').dialog('center').dialog('setTitle','添加栏目');
	$('#columnForm').form('clear');
	$('#column_status').combobox('setValue',1);
	var row = $('#columnTreeGrid').treegrid('getSelected');
	$('#columnParentId').val(row.id)
	columnSubmitUrl = basePath + "/admin/column/save";
}

function columnEditFunc(index){
	var row = $('#columnTreeGrid').treegrid('find', index);
	if (row){
		$('#columnDialog').dialog('open').dialog('center').dialog('setTitle','修改栏目');
		$('#columnForm').form('load',row);
		columnSubmitUrl = basePath + "/admin/column/update";
	}
}
function columnSaveFunc(){
	$('#columnForm').form('submit',{
		url: columnSubmitUrl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status == 0){
				$('#columnDialog').dialog('close');
				$('#columnTreeGrid').treegrid('reload');
			} else {
				$.messager.show({
					title: '错误',
					msg: result.msg
				});
			}
		}
	});
}
function columnDeleteFunc(){
	var row = $('#columnTreeGrid').treegrid('getSelected');
	if (row){
		$.messager.confirm('提示','真的要删除这个栏目吗?',function(r){
			if (r){
				$.post(basePath + '/admin/column/delete',{id:row.id},function(result){
					if (result.status == 0){
						$('#columnTreeGrid').treegrid('reload');
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
