package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ColumnBlogRelateDao;
import com.xian.blog.model.ColumnBlogRelate;
import com.xian.blog.model.ColumnResource;

@Service
@Transactional
public class ColumnBlogRelateService {

	@Resource
	private ColumnBlogRelateDao columnBlogRelateDao;

	public int update(ColumnBlogRelate columnBlogRelate) {
		return columnBlogRelateDao.updateById(columnBlogRelate);
	}

	public int save(ColumnBlogRelate columnBlogRelate) {
		return columnBlogRelateDao.insert(columnBlogRelate);
	}

	public int delete(Long id) {
		return columnBlogRelateDao.deleteById(id);
	}

	public int delete(Long[] ids) {
		return columnBlogRelateDao.deleteBatchIds(Arrays.asList(ids));
	}

	public ColumnBlogRelate get(Long id) {
		return columnBlogRelateDao.selectById(id);
	}

	public DataGridResult pageRelate(Page<ColumnBlogRelate> page, Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		List<ColumnResource> datas = columnBlogRelateDao.listRelate(page, map);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public DataGridResult pageUnRelate(Page<ColumnBlogRelate> page, Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		List<ColumnResource> datas = columnBlogRelateDao.listUnRelate(page, map);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public void save(Long columnId, Long[] ids) {
		for (Long typeId : ids) {
			ColumnBlogRelate columnBlogResource = new ColumnBlogRelate();
			columnBlogResource.setColumnId(columnId);
			columnBlogResource.setBlogId(typeId);
			columnBlogResource.setRank(99);
			save(columnBlogResource);
		}
	}

}
