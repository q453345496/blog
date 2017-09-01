<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../common/link.jsp"></jsp:include>
<title>文章类型</title>
<style>
    #fm{
        margin:0;
        padding:10px;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:80px;
        text-align:right;
    }
    .fitem input{
        width:160px;
    }
</style>
</head>
<body>
	<table id="type_dg" style="padding:10px"></table>
	<div id="toolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="openBlogTypeDialog()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="deleteBlogType()">删除</a>
			分类名称:<input id="s_name" type="text" class="easyui-textbox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="searchBlogType()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#toolbar')">清空</a>
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
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="saveBlogType()" style="width:90px">保存</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
<script>
$(function() {
	$('#type_dg').datagrid({
		title : '分类列表',
		url : '<%=path%>/admin/blogType/list',
		columns : [[ 
             {field : 'id',		title : '编号',	width:50,	align:'center'}, 
             {field : 'name',	title : '分类名称',	width:100,	align:'center',	formatter: nameFormatter},
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
	return '<a href="#" onclick="editBlogType('+index+')">'+value+'</a>';
}

var url;
function openBlogTypeDialog(){
    $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加文章分类信息');
    $('#fm').form('clear');
    url="<%=path%>/admin/blogType/save";
}

function editBlogType(index){
	$('#type_dg').datagrid('selectRow', index);
    var row = $('#type_dg').datagrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改文章分类信息');
        $('#fm').form('load',row);
        url="<%=path%>/admin/blogType/update";
    }
}
function saveBlogType(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.status == 0){
                $('#dlg').dialog('close');
                $('#type_dg').datagrid('reload');
            } else {
                $.messager.show({
                    title: '错误',
                    msg: result.msg
                });
            }
        }
    });
}
function deleteBlogType(){
    var row = $('#type_dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('提示','真的要删除这个分类吗?',function(r){
            if (r){
                $.post('<%=path%>/admin/blogType/delete',{id:row.id},function(result){
                    if (result.status == 0){
                        $('#type_dg').datagrid('reload');    // reload the user data
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

function searchBlogType(){
	$('#type_dg').datagrid({
		queryParams: {
			name: $("#s_name").val(),
		}
	});
}
</script>

</body>
</html>
