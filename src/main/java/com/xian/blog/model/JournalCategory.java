package com.xian.blog.model;

public class JournalCategory {
	private Long id;
	private String name;
	private Integer rank;
	private Long parentId;
	private Boolean isParent;

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

	public Boolean getIsParent() {
		return Boolean.TRUE.equals(isParent);
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getState() {
		return Boolean.TRUE.equals(isParent) ? "closed" : "open";
	}
}
