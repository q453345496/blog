<%@ page language="java" pageEncoding="UTF-8"%>
<div id="contentRelateDialog" class="easyui-dialog" style="width:1200px;height:680px;" closed="true" modal="true">
	<div id="blogRelateTabs" class="easyui-tabs" data-options="fit:true" border=false>
		<div title="已关联文章" style="overflow:hidden;height:500px;">
			<div id="blogRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogUnrelateBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRelateBlogFunc()">取消关联</a>
					&nbsp;文章名称: <input type="text" id="blogNameR">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogRFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogRelatedDataGridToolbar')" plain="true">清空</a>
			</div> 
			<table id="blogRelatedDataGrid" fit="true" rownumbers="true" pagination="true" fitcolumns=true pageSize="20" data-options="toolbar:'#blogRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="id" width="100" align="left" hidden="true">ID</th>
						<th field="blogId" width="100" align="left" hidden="true">文章ID</th>
						<th field="name" width="100" align="left" hidden="true">名称</th>
					</tr>
				</thead>
			</table>    
		</div>
		<div title="未关联文章" style="overflow:hidden;height:500px;"> 
			<div id="blogUnRelatedDataGridToolbar" style="padding:5px">
					<a href="#" id="blogAddRelateBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="blogAddRelateFunc()">添加关联</a>
					&nbsp;文章名称: <input type="text" id="blogNameUR">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchBlogURFunc()" plain="true">查询</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearSearch('#blogUnRelatedDataGridToolbar')" plain="true">清空</a>
			</div>  
			<table id="blogUnRelatedDataGrid" rownumbers="true" fit="true" fitcolumns=true pagination="true" pageSize="20" data-options="toolbar:'#blogUnRelatedDataGridToolbar',height:500" border=false>
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="blogId" width="100" align="left" hidden="true">文章ID</th>
						<th field="name" width="100" align="left" hidden="true">名称</th>
					</tr>
				</thead>
			</table>    
		</div>
	</div>
</div>
<script type="text/javascript">
function blogOpenRelateDialogFunc(rowIndex){
	$('#specialTopicDataGrid').datagrid('selectRow', rowIndex);
    var row = $("#specialTopicDataGrid").datagrid("getSelected");
    if(row){
    	 $("#contentRelateDialog").dialog("open").dialog('setTitle',"关联文章 【专题： "+row.name+"】");
    	 $('#blogRelatedDataGrid').datagrid({url : '?id='+row.id});
    	 $('#blogUnRelatedDataGrid').datagrid({url : '?id='+row.id});
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
		$("#blogRelatedDataGrid").datagrid('load', {});
	} else {
		$("#blogUnRelatedDataGrid").datagrid('load', {});
	}
}

function doSearchBlogRFunc(){
	var params = {
			'name': $("#blogNameR").val()
	   	};
	$('#blogRelatedDataGrid').datagrid('reload', params);
}

function doSearchBlogURFunc(){
	var params = {
			'name': $("#blogNameUR").val()
	   	};
	$('#blogUnRelatedDataGrid').datagrid('reload', params);
}

function blogAddRelateFunc(){
	$("#blogAddRelateBtn").linkbutton("disable");
	var ids = concatIds('#blogUnRelatedDataGrid', 'blogId');
	if (ids != "") {
		$.post('',{'ids':ids},function(result){
            if ("0" == result.ret){
            	$.messager.show({ title: '提示', msg: result.retInfo });
                $('#blogUnRelatedDataGrid').datagrid('reload');
    			$("#blogUnRelatedDataGrid").datagrid({selectOnCheck:true});
            } else {
            	$.messager.show({ title: '提示', msg: result.retInfo });
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
		$.post('',{'ids':ids},function(result){
            if ("0" == result.ret){
            	$.messager.show({ title: '提示', msg: result.retInfo });
                $('#blogRelatedDataGrid').datagrid('reload');
            	$("#blogRelatedDataGrid").datagrid({selectOnCheck:true});
            } else {
                $.messager.show({ title: '提示', msg: result.retInfo });
            }
            $("#blogUnrelateBtn").linkbutton("enable");
        },'json');
	} else {
		$.messager.show({title:'提示',msg:'请选择要取消关联的文章',timeout:1000});
		$("#blogUnrelateBtn").linkbutton("enable");
	}
}
</script>