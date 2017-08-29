package com.xian.blog.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Blog {
	private Long id;
	private String title;// 标题
	private String content;// 内容
	private String keyWord;// 关键字
	private Integer rightType;// 权限类型 目前只有自己可见和公开
	private Integer click;
	private Integer reply;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTF-8")
	private Date createTime;
	private Date lastUpdateTime;

	private String summary;// 摘要,内容的前n个字符

	private BlogType blogType; // 博客类型

	private String contentNoTag;// 内容无标签,方便分词

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getRightType() {
		return rightType;
	}

	public void setRightType(Integer rightType) {
		this.rightType = rightType;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getReply() {
		return reply;
	}

	public void setReply(Integer reply) {
		this.reply = reply;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}

}
