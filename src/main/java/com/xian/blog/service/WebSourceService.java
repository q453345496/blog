package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.WebSourceDao;
import com.xian.blog.model.WebSource;

@Service
@Transactional
public class WebSourceService {

	@Resource
	private WebSourceDao webSourceDao;

	public List<WebSource> list(Wrapper<WebSource> wrapper) {
		return webSourceDao.selectList(wrapper);
	}

	public DataGridResult page(Page<WebSource> page, Wrapper<WebSource> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<WebSource> datas = webSourceDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public int update(WebSource webSource) {
		return webSourceDao.updateById(webSource);
	}

	public int save(WebSource webSource) {
		return webSourceDao.insert(webSource);
	}

	public int delete(Long id) {
		return webSourceDao.deleteById(id);
	}

	public int delete(Long[] ids) {
		return webSourceDao.deleteBatchIds(Arrays.asList(ids));
	}

	public WebSource get(Long id) {
		return webSourceDao.selectById(id);
	}
	
}
