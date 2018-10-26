package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.constants.Constants;
import com.xian.blog.dao.SpecialTopicResourceDao;
import com.xian.blog.model.SpecialTopicResource;

@Service
@Transactional
public class SpecialTopicResourceService {

	@Resource
	private SpecialTopicResourceDao specialTopicResourceDao;

	public List<SpecialTopicResource> list(Wrapper<SpecialTopicResource> wrapper) {
		return specialTopicResourceDao.selectList(wrapper);
	}

	public int update(SpecialTopicResource specialTopicResource) {
		return specialTopicResourceDao.updateById(specialTopicResource);
	}

	public int save(SpecialTopicResource specialTopicResource) {
		return specialTopicResourceDao.insert(specialTopicResource);
	}

	public void save(Long topicId, Long[] ids) {
		for (Long blogId : ids) {
			SpecialTopicResource specialTopicResource = new SpecialTopicResource();
			specialTopicResource.setTopicId(topicId);
			specialTopicResource.setBlogId(blogId);
			specialTopicResource.setRank(Constants.DEFAULT_RANK);
			save(specialTopicResource);
		}

	}

	public void delete(Long[] ids) {
		specialTopicResourceDao.deleteBatchIds(Arrays.asList(ids));
	}

	public SpecialTopicResource get(Long id) {
		return specialTopicResourceDao.selectById(id);
	}

	public DataGridResult pageRelate(Page<SpecialTopicResource> page, Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		List<SpecialTopicResource> datas = specialTopicResourceDao.listRelate(page, map);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public DataGridResult pageUnRelate(Page<SpecialTopicResource> page, Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		List<SpecialTopicResource> datas = specialTopicResourceDao.listUnRelate(page, map);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}
}
