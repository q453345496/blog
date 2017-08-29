package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.JournalCategory;

public interface JournalCategoryDao {

	public List<JournalCategory> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(JournalCategory category);

	public int save(JournalCategory category);

	public int delete(Long id);
}
