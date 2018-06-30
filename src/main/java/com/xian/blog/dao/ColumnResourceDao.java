package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.model.ColumnResource;

public interface ColumnResourceDao extends BaseMapper<ColumnResource> {

	public List<ColumnResource> listRelate(Page<ColumnResource> page, Map<String, Object> map);

	public List<ColumnResource> listUnRelate(Page<ColumnResource> page, Map<String, Object> map);

}
