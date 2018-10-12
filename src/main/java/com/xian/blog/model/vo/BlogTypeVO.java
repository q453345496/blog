package com.xian.blog.model.vo;

import java.util.List;

public class BlogTypeVO {
	private String name;
	private String code;//编码

	private List<BlogTypeVO> subs;

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

	public List<BlogTypeVO> getSubs() {
		return subs;
	}

	public void setSubs(List<BlogTypeVO> subs) {
		this.subs = subs;
	}

}
