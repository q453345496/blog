package com.xian.blog.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.dao.JournalCategoryDao;
import com.xian.blog.exception.CheckException;
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
		JournalCategory parent = journalCategoryDao.get(journalCategory.getParentId());
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			journalCategoryDao.update(parent);
		}
		journalCategory.setIsParent(false);
		return journalCategoryDao.save(journalCategory);
	}

	public void delete(Long id) {
		JournalCategory journalCategory = journalCategoryDao.get(id);
		if (journalCategory != null) {
			if(journalCategory.getIsParent()){
				throw new CheckException("该节点存在子节点，无法删除");
			}
			if (0 == journalCategory.getParentId()) {
				throw new CheckException("一级节点无法删除");
			}
			journalCategoryDao.delete(id);
			int subCount = journalCategoryDao.getSubCount(journalCategory.getParentId());
			if (subCount == 0) {
				JournalCategory parent = new JournalCategory();
				parent.setId(journalCategory.getParentId());
				parent.setIsParent(false);
				journalCategoryDao.update(parent);
			}
		}
	}

	public JournalCategory get(Long id) {
		return journalCategoryDao.get(id);
	}
}
