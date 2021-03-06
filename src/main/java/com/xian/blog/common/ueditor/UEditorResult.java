package com.xian.blog.common.ueditor;

import com.xian.blog.constants.FTPConstant;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public class UEditorResult {
	protected String state = FTPConstant.SUCCESS;
	protected String url;

	public UEditorResult() {
		super();
	}

	public UEditorResult(String url) {
		this.url = url;
	}

	public static UEditorResult errorResult(String state) {
		UEditorResult uEditorUploadResult = new UEditorResult();
		uEditorUploadResult.setState(state);
		return uEditorUploadResult;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
