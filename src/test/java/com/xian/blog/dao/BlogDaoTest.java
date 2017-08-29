package com.xian.blog.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xian.blog.model.Blog;

/**
 * Date:2016年6月23日下午10:06:01
 * 
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BlogDaoTest {

	@Resource
	private BlogDao blogDao;

	@Test
	public void testFind() {
		List<Blog> list = blogDao.list(new HashMap<>());
		for (Blog blog : list) {
			System.out.println(blog);
		}
	}

	@Test
	public void testAdd() {
		Blog blog = new Blog();
		blog.setTitle("呵呵");
		blogDao.save(blog);
		System.out.println(blog.getId());
	}

	@Test
	public void testUpdate() {
		Blog blog = new Blog();
		blog.setId(1l);
		blog.setTitle("呵呵1");
		blogDao.update(blog);
	}

	@Test
	public void testDelete() {
		blogDao.delete(123l);
	}

	@Test
	public void testCount() {
		blogDao.getTotal(Collections.emptyMap());
	}
}
