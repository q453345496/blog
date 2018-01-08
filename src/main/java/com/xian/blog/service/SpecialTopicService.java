package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.dao.SpecialTopicDao;
import com.xian.blog.model.SpecialTopic;

@Service
@Transactional
public class SpecialTopicService {

	@Resource
	private SpecialTopicDao specialTopicDao;

	public List<SpecialTopic> list(Map<String, Object> map) {
		return specialTopicDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return specialTopicDao.getTotal(map);
	}

	public int update(SpecialTopic specialTopic) {
		return specialTopicDao.update(specialTopic);
	}

	public int save(SpecialTopic specialTopic) {
		specialTopic.setCreateTime(new Date());
		return specialTopicDao.save(specialTopic);
	}

	public int delete(Long id) {
		return specialTopicDao.delete(id);
	}

	public SpecialTopic get(Long id) {
		return specialTopicDao.get(id);
	}
}
