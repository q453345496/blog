package com.xian.blog.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xian.blog.dao.ColumnDao;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.Column;

@Service
@Transactional
public class ColumnService {

	@Resource
	private ColumnDao columnDao;

	public List<Column> list(Wrapper<Column> wrapper) {
		return columnDao.selectList(wrapper);
	}

	public int update(Column column) {
		return columnDao.updateById(column);
	}

	public int save(Column column) {
		Column parent = columnDao.selectById(column.getParentId());
		if (!parent.getParent()) {
			parent.setParent(true);
			columnDao.updateById(parent);
		}
		column.setParent(false);
		return columnDao.insert(column);
	}

	public void delete(Long id) {
		Column column = columnDao.selectById(id);
		if (column != null) {
			if (column.getParent()) {
				throw new CheckException("该节点存在子节点，无法删除");
			}
			if (-1 == column.getParentId()) {
				throw new CheckException("根节点无法删除");
			}
			columnDao.deleteById(id);
			int subCount = getSubCount(column.getParentId());
			if (subCount == 0) {
				Column parent = new Column();
				parent.setId(column.getParentId());
				parent.setParent(false);
				columnDao.updateById(parent);
			}
		}
	}

	public Column get(Long id) {
		return columnDao.selectById(id);
	}

	public int getSubCount(Long parentId) {
		return columnDao.selectCount(new EntityWrapper<Column>().eq("parent_id", parentId));
	}
}
