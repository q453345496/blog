package com.xian.blog.constants;

import org.apache.commons.lang3.StringUtils;

import com.xian.blog.exception.CheckException;

/**
 * Date:2016年7月27日下午10:31:16
 * 
 */
public final class FTPConstant {
	public static final String IMAGE_PATH = "image";
	public static final String VIDEO_PATH = "video";
	public static final String FILE_PATH = "file";

	public static final String BLOG_PATH = "blog";

	public static final String SUCCESS = "SUCCESS";

	public static String getSuffix(String url) {
		String suffix = StringUtils.substringAfterLast(url, ".");
		if (StringUtils.isBlank(suffix)) {
			throw new CheckException("unable get the suffix");
		}
		return suffix;
	}

	public static String getTitle(String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis())//
				.append((Math.random() + "").substring(2, 8))//随机六位
				.append(".").append(suffix);
		return sb.toString();
	}

	public static String getSavepPath(String bizId, String bizType, String type, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(bizType)//
				.append("/").append(bizId)//
				.append("/").append(type)//
				.append("/").append(name);//
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getSuffix(""));
	}

}
