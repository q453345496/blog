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

import com.xian.blog.model.JournalCategory;

/**
 * Date:2016年6月23日下午10:06:01
 * 
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JournalCategoryDaoTest {

	@Resource
	private JournalCategoryDao categoryDao;

	@Test
	public void batchAdd() {
		for (int i = 0; i < 50; i++) {
			JournalCategory journalCategory = new JournalCategory();
			journalCategory.setName("呵呵" + i);
			journalCategory.setRank(i);
			journalCategory.setParentId(0l);
			categoryDao.save(journalCategory);
		}
	}

	@Test
	public void testFind() {
		Map<String, Object> map = new HashMap<>();
		// map.put("parentId", 0);
		map.put("name", 1);
		// map.put("rank", 2);
		List<JournalCategory> list = categoryDao.list(map);
		for (JournalCategory journalCategory : list) {
			System.out.println(journalCategory.getName());
		}
	}

	@Test
	public void testAdd() {
		JournalCategory journalCategory = new JournalCategory();
		journalCategory.setName("呵呵");
		journalCategory.setRank(99);
		journalCategory.setParentId(0l);
		categoryDao.save(journalCategory);
		System.out.println(journalCategory.getId());
	}

	@Test
	public void testUpdate() {
		JournalCategory journalCategory = new JournalCategory();
		journalCategory.setId(1l);
		journalCategory.setName("改了啊");
		journalCategory.setParentId(99l);
		categoryDao.update(journalCategory);
	}

	@Test
	public void testDelete() {
		categoryDao.delete(50l);
	}

	@Test
	public void testCount() {
		categoryDao.getTotal(Collections.emptyMap());
	}
}
