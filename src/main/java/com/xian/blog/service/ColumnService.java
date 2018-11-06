package com.xian.blog.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.constants.Constants;
import com.xian.blog.dao.BlogDao;
import com.xian.blog.dao.BlogTypeDao;
import com.xian.blog.dao.ColumnDao;
import com.xian.blog.dao.ColumnResourceDao;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.Blog;
import com.xian.blog.model.BlogType;
import com.xian.blog.model.Column;
import com.xian.blog.model.ColumnResource;
import com.xian.blog.model.vo.CategoryVo;

@Service
@Transactional
public class ColumnService {

	@Resource
	private BlogDao blogDao;
	@Resource
	private BlogTypeDao blogTypeDao;
	@Resource
	private ColumnDao columnDao;
	@Resource
	private ColumnResourceDao columnResourceDao;
	@Resource
	private BlogService blogService;

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

	public Column getByCode(String code) {
		Column column = new Column();
		column.setCode(code);
		return columnDao.selectOne(column);
	}

	public List<CategoryVo> listForCat() {
		List<CategoryVo> datas = new ArrayList<>();
		Column indexColumn = getByCode(Constants.COLUMN_INDEX);
		List<ColumnResource> columnResources = columnResourceDao
				.selectList(new EntityWrapper<ColumnResource>().eq("column_id", indexColumn.getId()).orderBy("rank"));
		Page<Blog> pageInfo = new Page<Blog>(1, Constants.DEFAULT_COUNT);
		for (ColumnResource columnResource : columnResources) {
			Long typeId = columnResource.getTypeId();
			BlogType type = blogTypeDao.selectById(typeId);
			CategoryVo vo = new CategoryVo();
			vo.setName(type.getName());
			vo.setCode(type.getCode());
			vo.setBlogs(blogService.listByType(typeId, pageInfo));
			datas.add(vo);
		}
		return datas;
	}
}
