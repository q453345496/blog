package com.xian.blog.common;

import java.util.List;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public final class DataGridResult {
	private int total;
	private List<?> rows;

	public DataGridResult() {
	}

	public DataGridResult(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
