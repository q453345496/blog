UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
	var test = true;
	if (action == 'config') {
		if(test){
			return basePath + '/resources/config2.json';
		}
		return basePath + '/resources/config.json';
	} else if (action == 'uploadImage') {
		return basePath + '/ueditor/uploadImage';
	} else if (action == 'uploadVideo') {
		return basePath + '/ueditor/uploadVideo';
	} else if (action == 'uploadFile') {
		return basePath + '/ueditor/uploadFile';
	} else if (action == 'listFile') {
		return basePath + '/ueditor/listImage';
	} else if (action == 'listImage') {
		return basePath + '/ueditor/listFile';
	} else if (action == 'catchImage') {
		return basePath + '/ueditor/catchImage';
	} else {
		return this._bkGetActionUrl.call(this, action);
	}
}