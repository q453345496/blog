package com.xian.blog.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.dao.JournalCategoryDao;
import com.xian.blog.model.JournalCategory;

@Service
@Transactional
public class JournalCategoryService {
	@Resource
	private JournalCategoryDao categoryDao;

	public List<JournalCategory> list(Map<String, Object> map) {
		return categoryDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return categoryDao.getTotal(map);
	}

	public int update(JournalCategory category) {
		return categoryDao.update(category);
	}

	public int save(JournalCategory category) {
		return categoryDao.save(category);
	}

	public int delete(Long id) {
		return categoryDao.delete(id);
	}
}
