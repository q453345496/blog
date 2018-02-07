package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.Column;

public interface ColumnDao {

	public List<Column> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(Column column);

	public int save(Column column);

	public int delete(Long id);

	public Column get(Long id);
	
	public int getSubCount(Long parentId);
}
