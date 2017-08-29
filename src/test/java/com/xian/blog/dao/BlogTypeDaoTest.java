package com.xian.blog.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xian.blog.model.BlogType;

/**
 * Date:2016年6月23日下午10:06:01
 * 
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BlogTypeDaoTest {

	@Resource
	private BlogTypeDao blogTypeTypeDao;

	@Test
	public void batchAdd() {
		for (int i = 0; i < 50; i++) {
			BlogType blogType = new BlogType();
			blogType.setName("呵呵" + i);
			blogType.setRank(i);
			blogTypeTypeDao.save(blogType);
		}
	}

	@Test
	public void testFind() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "ava");
		List<BlogType> list = blogTypeTypeDao.list(map);
		for (BlogType blogType : list) {
			System.out.println(blogType);
		}
	}

	@Test
	public void testAdd() {
		BlogType blogType = new BlogType();
		blogType.setName("呵呵");
		blogTypeTypeDao.save(blogType);
		System.out.println(blogType.getId());
	}

	@Test
	public void testUpdate() {
		BlogType blogType = new BlogType();
		blogType.setId(1l);
		blogTypeTypeDao.update(blogType);
	}

	@Test
	public void testDelete() {
		blogTypeTypeDao.delete(123l);
	}

	@Test
	public void testCount() {
		blogTypeTypeDao.getTotal(Collections.emptyMap());
	}
}
