package com.xian.blog.model;

/**
 * 类别
 * Date:2016年8月18日下午9:29:01
 * 
 */
public class JournalCategory {
	private Long id;
	private String name;
	private Integer rank;
	private Long parentId;
	private Integer type;

	/**
	 * easyui的Tree显示使用,只有两级分类
	 */
	public String getState() {
		return 0 == parentId ? "closed" : "open";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
