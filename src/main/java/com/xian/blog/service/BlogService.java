package com.xian.blog.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.BlogDao;
import com.xian.blog.dao.ColumnDao;
import com.xian.blog.model.Blog;
import com.xian.blog.model.Column;

@Service
@Transactional
public class BlogService {
	@Resource
	private BlogDao blogDao;
	@Resource
	private ColumnDao columnDao;
	
	public List<Blog> list(Page<Blog> page, Map<String, Object> map) {
		return blogDao.list(page, map);
	}

	public DataGridResult page(Page<Blog> page, Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		List<Blog> datas = blogDao.list(page, map);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public List<Blog> listLast(int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", Blog.ONLINE);
		map.put("orderByCause", "b.create_time DESC");
		return blogDao.list(new Page<Blog>(1, size, "b.create_time", false), map);
	}

	public List<Blog> listHot(int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", Blog.ONLINE);
		map.put("orderByCause", "b.click DESC");
		return blogDao.list(new Page<Blog>(1, size, "b.click", false), map);
	}
	
	public List<Blog> listRelate(Long id, Long typeId, int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("typeId", typeId);
		map.put("status", Blog.ONLINE);
		return blogDao.listRelate(new Page<Blog>(1, size), map);
	}
	
	public List<Blog> listColumn(String columnCode) {
		Column query = new Column();
		query.setCode(columnCode);
		Column column = columnDao.selectOne(query);
		return listColumn(column.getId());
	}
	
	public List<Blog> listColumn(Long columnId) {
		Map<String, Object> map = new HashMap<>();
		map.put("columnId", columnId);
		map.put("status", Blog.ONLINE);
		return blogDao.listColumn(map);
	}

	public void update(Blog blog) {
		blogDao.updateById(blog);
		LuceneService.updateIndex(blog);
	}

	public void save(Blog blog) {
		blogDao.insert(blog);
	}

	public int delete(Long id) {
		return blogDao.deleteById(id);
	}

	public Blog detail(Long id) {
		return blogDao.detail(id);
	}

	public Long getDraftId() {
		Blog query = new Blog();
		query.setStatus(Blog.DRAFT);
		Blog blog = blogDao.selectOne(query);
		if (blog == null) {
			blog = new Blog();
			blog.setTitle("草稿");
			blog.setStatus(Blog.DRAFT);
			blog.setContent("");
			blogDao.insert(blog);
			return blog.getId();
		} else {
			return blog.getId();
		}
	}

	public void delete(Long[] ids) {
		blogDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	public Blog getLast(Long id){
		return blogDao.getLast(id);
	}
	
	public Blog getNext(Long id){
		return blogDao.getNext(id);
	}

	public void addClick(Long id, int num) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("num", num);
		blogDao.addClick(map);
	}
}
