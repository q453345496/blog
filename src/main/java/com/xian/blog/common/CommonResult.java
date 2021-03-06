package com.xian.blog.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public final class CommonResult implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;
	private static final CommonResult EMPTY = new CommonResult();
	
	private int status = SUCCESS;
	private String msg = "success";
	private Object data;

	public CommonResult() {
	}

	public CommonResult(Object data) {
		this.status = SUCCESS;
		this.data = data;
	}

	public CommonResult(Throwable e) {
		this.status = FAIL;
		this.msg = e.getMessage();
	}

	public CommonResult(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static CommonResult fail(Throwable e) {
		return new CommonResult(e);
	}

	public static CommonResult success(Object data) {
		return new CommonResult(data);
	}

	public static CommonResult success() {
		return EMPTY;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		map.put("msg", msg);
		map.put("data", data);
		return map;
	}
}
