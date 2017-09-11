package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.ParamType;

public interface ParamTypeDao {

	public List<ParamType> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(ParamType paramType);

	public int save(ParamType paramType);

	public int delete(Long id);

	public ParamType get(Long id);
	
	public ParamType getByCode(String code);
}
