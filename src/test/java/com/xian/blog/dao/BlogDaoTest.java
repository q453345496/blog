package com.xian.blog.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	public void test() {
		//		Map<String, Object> map = new HashMap<>();
		//		map.put("title", blog.getTitle());
		//		map.put("typeId", blog.getTypeId());
	}
}
