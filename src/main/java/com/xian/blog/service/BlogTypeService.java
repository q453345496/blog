package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.dao.BlogTypeDao;
import com.xian.blog.model.BlogType;

@Service
@Transactional
public class BlogTypeService {
	@Resource
	private BlogTypeDao blogTypeDao;

	public List<BlogType> list(Map<String, Object> map) {
		return blogTypeDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return blogTypeDao.getTotal(map);
	}

	public int update(BlogType blogType) {
		blogType.setLastUpdateTime(new Date());
		return blogTypeDao.update(blogType);
	}

	public int save(BlogType blogType) {
		Date now = new Date();
		blogType.setCreateTime(now);
		blogType.setLastUpdateTime(now);
		return blogTypeDao.save(blogType);
	}

	public int delete(Long id) {
		return blogTypeDao.delete(id);
	}

	public BlogType get(Long id) {
		return blogTypeDao.get(id);
	}
}
