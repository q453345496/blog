package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.SpecialTopicDao;
import com.xian.blog.model.SpecialTopic;

@Service
@Transactional
public class SpecialTopicService {

	@Resource
	private SpecialTopicDao specialTopicDao;

	public List<SpecialTopic> list(Wrapper<SpecialTopic> wrapper) {
		return specialTopicDao.selectList(wrapper);
	}
	
	public DataGridResult page(Page<SpecialTopic> page, Wrapper<SpecialTopic> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<SpecialTopic> datas = specialTopicDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}
	
	public int update(SpecialTopic specialTopic) {
		return specialTopicDao.updateById(specialTopic);
	}

	public int save(SpecialTopic specialTopic) {
		return specialTopicDao.insert(specialTopic);
	}

	public int delete(Long[] ids) {
		return specialTopicDao.deleteBatchIds(Arrays.asList(ids));
	}

	public SpecialTopic get(Long id) {
		return specialTopicDao.selectById(id);
	}
}
