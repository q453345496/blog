//package com.xian.blog.common;
//
//public class Page {
//	private int page;
//	private int pageSize;
//
//	public Page() {
//	}
//
//	public Page(Integer page, Integer pageSize) {
//		if (page == null || page <= 0) {
//			page = 1;
//		}
//		if (pageSize == null || page <= 0) {
//			pageSize = 20;
//		}
//		this.page = page;
//		this.pageSize = pageSize;
//	}
//
//	public int getPage() {
//		return page;
//	}
//
//	public void setPage(int page) {
//		this.page = page;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public int getStart() {
//		return (page - 1) * pageSize;
//	}
//
//}
