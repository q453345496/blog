package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ParamTypeDao;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.ParamType;

@Service
@Transactional
public class ParamTypeService {

	@Resource
	private ParamTypeDao paramTypeDao;

	public List<ParamType> list(Map<String, Object> map) {
		return paramTypeDao.list(map);
	}
	
	public DataGridResult page(Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		vo.setTotal(getTotal(map));
		vo.setRows(list(map));
		return vo;
	}
	
	public Integer getTotal(Map<String, Object> map) {
		return paramTypeDao.getTotal(map);
	}

	public int update(ParamType paramType) {
		ParamType byCode = paramTypeDao.getByCode(paramType.getCode());
		if (byCode != null && !Objects.equals(paramType.getId(), byCode.getId())) {
			throw new CheckException("code已经存在:" + paramType.getCode());
		}
		paramType.setModifyTime(new Date());
		return paramTypeDao.update(paramType);
	}

	public int save(ParamType paramType) {
		ParamType byCode = paramTypeDao.getByCode(paramType.getCode());
		if (byCode != null) {
			throw new CheckException("code已经存在:" + paramType.getCode());
		}
		Date now = new Date();
		paramType.setCreateTime(now);
		paramType.setModifyTime(now);
		return paramTypeDao.save(paramType);
	}

	public int delete(Long id) {
		return paramTypeDao.delete(id);
	}

	public ParamType get(Long id) {
		return paramTypeDao.get(id);
	}

	public ParamType getByCode(String code) {
		return paramTypeDao.getByCode(code);
	}
}
