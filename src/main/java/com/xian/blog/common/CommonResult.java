package com.xian.blog.common;

/**
 * Date:2016年7月29日下午11:20:19
 * 
 */
public final class CommonResult {
	private int status;
	private String msg;
	private Object data;

	public CommonResult() {
	}

	public CommonResult(Object data) {
		this.status = 200;
		this.msg = "success";
		this.data = data;
	}

	public CommonResult(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static CommonResult bulid(int status, String msg, Object data) {
		return new CommonResult(status, msg, data);
	}

	public static CommonResult success(Object data) {
		return new CommonResult(data);
	}

	public static CommonResult success() {
		return new CommonResult(null);
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

}
