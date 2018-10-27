$.extend($.fn.datagrid.defaults,{pageSize:15,pageList: [15,20,50,100]});
function openTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:99%;"></iframe>',
			closable : true,
			icon : icon
		});
//		tabClose();
	} else {
		$('#tabs').tabs('select', subtitle);
		refreshTab(subtitle, url);
	}
}

// 刷新
function refreshTab(title, url) {
	if ($('#tabs').tabs('exists', title)) {
		var src = url;
		var currTab = $('#tabs').tabs('getTab', title);
		if(!url){
			var iframe = $(currTab.panel('options').content);
			src = iframe.attr('src')
		}
		var content = '<iframe scrolling="auto" frameborder="0" src="' + src + '" style="width:100%;height:99%;"></iframe>';
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : content,
				closable : true
			}
		});
	}
}

// 刷新
function closeTab(title) {
	if ($('#tabs').tabs('exists', title)) {
		$('#tabs').tabs('close', title);
	}
}

function parentOpenTab(subtitle, url, icon){
	window.parent.openTab(subtitle, url, icon);
}
function parentRefreshTab(subtitle, url, icon){
	window.parent.refreshTab(subtitle, url, icon);
}
function parentCloseTab(subtitle, url, icon){
	window.parent.closeTab(subtitle, url, icon);
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}

function clearSearch(target) {
	$(target).find('input').each(function() {
		if ($(this).hasClass('textbox-text')) {//the old is $(this).hasClass('combo-text')
			var $formBox = $(this).parent().prev();
			if ($formBox.hasClass('easyui-datebox')) {
				$formBox.datebox('clear');
			} else if ($formBox.hasClass('easyui-datetimebox')) {
				$formBox.datetimebox('clear')
			} else if($formBox.hasClass('combobox-f')){
				$formBox.combobox('reset');
			} else{
				$(this).val('');
			}
		} else {
			$(this).val('');
		}
	});
}

function concatIds(target, prop, separator) {
	var rows = $(target).datagrid("getSelections");
	
	// 未选中记录返回空字符串
	if(rows.length == 0) {
		return "";
    }
	
	// 默认连接符
	var DEFAULT_PROP = "id";
	if (prop == undefined) {
		prop = DEFAULT_PROP;
	}
	
	// 默认连接符
	var DEFAULT_SEPARATOR = ",";
	if (separator == undefined) {
		separator = DEFAULT_SEPARATOR;
	}
	
	// 拼接字符串
	var ids = "";
    for(var i=0, len=rows.length; i <len; i++) {
		if(i > 0) {
			ids += separator;
		}
		var obj = rows[i];
		for ( var p in obj ){ // 方法
			if (p == prop) {
				ids += obj[p];
			} 
		}
    }
    return ids;
}
function setImagePreviews(sourceId, targetId, imgHeight, imgWidth) {
	var height = imgHeight || "180px", width = imgWidth || '200px';
	var docObj=document.getElementById(sourceId);
	var imgObjPreview=document.getElementById(targetId);
	if(docObj.files && docObj.files[0]){
		//火狐下，直接设img属性
//		imgObjPreview.style.display = 'block';
//		imgObjPreview.style.height = height;
//		imgObjPreview.style.width = width;
		//imgObjPreview.src = docObj.files[0].getAsDataURL();
		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
	} else {
		//IE下，使用滤镜
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag");
		//必须设置初始大小 
		localImagId.style.width = "250px";
		localImagId.style.height = "200px";
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try{
			localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch(e) {
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
	return true;
}
function validateRank(oldValue, obj){
	var newValue = $(obj).val();
	var reg = /^-?[0-9]\d*$/;
	if(newValue>2147483647||!(reg.test(newValue))){
		newValue = oldValue;
	}
	$(obj).val(newValue);
}