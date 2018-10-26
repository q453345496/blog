package com.xian.blog.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SpecialTopicResource {
	@TableId
	private Long id;
	private Long topicId;
	private Long blogId;
	private Integer rank;
	
	@TableField(exist = false)
	private String blogTitle;
	@TableField(exist = false)
	private String blogTypeName;
	@TableField(exist = false)
	private Long blogTypeId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
	private Date modifyTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
