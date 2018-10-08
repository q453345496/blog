package com.xian.blog.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xian.blog.model.BlogType;

public interface BlogTypeDao extends BaseMapper<BlogType> {
	public List<BlogType> listGroup();
}
