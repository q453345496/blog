package com.xian.blog.model.vo;

import java.util.List;

public class NavVO {
	private String name;
	private String code;//编码

	private List<NavVO> subs;

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

	public List<NavVO> getSubs() {
		return subs;
	}

	public void setSubs(List<NavVO> subs) {
		this.subs = subs;
	}

}
