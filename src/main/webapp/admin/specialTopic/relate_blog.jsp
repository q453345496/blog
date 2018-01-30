<%@ page language="java" pageEncoding="UTF-8"%>
<div id="specialTopicRelateblogDialog" class="easyui-dialog" style="width:1200px;height:680px;" closed="true" modal="true">
	<div id="blogRelateTabs" class="easyui-tabs" data-options="fit:true" border=false>
		<div title="已关联文章" style="overflow:hidden;height:500px;">
			<div id="blogRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogUnrelateBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRelateBlogFunc()">取消关联</a>
					&nbsp;文章名称: <input type="text" id="blogNameR">
					&nbsp;分类: <input id="blogTypeIdR" style="width:145px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogRFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogRelatedDataGridToolbar')" plain="true">清空</a>
			</div> 
			<table id="blogRelatedDataGrid" fit="true" rownumbers="true" pagination="true" fitcolumns=true pageSize="20" data-options="toolbar:'#blogRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="id" width="20" align="left" hidden="true">ID</th>
						<th field="blogId" width="20" align="left">文章ID</th>
						<th field="blogTitle" width="100" align="left">名称</th>
						<th field="blogTypeName" width="30" align="left">分类</th>
					</tr>
				</thead>
			</table>    
		</div>
		<div title="未关联文章" style="overflow:hidden;height:500px;"> 
			<div id="blogUnRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogAddRelateBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="blogAddRelateFunc()">添加关联</a>
					&nbsp;文章名称: <input type="text" id="blogNameUR">
					&nbsp;分类: <input id="blogTypeIdUR" style="width:145px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogURFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogUnRelatedDataGridToolbar')" plain="true">清空</a>
			</div>  
			<table id="blogUnRelatedDataGrid" rownumbers="true" fit="true" fitcolumns=true pagination="true" pageSize="20" data-options="toolbar:'#blogUnRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="blogId" width="20" align="left">文章ID</th>
						<th field="blogTitle" width="100" align="left">名称</th>
						<th field="blogTypeName" width="30" align="left">分类</th>
					</tr>
				</thead>
			</table>    
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$.getJSON(basePath + '/admin/blogType/listAll', function(res){
		var data = res.data;
		data.unshift({
			'id':'',
			'name':'全部'
		});
	    $('#blogTypeIdR').combobox({
	        data: data,
	        valueField: 'id',
	        textField: 'name',
	        editable: false
	    });
	    $('#blogTypeIdUR').combobox({
	        data: data,
	        valueField: 'id',
	        textField: 'name',
	        editable: false
	    });
	});
});

function openRelateBlogDialogFunc(rowIndex){
	clearSearch('#blogRelatedDataGridToolbar');
	clearSearch('#blogUnRelatedDataGridToolbar');
	$('#specialTopicDataGrid').datagrid('selectRow', rowIndex);
    var row = $("#specialTopicDataGrid").datagrid("getSelected");
    if(row){
    	 $("#specialTopicRelateblogDialog").dialog("open").dialog('setTitle', "关联文章 【专题： " + row.name + "】");
    	 $('#blogRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/topicResource/listRelate?topicId=' + row.id, queryParams:{}});
    	 $('#blogUnRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/topicResource/listUnRelate?topicId=' + row.id, queryParams:{}});
    }
    $('#blogRelateTabs').tabs({
		'onSelect':function(title, index) {
			refreshBlogTabFunc(index);
		},
		'selected' : 0
	});
}

function refreshBlogTabFunc(index) {
	if (index == 0) {
		doSearchBlogRFunc();
	} else {
		doSearchBlogURFunc();
	}
}

function doSearchBlogRFunc(){
	var params = {
			'blogTitle': $("#blogNameR").val(),
			'blogTypeId': $("#blogTypeIdR").combobox("getValue")
	   	};
	$('#blogRelatedDataGrid').datagrid('reload', params);
}

function doSearchBlogURFunc(){
	var params = {
			'blogTitle': $("#blogNameUR").val(),
			'blogTypeId': $("#blogTypeIdUR").combobox("getValue")
	   	};
	$('#blogUnRelatedDataGrid').datagrid('reload', params);
}

function blogAddRelateFunc(){
	$("#blogAddRelateBtn").linkbutton("disable");
	var ids = concatIds('#blogUnRelatedDataGrid', 'blogId');
	var row = $("#specialTopicDataGrid").datagrid("getSelected");
	if (row && row.id && ids != "") {
		$.post(basePath + '/admin/topicResource/save',{'topicId': row.id, 'ids':ids},function(result){
            if (0 == result.status){
                $('#blogUnRelatedDataGrid').datagrid('reload');
    			$("#blogUnRelatedDataGrid").datagrid({selectOnCheck:true});
            } else {
            	$.messager.show({ title: '错误', msg: result.msg });
            }
            
            $("#blogAddRelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要关联的专题',timeout:1000});
		$("#blogAddRelateBtn").linkbutton("enable");
	}
}

function delRelateBlogFunc(){
	$("#blogUnrelateBtn").linkbutton("disable");
	var ids = concatIds('#blogRelatedDataGrid','id');
	if (ids != "") {
		$.post(basePath + '/admin/topicResource/delete',{'ids':ids},function(result){
            if (0 == result.status){
                $('#blogRelatedDataGrid').datagrid('reload');
            	$("#blogRelatedDataGrid").datagrid({selectOnCheck:true});
            } else {
                $.messager.show({ title: '错误', msg: result.msg });
            }
            $("#blogUnrelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要取消关联的文章',timeout:1000});
		$("#blogUnrelateBtn").linkbutton("enable");
	}
}
</script>