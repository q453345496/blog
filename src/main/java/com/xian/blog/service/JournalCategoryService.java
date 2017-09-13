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
	private JournalCategoryDao journalCategoryDao;

	public List<JournalCategory> list(Map<String, Object> map) {
		return journalCategoryDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return journalCategoryDao.getTotal(map);
	}

	public int update(JournalCategory journalCategory) {
		return journalCategoryDao.update(journalCategory);
	}

	public int save(JournalCategory journalCategory) {
		return journalCategoryDao.save(journalCategory);
	}

	public int delete(Long id) {
		return journalCategoryDao.delete(id);
	}

	public JournalCategory get(Long id) {
		return journalCategoryDao.get(id);
	}
}
