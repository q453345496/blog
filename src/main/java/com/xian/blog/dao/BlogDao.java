package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.Blog;

public interface BlogDao {

	public List<Blog> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(Blog blog);

	public int save(Blog blog);

	public int delete(Long id);

	public Blog get(Long id);
}
