package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.BlogType;

public interface BlogTypeDao {

	public List<BlogType> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(BlogType blogType);

	public int save(BlogType blogType);

	public int delete(Long id);
}
