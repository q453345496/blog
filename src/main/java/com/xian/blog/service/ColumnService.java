package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.dao.ColumnDao;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.Column;

@Service
@Transactional
public class ColumnService {

	@Resource
	private ColumnDao columnDao;

	public List<Column> list(Map<String, Object> map) {
		return columnDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return columnDao.getTotal(map);
	}

	public int update(Column column) {
		column.setModifyTime(new Date());
		return columnDao.update(column);
	}

	public int save(Column column) {
		Column parent = columnDao.get(column.getParentId());
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			columnDao.update(parent);
		}
		column.setIsParent(false);
		Date now = new Date();
		column.setCreateTime(now);
		column.setModifyTime(now);
		return columnDao.save(column);
	}

	public void delete(Long id) {
		Column column = columnDao.get(id);
		if (column != null) {
			if (column.getIsParent()) {
				throw new CheckException("该节点存在子节点，无法删除");
			}
			if (-1 == column.getParentId()) {
				throw new CheckException("根节点无法删除");
			}
			columnDao.delete(id);
			int subCount = columnDao.getSubCount(column.getParentId());
			if (subCount == 0) {
				Column parent = new Column();
				parent.setId(column.getParentId());
				parent.setIsParent(false);
				columnDao.update(parent);
			}
		}
	}

	public Column get(Long id) {
		return columnDao.get(id);
	}
}
