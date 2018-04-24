package com.xian.blog.constants;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xian.blog.exception.CheckException;

/**
 * Date:2016年7月27日下午10:31:16
 * 
 */
public final class FTPConstant {
	public static final String PATH_IMAGE = "image";
	public static final String PATH_VIDEO = "video";
	public static final String PATH_FILE = "file";

	public static final String PATH_BLOG = "blog";
	public static final String PATH_BLOG_TYPE = "blogtype";

	public static final String SUCCESS = "SUCCESS";

	public static String getSuffix(String name) {
		String suffix = StringUtils.substringAfterLast(name, ".");
		if (StringUtils.isBlank(suffix)) {
			throw new CheckException("unable get the suffix");
		}
		return suffix;
	}

	public static String newTitle(MultipartFile file) {
		return newTitle(getSuffix(file.getOriginalFilename()));
	}

	public static String newTitle(String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis())//
				.append(RandomStringUtils.random(6, DIGITS))//随机六位
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

	private static final char[] DIGITS = { //
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', //
			'a', 'b', 'c', 'd', 'e', 'f', 'g', //
			'h', 'i', 'j', 'k', 'l', 'm', 'n', //
			'o', 'p', 'q', 'r', 's', 't', //
			'u', 'v', 'w', 'x', 'y', 'z', //
			'A', 'B', 'C', 'D', 'E', 'F', 'G', //
			'H', 'I', 'J', 'K', 'L', 'M', 'N', //
			'O', 'P', 'Q', 'R', 'S', 'T', //
			'U', 'V', 'W', 'X', 'Y', 'Z' //
	};

	public static void main(String[] args) {
	}

}
