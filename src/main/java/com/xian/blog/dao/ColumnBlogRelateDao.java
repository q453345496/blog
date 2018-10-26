package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.model.ColumnBlogRelate;
import com.xian.blog.model.ColumnResource;

public interface ColumnBlogRelateDao extends BaseMapper<ColumnBlogRelate> {

	List<ColumnResource> listRelate(Page<ColumnBlogRelate> page, Map<String, Object> map);

	List<ColumnResource> listUnRelate(Page<ColumnBlogRelate> page, Map<String, Object> map);

}
