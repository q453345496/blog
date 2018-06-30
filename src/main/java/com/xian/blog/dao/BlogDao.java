package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.model.Blog;

public interface BlogDao extends BaseMapper<Blog> {

	public List<Blog> list(Page<Blog> page, Map<String, Object> map);

	public Blog detail(Long id);
}
