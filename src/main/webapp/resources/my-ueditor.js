UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
	var test = true;
	if (action == 'config') {
		if(test){
			return '/blog/resources/config2.json';
		}
		return '/blog/resources/config.json';
	} else if (action == 'uploadImage') {
		return '/blog/ueditor/uploadImage';
	} else if (action == 'uploadVideo') {
		return '/blog/ueditor/uploadVideo';
	} else if (action == 'uploadFile') {
		return '/blog/ueditor/uploadFile';
	} else if (action == 'listFile') {
		return '/blog/ueditor/listImage';
	} else if (action == 'listImage') {
		return '/blog/ueditor/listFile';
	} else if (action == 'catchImage') {
		return '/blog/ueditor/catchImage';
	} else {
		return this._bkGetActionUrl.call(this, action);
	}
}