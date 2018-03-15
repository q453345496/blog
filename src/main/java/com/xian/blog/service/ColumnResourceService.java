package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ColumnResourceDao;
import com.xian.blog.model.ColumnResource;

@Service
@Transactional
public class ColumnResourceService {

	@Resource
	private ColumnResourceDao columnResourceDao;

	public List<ColumnResource> list(Map<String, Object> map) {
		return columnResourceDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return columnResourceDao.getTotal(map);
	}

	public int update(ColumnResource columnResource) {
		columnResource.setModifyTime(new Date());
		return columnResourceDao.update(columnResource);
	}

	public int save(ColumnResource columnResource) {
		Date now = new Date();
		columnResource.setCreateTime(now);
		columnResource.setModifyTime(now);
		return columnResourceDao.save(columnResource);
	}

	public int delete(Long id) {
		return columnResourceDao.delete(id);
	}

	public ColumnResource get(Long id) {
		return columnResourceDao.get(id);
	}

	public int getRelateTotal(Map<String, Object> map) {
		return columnResourceDao.getRelateTotal(map);
	}

	public List<ColumnResource> listRelate(Map<String, Object> map) {
		return columnResourceDao.listRelate(map);
	}
	
	public DataGridResult pageRelate(Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		vo.setTotal(getRelateTotal(map));
		vo.setRows(listRelate(map));
		return vo;
	}
	
	public int getUnRelateTotal(Map<String, Object> map) {
		return columnResourceDao.getUnRelateTotal(map);
	}

	public List<ColumnResource> listUnRelate(Map<String, Object> map) {
		return columnResourceDao.listUnRelate(map);
	}
	
	public DataGridResult pageUnRelate(Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		vo.setTotal(getUnRelateTotal(map));
		vo.setRows(listUnRelate(map));
		return vo;
	}
	
	public void save(Long columnId, Long[] ids) {
		for (Long typeId : ids) {
			ColumnResource columnResource = new ColumnResource();
			columnResource.setColumnId(columnId);
			columnResource.setTypeId(typeId);
			columnResource.setRank(99);
			save(columnResource);
		}
	}
	
	public void delete(Long[] ids) {
		for (Long id : ids) {
			columnResourceDao.delete(id);
		}
	}
}
