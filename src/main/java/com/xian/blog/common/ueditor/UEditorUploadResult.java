package com.xian.blog.common.ueditor;

public class UEditorUploadResult extends UEditorResult {

	private String title;

	public UEditorUploadResult() {
		super();
	}

	public UEditorUploadResult(String title, String url) {
		super(url);
		this.title = title;
	}

	public String getOriginal() {
		return title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
