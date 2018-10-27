<%@ page language="java" pageEncoding="UTF-8"%>
<div id="columnRelateblogDialog" class="easyui-dialog" style="width:1200px;height:680px;" closed="true" modal="true">
	<div id="columnBlogRelateTabs" class="easyui-tabs" data-options="fit:true" border=false>
		<div title="已关联博客" style="overflow:hidden;height:500px;">
			<div id="columnBlogRelateRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="columnBlogRelateUnrelateBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRelateColumnBlogRelateFunc()">取消关联</a>
					&nbsp;博客名称: <input type="text" id="blogNameR">
					&nbsp;分类: <input id="blogTypeIdR" style="width:145px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchColumnBlogRelateRFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#columnBlogRelateRelatedDataGridToolbar')" plain="true">清空</a>
			</div> 
			<table id="columnBlogRelateRelatedDataGrid" fit="true" rownumbers="true" pagination="true" fitcolumns=true pageSize="20" data-options="toolbar:'#columnBlogRelateRelatedDataGridToolbar',height:500" border=false>
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
			<div id="columnBlogRelateUnRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="columnBlogRelateAddRelateBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRelateColumnBlogRelateFunc()">添加关联</a>
					&nbsp;博客名称: <input type="text" id="blogNameUR">
					&nbsp;分类: <input id="blogTypeIdUR" style="width:145px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchColumnBlogRelateURFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#columnBlogRelateUnRelatedDataGridToolbar')" plain="true">清空</a>
			</div>  
			<table id="columnBlogRelateUnRelatedDataGrid" rownumbers="true" fit="true" fitcolumns=true pagination="true" pageSize="20" data-options="toolbar:'#columnBlogRelateUnRelatedDataGridToolbar',height:500" border=false>
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
	return '<input type="text" size="6" value="'+value+'" onchange="validateRank('+value+', this)" onblur="updateColumnBlogRank(\'' + row.id +'\',\'' + value + '\', this)"/>';
}

function updateColumnBlogRank(id, oldValue, obj){
	var newValue = $(obj).val();
	if(newValue == "" || oldValue == newValue){
		return;
	}
	$.post(basePath + '/admin/columnBlogRelate/update',{'id': id, 'rank':newValue},function(result){
        if (0 == result.status){
            $('#columnBlogRelateRelatedDataGrid').datagrid('reload');
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

function openColumnBlogRelateDialogFunc(rowIndex){
	clearSearch('#columnBlogRelateRelatedDataGridToolbar');
	clearSearch('#columnBlogRelateUnRelatedDataGridToolbar');
	$('#columnTreeGrid').treegrid('unselectAll');
	$('#columnTreeGrid').treegrid('select', rowIndex);
    var row = $("#columnTreeGrid").treegrid("find", rowIndex);
    if(row){
    	 $("#columnRelateblogDialog").dialog("open").dialog('setTitle', "关联博客 【栏目： " + row.name + "】");
    	 $('#columnBlogRelateRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/columnBlogRelate/listRelate?columnId=' + row.id, queryParams:{}});
    	 $('#columnBlogRelateUnRelatedDataGrid').datagrid({method : 'GET', url : basePath + '/admin/columnBlogRelate/listUnRelate?columnId=' + row.id, queryParams:{}});
    }
    $('#columnBlogRelateTabs').tabs({
		'onSelect':function(title, index) {
			refreshColumnBlogRelateTabFunc(index);
		},
		'selected' : 0
	});
    event.stopPropagation();
    
}

function refreshColumnBlogRelateTabFunc(index) {
	if (index == 0) {
		//$("#columnBlogRelateRelatedDataGrid").datagrid('load', {});
		doSearchColumnBlogRelateRFunc();
	} else {
		//$("#columnBlogRelateUnRelatedDataGrid").datagrid('load', {});
		doSearchColumnBlogRelateURFunc()
	}
}

function doSearchColumnBlogRelateRFunc(){
	var params = {
			'blogTitle': $("#blogNameR").val(),
			'blogTypeId': $("#blogTypeIdR").combobox("getValue")
	   	};
	$('#columnBlogRelateRelatedDataGrid').datagrid('reload', params);
}

function doSearchColumnBlogRelateURFunc(){
	var params = {
			'blogTitle': $("#blogNameUR").val(),
			'blogTypeId': $("#blogTypeIdUR").combobox("getValue")
	   	};
	$('#columnBlogRelateUnRelatedDataGrid').datagrid('reload', params);
}

function addRelateColumnBlogRelateFunc(){
	$("#columnBlogRelateAddRelateBtn").linkbutton("disable");
	var ids = concatIds('#columnBlogRelateUnRelatedDataGrid', 'blogId');
	var row = $("#columnTreeGrid").datagrid("getSelected");
	if (row && row.id && ids != "") {
		$.post(basePath + '/admin/columnBlogRelate/save',{'columnId': row.id, 'ids':ids},function(result){
            if (0 == result.status){
                $('#columnBlogRelateUnRelatedDataGrid').datagrid('reload');
    			$("#columnBlogRelateUnRelatedDataGrid").datagrid({selectOnCheck:true});
            } else {
            	$.messager.show({ title: '错误', msg: result.msg });
            }
            
            $("#columnBlogRelateAddRelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要关联的栏目',timeout:1000});
		$("#columnBlogRelateAddRelateBtn").linkbutton("enable");
	}
}

function delRelateColumnBlogRelateFunc(){
	$("#columnBlogRelateUnrelateBtn").linkbutton("disable");
	var ids = concatIds('#columnBlogRelateRelatedDataGrid','id');
	if (ids != "") {
		$.post(basePath + '/admin/columnBlogRelate/delete',{'ids':ids},function(result){
            if (0 == result.status){
                $('#columnBlogRelateRelatedDataGrid').datagrid('reload');
            	$("#columnBlogRelateRelatedDataGrid").datagrid({selectOnCheck:true});
            } else {
                $.messager.show({ title: '错误', msg: result.msg });
            }
            $("#columnBlogRelateUnrelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要取消关联的博客',timeout:1000});
		$("#columnBlogRelateUnrelateBtn").linkbutton("enable");
	}
}
</script>