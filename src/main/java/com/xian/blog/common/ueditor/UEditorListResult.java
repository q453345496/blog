package com.xian.blog.common.ueditor;

import java.util.List;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public final class UEditorListResult {
	private static final UEditorListResult ERROR = new UEditorListResult();
	static {
		ERROR.setState("error");
	}
	private String state;
	private List<?> list;
	private int start;
	private int total;

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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public static UEditorListResult error() {
		return ERROR;
	}
}
