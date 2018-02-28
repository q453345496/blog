package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.dao.SpecialTopicResourceDao;
import com.xian.blog.model.SpecialTopicResource;

@Service
@Transactional
public class SpecialTopicResourceService {

	@Resource
	private SpecialTopicResourceDao specialTopicResourceDao;

	public List<SpecialTopicResource> list(Map<String, Object> map) {
		return specialTopicResourceDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return specialTopicResourceDao.getTotal(map);
	}

	public int update(SpecialTopicResource specialTopicResource) {
		specialTopicResource.setModifyTime(new Date());
		return specialTopicResourceDao.update(specialTopicResource);
	}

	public int save(SpecialTopicResource specialTopicResource) {
		Date now = new Date();
		specialTopicResource.setCreateTime(new Date());
		specialTopicResource.setModifyTime(now);
		return specialTopicResourceDao.save(specialTopicResource);
	}

	public void save(Long topicId, Long[] ids) {
		for (Long blogId : ids) {
			SpecialTopicResource specialTopicResource = new SpecialTopicResource();
			specialTopicResource.setTopicId(topicId);
			specialTopicResource.setBlogId(blogId);
			save(specialTopicResource);
		}

	}

	public void delete(Long[] ids) {
		for (Long id : ids) {
			specialTopicResourceDao.delete(id);
		}
	}

	public SpecialTopicResource get(Long id) {
		return specialTopicResourceDao.get(id);
	}

	public List<SpecialTopicResource> listRelate(Map<String, Object> map) {
		return specialTopicResourceDao.listRelate(map);
	}

	public Integer getRelateTotal(Map<String, Object> map){
		return specialTopicResourceDao.getRelateTotal(map);
	}

	public List<SpecialTopicResource> listUnRelate(Map<String, Object> map) {
		return specialTopicResourceDao.listUnRelate(map);
	}

	public Integer getUnRelateTotal(Map<String, Object> map){
		return specialTopicResourceDao.getUnRelateTotal(map);
	}
}
