<%@ page language="java" pageEncoding="UTF-8"%>
<div id="specialTopicRelateblogDialog" class="easyui-dialog" style="width:1200px;height:680px;" closed="true" modal="true">
	<div id="blogRelateTabs" class="easyui-tabs" data-options="fit:true" border=false>
		<div title="已关联博客" style="overflow:hidden;height:500px;">
			<div id="blogRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogUnrelateBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRelateBlogFunc()">取消关联</a>
					&nbsp;博客名称: <input type="text" id="blogNameR">
					&nbsp;分类: <input id="blogTypeIdR" style="width:145px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogRFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogRelatedDataGridToolbar')" plain="true">清空</a>
			</div> 
			<table id="blogRelatedDataGrid" fit="true" rownumbers="true" pagination="true" fitcolumns=true pageSize="20" data-options="toolbar:'#blogRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="id" width="20" align="left" hidden="true">ID</th>
						<th field="blogId" width="20" align="left">博客ID</th>
						<th field="blogTitle" width="100" align="left">名称</th>
						<th field="rank" width="20" align="left" formatter="rankFormatter">排序</th>
						<th field="blogTypeName" width="30" align="left">分类</th>
					</tr>
				</thead>
			</table>    
		</div>
		<div title="未关联博客" style="overflow:hidden;height:500px;"> 
			<div id="blogUnRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogAddRelateBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="blogAddRelateFunc()">添加关联</a>
					&nbsp;博客名称: <input type="text" id="blogNameUR">
					&nbsp;分类: <input id="blogTypeIdUR" style="width:145px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogURFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogUnRelatedDataGridToolbar')" plain="true">清空</a>
			</div>  
			<table id="blogUnRelatedDataGrid" rownumbers="true" fit="true" fitcolumns=true pagination="true" pageSize="20" data-options="toolbar:'#blogUnRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="blogId" width="20" align="left">博客ID</th>
						<th field="blogTitle" width="100" align="left">名称</th>
						<th field="blogTypeName" width="30" align="left">分类</th>
					</tr>
				</thead>
			</table>    
		</div>
	</div>
</div>
<script type="text/javascript">
function rankFormatter(value, row, index){
	return '<input type="text" size="6" value="'+value+'" onchange="validateRank('+value+', this)" onblur="updateTopicResourceRank(\'' + row.id +'\',\'' + value + '\', this)"/>';
}

function updateTopicResourceRank(id, oldValue, obj){
	var newValue = $(obj).val();
	if(newValue == "" || oldValue == newValue){
		return;
	}
	$.post(basePath + '/admin/specialTopicResource/update',{'id': id, 'rank':newValue},function(result){
        if (0 == result.status){
            $('#blogRelatedDataGrid').datagrid('reload');
        } else {
        	$.messager.show({ title: '错误', msg: result.msg });
        }
        
    },'json');
}
$(function(){
	$.getJSON(basePath + '/admin/blogType/listGroup', function(res){
		var data = res.data;
	    $('#blogTypeIdR').combobox({
	        data: data,
	        valueField: 'id',
	        textField: 'name',
	        groupField: 'parentName',
	        editable: false
	    });
	    $('#blogTypeIdUR').combobox({
	        data: data,
	        valueField: 'id',
	        textField: 'name',
	        groupField: 'parentName',
	        editable: false
	    });
	});
});

function openRelateBlogDialogFunc(rowIndex){
	clearSearch('#blogRelatedDataGridToolbar');
	clearSearch('#blogUnRelatedDataGridToolbar');
	$('#specialTopicDataGrid').datagrid('unselectAll');
	$('#specialTopicDataGrid').datagrid('selectRow', rowIndex);
    var row = $("#specialTopicDataGrid").datagrid("getSelected");
    if(row){
    	 $("#specialTopicRelateblogDialog").dialog("open").dialog('setTitle', "关联博客 【专题： " + row.name + "】");
    	 $('#blogRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/specialTopicResource/listRelate?topicId=' + row.id, queryParams:{}});
    	 $('#blogUnRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/specialTopicResource/listUnRelate?topicId=' + row.id, queryParams:{}});
    }
    $('#blogRelateTabs').tabs({
		'onSelect':function(title, index) {
			refreshBlogTabFunc(index);
		},
		'selected' : 0
	});
    event.stopPropagation();
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
		$.post(basePath + '/admin/specialTopicResource/save',{'topicId': row.id, 'ids':ids},function(result){
            if (0 == result.status){
                $('#blogUnRelatedDataGrid').datagrid('reload');
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
		$.post(basePath + '/admin/specialTopicResource/delete',{'ids':ids},function(result){
            if (0 == result.status){
                $('#blogRelatedDataGrid').datagrid('reload');
            } else {
                $.messager.show({ title: '错误', msg: result.msg });
            }
            $("#blogUnrelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要取消关联的博客',timeout:1000});
		$("#blogUnrelateBtn").linkbutton("enable");
	}
}
</script>