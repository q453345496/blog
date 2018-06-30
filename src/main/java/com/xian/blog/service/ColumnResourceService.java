package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ColumnResourceDao;
import com.xian.blog.model.ColumnResource;

@Service
@Transactional
public class ColumnResourceService {

	@Resource
	private ColumnResourceDao columnResourceDao;

	public int update(ColumnResource columnResource) {
		return columnResourceDao.updateById(columnResource);
	}

	public int save(ColumnResource columnResource) {
		return columnResourceDao.insert(columnResource);
	}

	public int delete(Long id) {
		return columnResourceDao.deleteById(id);
	}

	public ColumnResource get(Long id) {
		return columnResourceDao.selectById(id);
	}

	public DataGridResult pageRelate(Page<ColumnResource> page, Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		List<ColumnResource> datas = columnResourceDao.listRelate(page, map);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public DataGridResult pageUnRelate(Page<ColumnResource> page, Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		List<ColumnResource> datas = columnResourceDao.listUnRelate(page, map);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
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
		columnResourceDao.deleteBatchIds(Arrays.asList(ids));
	}
}
