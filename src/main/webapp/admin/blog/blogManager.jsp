<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/common/adminLink.jsp"></jsp:include>
<title>文章列表</title>
</head>
<body>
	<table id="blogDataGrid"></table>
	<div id="blogDataGridToolbar">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus',plain:true" onclick="openBlogCreateTab()">添加</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-minus',plain:true" onclick="blogDeleteFunc()">删除</a>
			文章标题:<input id="blogTitleQ" type="text" class="easyui-textbox"/>
			文章分类:<input id="blogTypeQ" class="easyui-combobox"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-search',plain:true" onclick="searchBlogType()">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash',plain:true" onclick="clearSearch('#blogDataGridToolbar')">清空</a>
	</div>
<script>
$(function() {
	$.getJSON(basePath + '/admin/blogType/listAll', function(res){
			var data = res.data;
			data.unshift({
				'id':'',
				'name':'全部'
			});
	        $('#blogTypeQ').combobox({
	            data: data,
		        valueField: 'id',
		        textField: 'name',
		        editable: false
	        });
	});
	$('#blogDataGrid').datagrid({
		title : '文章列表',
		url : basePath + '/admin/blog/list',
		columns : [[ 
			 {field : 'ck',	checkbox : true},
             {field : 'id',		title : '编号',	width:50,	align:'center'}, 
             {field : 'title',	title : '标题',	width:100,	align:'center',	formatter: function(value,row,index){
					return '<a href="#" onclick="blogEditFunc('+index+')">'+value+'</a>';
				}},
             {field : 'blogTypeName',	title : '分类',	width:30,	align:'center', 
            	formatter: function(value,row,index){
            		return row.typeId ? row.typeName : "";
            	}
             },
             {field : 'createTime',	title : '发布时间',	width:100,	align:'center'},
             {field : 'status',	title : '状态',	width:100,	align:'center', 
            	formatter: function(value,row,index){
            		if(value == "0") return '<span class="s0">草稿</span>';
            		if(value == "-1") return '<span class="s-1">下线</span>';
            		if(value == "1") return '<span class="s1">上线</span>';
            		return value;
             	}
             },
        ]],
        toolbar: '#blogDataGridToolbar',
        method : 'GET',
        singleSelect : false,
        pagination : true,
        rownumbers : true,
        fitColumns : true,
        fit : true
	});
});

function searchBlogType(){
	$('#blogDataGrid').datagrid({
		queryParams: {
			title : $("#blogTitleQ").val(),
			typeId : $("#blogTypeQ").combobox("getValue"),
		}
	});
}

function openBlogCreateTab(){
	parentOpenTab('写博客', basePath + '/admin/blog/toAdd','fa fa-edit');
}
function blogEditFunc(index){
	$('#blogDataGrid').datagrid('unselectAll');
	$('#blogDataGrid').datagrid('selectRow', index);
	var row = $('#blogDataGrid').datagrid('getSelected');
	if(row){
		parentOpenTab('写博客', basePath + '/admin/blog/toEdit/'+row.id,'fa fa-edit');
	}
	event.stopPropagation();
}
function blogDeleteFunc(){
	var ids = concatIds('#blogDataGrid');
	if (ids != ''){
		$.messager.confirm('提示','真的要删除这个文章吗?',function(r){
			if (r){
				$.post(basePath + '/admin/blog/delete',{id : ids},function(result){
					if (result.status == 0){
						$('#blogDataGrid').datagrid('reload');
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
