package com.xian.blog.common;

/**
 * Date:2016年7月29日下午11:42:58
 * 
 */
public class Page {
	private int page;
	private int pageSize;

	public Page() {
	}

	public Page(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public Page(Integer page, Integer pageSize) {
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (page - 1) * pageSize;
	}

}
