package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.Param;

public interface ParamDao {

	public List<Param> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(Param param);

	public int save(Param param);

	public int delete(Long id);

	public Param get(Long id);
}
