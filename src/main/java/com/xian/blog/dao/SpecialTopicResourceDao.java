package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.SpecialTopicResource;

public interface SpecialTopicResourceDao {

	public List<SpecialTopicResource> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(SpecialTopicResource specialTopicResource);

	public int save(SpecialTopicResource specialTopicResource);

	public int delete(Long id);

	public SpecialTopicResource get(Long id);

	public List<SpecialTopicResource> listRelate(Map<String, Object> map);

	public Integer getRelateTotal(Map<String, Object> map);

	public List<SpecialTopicResource> listUnRelate(Map<String, Object> map);

	public Integer getUnRelateTotal(Map<String, Object> map);
}
