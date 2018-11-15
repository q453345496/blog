package com.xian.blog.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ProxyServer {
	public static final int TYPE_TRANSPARENT = 0;
	public static final int TYPE_ANONYMOUS = 1;
	public static final int TYPE_HIGH_ANONYMOUS = 2;

	public static final int PROTOCOL_HTTP = 0;
	public static final int PROTOCOL_HTTPS = 1;

	public static final String SCHEME_HTTP = "http";
	public static final String SCHEME_HTTPS = "https";

	public static final int STATE_UNABLE = 0;
	public static final int STATE_ENABLE = 1;

	@TableId
	private Long id;
	private String ip;
	private Integer port;
	private Integer type;
	private Integer protocol;
	private Integer state;
	private Integer failTimes;
	private Integer successTimes;
	private Integer responeTime;
	private Double score;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date modifyTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date lastCheckTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFailTimes() {
		return failTimes;
	}

	public void setFailTimes(Integer failTimes) {
		this.failTimes = failTimes;
	}

	public Integer getSuccessTimes() {
		return successTimes;
	}

	public void setSuccessTimes(Integer successTimes) {
		this.successTimes = successTimes;
	}

	public Integer getResponeTime() {
		return responeTime;
	}

	public void setResponeTime(Integer responeTime) {
		this.responeTime = responeTime;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
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

	public Date getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	@Override
	public String toString() {
		return "ProxyServer [ip=" + ip + ", port=" + port + ", protocol=" + protocol + "]";
	}

}
