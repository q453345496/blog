package com.xian.blog.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ParamDao;
import com.xian.blog.model.Param;

@Service
@Transactional
public class ParamService {

	@Resource
	private ParamDao paramDao;

	public List<Param> list(Wrapper<Param> wrapper) {
		return paramDao.selectList(wrapper);
	}
	
	public DataGridResult page(Page<Param> page, Wrapper<Param> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<Param> datas = paramDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}
	

	public int update(Param param) {
		return paramDao.updateById(param);
	}

	public int save(Param param) {
		return paramDao.insert(param);
	}

	public int delete(Long id) {
		return paramDao.deleteById(id);
	}

	public Param get(Long id) {
		return paramDao.selectById(id);
	}
}
