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
    #blogTypeForm{
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
	<table id="blogTypeTable" style="padding:10px"></table>
	<div id="blogTypeTableToolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="openBlogTypeDialog()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="deleteBlogType()">删除</a>
			分类名称:<input id="s_name" type="text" class="easyui-textbox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="searchBlogType()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#blogTypeTableToolbar')">清空</a>
	</div>
	<div id="blogTypeDialog" class="easyui-dialog" style="width:360px;height:180px;padding:10px"
            data-options="closed:'true', buttons:'#blogTypeDialog-buttons'">
        <form id="blogTypeForm" method="post" novalidate>
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
    <div id="blogTypeDialog-buttons">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-save'" onclick="saveBlogType()" style="width:90px">保存</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-times'" onclick="javascript:$('#blogTypeDialog').dialog('close')" style="width:90px">关闭</a>
    </div>
<script>
$(function() {
	$('#blogTypeTable').datagrid({
		title : '分类列表',
		url : '<%=path%>/admin/blogType/list',
		columns : [[ 
//              {field : 'ck',	checkbox : true}, 
             {field : 'id',		title : '编号',	width:50,	align:'center'}, 
             {field : 'name',	title : '分类名称',	width:100,	align:'center',	formatter: nameFormatter},
             {field : 'rank',	title : '排序',	width:100,	align:'center'}
        ]],
        toolbar : '#blogTypeTableToolbar',
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
    $('#blogTypeDialog').dialog('open').dialog('center').dialog('setTitle','添加文章分类信息');
    $('#blogTypeForm').form('clear');
    url="blogType/save";
}

function editBlogType(index){
	$('#blogTypeTable').datagrid('selectRow', index);
    var row = $('#blogTypeTable').datagrid('getSelected');
    if (row){
        $('#blogTypeDialog').dialog('open').dialog('center').dialog('setTitle','修改文章分类信息');
        $('#blogTypeForm').form('load',row);
        url="<%=path%>/admin/blogType/update";
    }
}
function saveBlogType(){
    $('#blogTypeForm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.status == 0){
                $('#blogTypeDialog').dialog('close');
                $('#blogTypeTable').datagrid('reload');
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
    var row = $('#blogTypeTable').datagrid('getSelected');
    if (row){
        $.messager.confirm('提示','真的要删除这个分类吗?',function(r){
            if (r){
                $.post('<%=path%>/admin/blogType/delete',{id:row.id},function(result){
                    if (result.status == 0){
                        $('#blogTypeTable').datagrid('reload');
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

function searchBlogType(){
	$('#blogTypeTable').datagrid({
		queryParams: {
			name: $("#s_name").val(),
		}
	});
}
</script>

</body>
</html>
