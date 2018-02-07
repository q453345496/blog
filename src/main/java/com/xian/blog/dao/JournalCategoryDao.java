package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.JournalCategory;

public interface JournalCategoryDao {

	public List<JournalCategory> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(JournalCategory journalCategory);

	public int save(JournalCategory journalCategory);

	public int delete(Long id);

	public JournalCategory get(Long id);
	
	public int getSubCount(Long parentId);
}
