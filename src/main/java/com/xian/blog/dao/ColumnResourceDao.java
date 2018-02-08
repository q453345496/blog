package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.ColumnResource;

public interface ColumnResourceDao {

	public List<ColumnResource> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(ColumnResource columnResource);

	public int save(ColumnResource columnResource);

	public int delete(Long id);

	public ColumnResource get(Long id);

	public int getRelateTotal(Map<String, Object> map);

	public List<ColumnResource> listRelate(Map<String, Object> map);

	public int getUnRelateTotal(Map<String, Object> map);

	public List<ColumnResource> listUnRelate(Map<String, Object> map);
	
}
