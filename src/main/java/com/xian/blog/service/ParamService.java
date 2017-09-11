package com.xian.blog.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Integer getTotal(Map<String, Object> map) {
		return paramDao.getTotal(map);
	}

	public int update(Param param) {
		return paramDao.update(param);
	}

	public int save(Param param) {
		return paramDao.save(param);
	}

	public int delete(Long id) {
		return paramDao.delete(id);
	}

	public Param get(Long id) {
		return paramDao.get(id);
	}
}
