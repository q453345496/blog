package com.xian.blog.model.vo;

import java.util.List;

import com.xian.blog.model.Blog;

public class CategoryVo {
	private String name;
	private String code;
	private List<Blog> blogs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

}
