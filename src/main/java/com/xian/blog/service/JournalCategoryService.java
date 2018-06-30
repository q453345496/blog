package com.xian.blog.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xian.blog.dao.JournalCategoryDao;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.JournalCategory;

@Service
@Transactional
public class JournalCategoryService {

	@Resource
	private JournalCategoryDao journalCategoryDao;

	public List<JournalCategory> list(Wrapper<JournalCategory> wrapper) {
		return journalCategoryDao.selectList(wrapper);
	}

	public int update(JournalCategory journalCategory) {
		return journalCategoryDao.updateById(journalCategory);
	}

	public int save(JournalCategory journalCategory) {
		JournalCategory parent = journalCategoryDao.selectById(journalCategory.getParentId());
		if (!parent.getParent()) {
			parent.setParent(true);
			journalCategoryDao.updateById(parent);
		}
		journalCategory.setParent(false);
		return journalCategoryDao.insert(journalCategory);
	}

	public void delete(Long id) {
		JournalCategory journalCategory = journalCategoryDao.selectById(id);
		if (journalCategory != null) {
			if (journalCategory.getParent()) {
				throw new CheckException("该节点存在子节点，无法删除");
			}
			if (-1 == journalCategory.getParentId()) {
				throw new CheckException("根节点无法删除");
			}
			journalCategoryDao.deleteById(id);
			int subCount = getSubCount(journalCategory.getParentId());
			if (subCount == 0) {
				JournalCategory parent = new JournalCategory();
				parent.setId(journalCategory.getParentId());
				parent.setParent(false);
				journalCategoryDao.updateById(parent);
			}
		}
	}

	public JournalCategory get(Long id) {
		return journalCategoryDao.selectById(id);
	}

	public int getSubCount(Long parentId) {
		return journalCategoryDao.selectCount(new EntityWrapper<JournalCategory>().eq("parent_id", parentId));
	}

}
