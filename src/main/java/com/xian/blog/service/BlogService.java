package com.xian.blog.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.dao.BlogDao;
import com.xian.blog.model.Blog;

@Service
@Transactional
public class BlogService {
	@Resource
	private BlogDao blogDao;

	public List<Blog> list(Map<String, Object> map) {
		return blogDao.list(map);
	}
	
	public List<Blog> listLast(int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", 0);
		map.put("size", size);
		map.put("orderByCause", "b.create_time DESC");
		return blogDao.list(map);
	}
	
	public List<Blog> listHot(int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", 0);
		map.put("size", size);
		map.put("status", Blog.ONLINE);
		map.put("orderByCause", "b.click DESC");
		return blogDao.list(map);
	}
	
	public Integer getTotal(Map<String, Object> map) {
		return blogDao.getTotal(map);
	}

	public void update(Blog blog) {
		blog.setModifyTime(new Date());
		blogDao.update(blog);
	}

	public void save(Blog blog) {
		Date now = new Date();
		blog.setCreateTime(now);
		blog.setModifyTime(now);
		blogDao.save(blog);
	}

	public int delete(Long id) {
		return blogDao.delete(id);
	}

	public Blog get(Long id) {
		return blogDao.get(id);
	}

	public Long getDraftId() {
		Map<String, Object> map = new HashMap<>();
		map.put("status", Blog.DRAFT);
		List<Blog> list = blogDao.list(map);
		if (list.isEmpty()) {
			Date now = new Date();
			Blog blog = new Blog();
			blog.setCreateTime(now);
			blog.setModifyTime(now);
			blog.setTitle("");
			blog.setStatus(Blog.DRAFT);
			blogDao.save(blog);
			return blog.getId();
		} else {
			return list.get(0).getId();
		}
	}
}
