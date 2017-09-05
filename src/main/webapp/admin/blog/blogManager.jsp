<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/link.jsp"></jsp:include>
<title>文章列表</title>
</head>
<body>
	<table id="blog_dg"></table>
	<div id="toolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="openBlogCreateTab()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-edit',plain:true" onclick="openBlogModifyTab()">修改</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="deleteBlogType()">删除</a>
			文章标题:<input id="s_title" type="text" class="easyui-textbox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="searchBlogType()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#toolbar')">清空</a>
	</div>
<script>
$(function() {
	$('#blog_dg').datagrid({
		title : '文章列表',
		url : '<%=path%>/admin/blog/list',
		columns : [[ 
             {field : 'id',		title : '编号',	width:50,	align:'center'}, 
             {field : 'title',	title : '标题',	width:100,	align:'center'},
             {field : 'createTime',	title : '发布时间',	width:100,	align:'center'}
        ]],
        toolbar: '#toolbar',
        method : 'GET',
        singleSelect : true,
        pagination : true,
        rownumbers : true,
        fitColumns : true,
        fit : true
	});
});

function deleteBlogType(){
    var row = $('#blog_dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('提示','真的要删除这个分类吗?',function(r){
            if (r){
                $.post('<%=path%>/admin/blog/delete',{id:row.id},function(result){
                    if (result.status == 0){
                        $('#blog_dg').datagrid('reload');    // reload the user data
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
	$('#blog_dg').datagrid({
		queryParams: {
			name: $("#s_title").val(),
		}
	});
}

function openBlogCreateTab(){
	parentOpenTab('写博客','<%=path%>/admin/blog/toAdd','fa fa-edit');
}
function openBlogModifyTab(){
	var selectedRows=$("#blog_dg").datagrid("getSelections");
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一个要修改的博客！","warning");
		return;
	}
	var row=selectedRows[0];
	parentOpenTab('写博客','<%=path%>/admin/blog/toEdit/'+row.id,'fa fa-edit');
}
</script>

</body>
</html>
