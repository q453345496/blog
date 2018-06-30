package com.xian.blog.common;

import java.util.List;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public final class DataGridResult {
	private long total;
	private List<?> rows;

	public DataGridResult() {
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
