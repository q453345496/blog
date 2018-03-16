package com.xian.blog.common.ueditor;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public class UEditorRemoteResult extends UEditorResult {

	private String source;//ueditor源码里,是源路径,只有是成功而且该值为源路径时才替换img的src

	public UEditorRemoteResult() {
		super();
	}

	public UEditorRemoteResult(String source, String url) {
		super(url);
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
