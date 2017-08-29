package com.xian.blog.common.ueditor;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public class UEditorUploadResult extends UEditorResult {

	private String title;
	private String original;

	public UEditorUploadResult() {
		super();
	}

	public UEditorUploadResult(String title, String original, String url) {
		super(url);
		this.title = title;
		this.original = original;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
