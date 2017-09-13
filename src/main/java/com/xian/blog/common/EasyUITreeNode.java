package com.xian.blog.common;

public final class EasyUITreeNode {
	private long id;
	private String text;
	/**
	 * easyui的Tree显示使用,当设置为 'closed'时，该节点有子节点，并且将从远程站点加载它们
	 */
	private String state;

	private static final String OPEN = "open";
	private static final String CLOSED = "closed";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(boolean open) {
		this.state = open ? OPEN : CLOSED;
	}
}
