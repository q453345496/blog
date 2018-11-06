package com.xian.blog.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.xian.blog.model.BlogType;
import com.xian.blog.service.BlogTypeService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BlogTypeDaoTest {

	@Resource
	private BlogTypeDao blogTypeDao;
	@Resource
	private BlogTypeService blogTypeService;

//	@Test
	public void batchAdd() {
		for (int i = 0; i < 20; i++) {
			BlogType blogType = new BlogType();
			blogType.setName("呵呵" + i);
			blogType.setRank(i);
			blogTypeDao.insert(blogType);
		}
	}
	
	@Test
	public void batch() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());;
	}

}
