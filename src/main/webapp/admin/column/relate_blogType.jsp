<%@ page language="java" pageEncoding="UTF-8"%>
<div id="columnRelateblogTypeDialog" class="easyui-dialog" style="width:1200px;height:680px;" closed="true" modal="true">
	<div id="blogTypeRelateTabs" class="easyui-tabs" data-options="fit:true" border=false>
		<div title="已关联分类" style="overflow:hidden;height:500px;">
			<div id="blogTypeRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogTypeUnrelateBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRelateBlogTypeFunc()">取消关联</a>
					&nbsp;分类名称: <input type="text" id="blogTypeNameR">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogTypeRFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogTypeRelatedDataGridToolbar')" plain="true">清空</a>
			</div> 
			<table id="blogTypeRelatedDataGrid" fit="true" rownumbers="true" pagination="true" fitcolumns=true pageSize="20" data-options="toolbar:'#blogTypeRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="id" width="20" align="left" hidden="true">ID</th>
						<th field="typeId" width="20" align="left">分类ID</th>
						<th field="blogTypeName" width="100" align="left">名称</th>
					</tr>
				</thead>
			</table>    
		</div>
		<div title="未关联分类" style="overflow:hidden;height:500px;"> 
			<div id="blogTypeUnRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogTypeAddRelateBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="blogTypeAddRelateFunc()">添加关联</a>
					&nbsp;分类名称: <input type="text" id="blogTypeNameUR">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogTypeURFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogTypeUnRelatedDataGridToolbar')" plain="true">清空</a>
			</div>  
			<table id="blogTypeUnRelatedDataGrid" rownumbers="true" fit="true" fitcolumns=true pagination="true" pageSize="20" data-options="toolbar:'#blogTypeUnRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="typeId" width="20" align="left">分类ID</th>
						<th field="blogTypeName" width="100" align="left">名称</th>
					</tr>
				</thead>
			</table>    
		</div>
	</div>
</div>
<script type="text/javascript">
function openRelateBlogTypeDialogFunc(rowIndex){
	clearSearch('#blogTypeRelatedDataGridToolbar');
	clearSearch('#blogTypeUnRelatedDataGridToolbar');
	$('#columnTreeGrid').treegrid('select', rowIndex);
    var row = $("#columnTreeGrid").treegrid("find", rowIndex);
    if(row){
    	 $("#columnRelateblogTypeDialog").dialog("open").dialog('setTitle', "关联分类 【栏目： " + row.name + "】");
    	 $('#blogTypeRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/columnResource/listRelate?columnId=' + row.id, queryParams:{}});
    	 $('#blogTypeUnRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/columnResource/listUnRelate?columnId=' + row.id, queryParams:{}});
    }
    $('#blogTypeRelateTabs').tabs({
		'onSelect':function(title, index) {
			refreshBlogTypeTabFunc(index);
		},
		'selected' : 0
	});
    
}

function refreshBlogTypeTabFunc(index) {
	if (index == 0) {
		//$("#blogTypeRelatedDataGrid").datagrid('load', {});
		doSearchBlogTypeRFunc();
	} else {
		//$("#blogTypeUnRelatedDataGrid").datagrid('load', {});
		doSearchBlogTypeURFunc()
	}
}

function doSearchBlogTypeRFunc(){
	var params = {
			'blogTypeName': $("#blogTypeNameR").val()
	   	};
	$('#blogTypeRelatedDataGrid').datagrid('reload', params);
}

function doSearchBlogTypeURFunc(){
	var params = {
			'blogTypeName': $("#blogTypeNameUR").val()
	   	};
	$('#blogTypeUnRelatedDataGrid').datagrid('reload', params);
}

function blogTypeAddRelateFunc(){
	$("#blogTypeAddRelateBtn").linkbutton("disable");
	var ids = concatIds('#blogTypeUnRelatedDataGrid', 'typeId');
	var row = $("#columnTreeGrid").datagrid("getSelected");
	if (row && row.id && ids != "") {
		$.post(basePath + '/admin/columnResource/save',{'columnId': row.id, 'ids':ids},function(result){
            if (0 == result.status){
                $('#blogTypeUnRelatedDataGrid').datagrid('reload');
            } else {
            	$.messager.show({ title: '错误', msg: result.msg });
            }
            
            $("#blogTypeAddRelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要关联的栏目',timeout:1000});
		$("#blogTypeAddRelateBtn").linkbutton("enable");
	}
}

function delRelateBlogTypeFunc(){
	$("#blogTypeUnrelateBtn").linkbutton("disable");
	var ids = concatIds('#blogTypeRelatedDataGrid','id');
	if (ids != "") {
		$.post(basePath + '/admin/columnResource/delete',{'ids':ids},function(result){
            if (0 == result.status){
                $('#blogTypeRelatedDataGrid').datagrid('reload');
            } else {
                $.messager.show({ title: '错误', msg: result.msg });
            }
            $("#blogTypeUnrelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要取消关联的分类',timeout:1000});
		$("#blogTypeUnrelateBtn").linkbutton("enable");
	}
}
</script>