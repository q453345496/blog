package com.xian.blog.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 流水账
 * 
 * @author xian
 * @date 2016年7月27日 下午10:09:24
 *
 */
public class Journal {
	private Long id;
	private BigDecimal money;//
	private Integer type;// 类型,收入、支出
	private JournalCategory mainType;// 一级分类
	private JournalCategory subType;// 一级分类
	private Date occurTime;// 产生时间
	private String remark;// 备注
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public JournalCategory getMainType() {
		return mainType;
	}

	public void setMainType(JournalCategory mainType) {
		this.mainType = mainType;
	}

	public JournalCategory getSubType() {
		return subType;
	}

	public void setSubType(JournalCategory subType) {
		this.subType = subType;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
