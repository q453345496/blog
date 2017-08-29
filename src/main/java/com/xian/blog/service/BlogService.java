package com.xian.blog.service;

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

	public Integer getTotal(Map<String, Object> map) {
		return blogDao.getTotal(map);
	}

	public int update(Blog blog) {
		return blogDao.update(blog);
	}

	public int save(Blog blog) {
		return blogDao.save(blog);
	}

	public int delete(Long id) {
		return blogDao.delete(id);
	}

	public Blog get(Long id) {
		return blogDao.get(id);
	}
}
