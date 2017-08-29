<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../common/link.jsp"></jsp:include>
<title>分类管理</title>
<style>
</style>
</head>
<body>
	<table id="type_dg" style="padding:10px"></table>
	<div id="toolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="openCategoryDialog()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="deleteCategory()">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog" style="width:360px;height:180px;padding:10px"
            data-options="closed:'true', buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate>
            	<input name="id" type="hidden"/>
            <div class="fitem">
                <label>分类名称:</label>
                <input name="name" class="easyui-textbox" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>排序序号:</label>
                <input name="rank" class="easyui-numberbox" value="100" data-options="required:true,min:0"/>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="saveCategory()" style="width:90px">保存</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
</body>
<script type="text/javascript">
$(function() {
	$('#type_dg').treegrid({
		title : '分类列表',
		url : '<%=path%>/admin/journalCategory/list',
		idField:'id',
		treeField:'name',
		columns : [[ 
//              {field : 'id',		title : '编号',	width:50,	align:'center'}, 
             {field : 'name',	title : '分类名称',	width:100,	formatter: nameFormatter},
             {field : 'rank',	title : '排序',	width:100,	align:'center'}
        ]],
        toolbar : '#toolbar',
        method : 'GET',
        singleSelect : true,
        pagination : true,
        rownumbers : true,
        fitColumns : true,
        fit : true
	});
});

function nameFormatter(value,row,index){
	return '<a href="#" onclick="editCategory('+row.id+')">'+value+'</a>';//注意树没有index
}

var url;
function openCategoryDialog(){
    $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加文章分类信息');
    $('#fm').form('clear');
    url="<%=path%>/admin/journalCategory/save";
}

function editCategory(index){
	$('#type_dg').treegrid('selectRow', index);
    var row = $('#type_dg').treegrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改文章分类信息');
        $('#fm').form('load',row);
        url="<%=path%>/admin/journalCategory/update";
    }
}
function saveCategory(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.status == 200){
                $('#dlg').dialog('close');
                $('#type_dg').treegrid('reload');
            } else {
                $.messager.show({
                    title: '错误',
                    msg: result.msg
                });
            }
        }
    });
}
function deleteCategory(){
    var row = $('#type_dg').treegrid('getSelected');
    if (row){
        $.messager.confirm('提示','真的要删除这个分类吗?',function(r){
            if (r){
                $.post('<%=path%>/admin/journalCategory/delete',{id:row.id},function(result){
                    if (result.status == 200){
                        $('#type_dg').treegrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
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
</html>
