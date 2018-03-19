package com.xian.blog.common.ueditor;

import java.util.List;

import com.xian.blog.constants.FTPConstant;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public final class UEditorCatchResult {
	private String state = FTPConstant.SUCCESS;

	private List<?> list;

	public UEditorCatchResult() {
		super();
	}

	public static UEditorCatchResult errorResult(String state) {
		UEditorCatchResult uEditorUploadResult = new UEditorCatchResult();
		uEditorUploadResult.setState(state);
		return uEditorUploadResult;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}
