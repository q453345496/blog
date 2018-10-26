package com.xian.blog.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ColumnBlogRelate {
	@TableId
	private Long id;
	private Long blogId;
	private Long columnId;
	private Integer rank;

	@TableField(exist = false)
	private String blogTitle;
	@TableField(exist = false)
	private String blogTypeName;
	@TableField(exist = false)
	private Long blogTypeId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBlogTypeName() {
		return blogTypeName;
	}

	public void setBlogTypeName(String blogTypeName) {
		this.blogTypeName = blogTypeName;
	}

	public Long getBlogTypeId() {
		return blogTypeId;
	}

	public void setBlogTypeId(Long blogTypeId) {
		this.blogTypeId = blogTypeId;
	}

}
