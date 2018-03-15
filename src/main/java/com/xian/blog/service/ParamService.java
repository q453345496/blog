package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ParamDao;
import com.xian.blog.model.Param;

@Service
@Transactional
public class ParamService {

	@Resource
	private ParamDao paramDao;

	public List<Param> list(Map<String, Object> map) {
		return paramDao.list(map);
	}
	
	public DataGridResult page(Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		vo.setTotal(getTotal(map));
		vo.setRows(list(map));
		return vo;
	}
	
	public Integer getTotal(Map<String, Object> map) {
		return paramDao.getTotal(map);
	}

	public int update(Param param) {
		param.setModifyTime(new Date());
		return paramDao.update(param);
	}

	public int save(Param param) {
		Date now = new Date();
		param.setCreateTime(now);
		param.setModifyTime(now);
		return paramDao.save(param);
	}

	public int delete(Long id) {
		return paramDao.delete(id);
	}

	public Param get(Long id) {
		return paramDao.get(id);
	}
}
