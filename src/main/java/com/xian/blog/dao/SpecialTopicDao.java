package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.SpecialTopic;

public interface SpecialTopicDao {

	public List<SpecialTopic> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(SpecialTopic specialTopic);

	public int save(SpecialTopic specialTopic);

	public int delete(Long id);

	public SpecialTopic get(Long id);
}
