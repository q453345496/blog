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
		if ($(this).hasClass('combo-text')) {
			var $formBox = $(this).parent().prev();
			if ($formBox.hasClass('easyui-datebox')) {
				$formBox.datebox('clear');
			} else if ($formBox.hasClass('easyui-datetimebox')) {
				$formBox.datetimebox('clear')
			} else {
				$formBox.combobox('reset');
			}
		} else {
			$(this).val('');
		}
	});
}
