package com.xian.blog.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户
 * 
 * @author xian
 * @date 2016年7月27日 下午10:16:23
 *
 */
public class AccountBook {
	private Long id;
	private String name;
	private BigDecimal inflow;// 流入
	private BigDecimal outflow;// 流出
	private Integer accountType;// 账户类型

	private String userId;// 所属用户的id

	private Date createTime;
	private Date lastUpdateTime;

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

	public BigDecimal getInflow() {
		return inflow;
	}

	public void setInflow(BigDecimal inflow) {
		this.inflow = inflow;
	}

	public BigDecimal getOutflow() {
		return outflow;
	}

	public void setOutflow(BigDecimal outflow) {
		this.outflow = outflow;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}
