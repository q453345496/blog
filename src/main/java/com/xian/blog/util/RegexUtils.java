package com.xian.blog.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class RegexUtils {

	private static final String SRC_REGEX = "src=\"(.*?)\"";
	private static final String IMG_URL_REGEX = "<img.*?src=\"(.*?)\"[^>]*?>";
	private static final String IMG_REGEX = "<img[^>]*?>";

	private static final Pattern SRC_PATTERN = Pattern.compile(SRC_REGEX);
	private static final Pattern IMG_URL_PATTERN = Pattern.compile(IMG_URL_REGEX);
	private static final Pattern IMG_PATTERN = Pattern.compile(IMG_REGEX);

	private static final String TAG_REGEX = "<!--.*-->|<[^>]*>";
	private static final Pattern TAG_PATTERN = Pattern.compile(TAG_REGEX);

	public static String getFirstImgURL(String text) {
		if (StringUtils.isNotBlank(text)) {
			Matcher matcher = IMG_URL_PATTERN.matcher(text);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	public static List<String> getAllImgURL(String text) {
		if (StringUtils.isNotBlank(text)) {
			List<String> imgs = new ArrayList<>();
			Matcher matcher = IMG_URL_PATTERN.matcher(text);
			while (matcher.find()) {
				imgs.add(matcher.group(1));
			}
			return imgs;
		}
		return Collections.emptyList();
	}

	public static List<String> getAllImg(String text) {
		if (StringUtils.isNotBlank(text)) {
			Matcher matcher = IMG_PATTERN.matcher(text);
			List<String> imgs = new ArrayList<>();
			while (matcher.find()) {
				imgs.add(matcher.group());
			}
			return imgs;
		}
		return Collections.emptyList();
	}

	public static List<String> getAllImgFromMarkdown(String text) {
		if (StringUtils.isNotBlank(text)) {
			List<String> imgs = new ArrayList<>();
			Matcher matcher = Pattern.compile("\\!\\[.*]\\((.+?)(\\s+\".*?\")?\\)").matcher(text);
			while (matcher.find()) {
				imgs.add(matcher.group(1));
			}
			return imgs;
		}
		return Collections.emptyList();
	}

	public static String getSrc(String text) {
		if (StringUtils.isNotBlank(text)) {
			Matcher matcher = SRC_PATTERN.matcher(text);
			while (matcher.find()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	public static String getNoTagContent(String content) {
		if (StringUtils.isBlank(content)) {
			return content;
		}
		Matcher matcher = TAG_PATTERN.matcher(content);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sb.append(matcher.replaceAll("").replaceAll("\\s", "").replaceAll("&.*?;", ""));
		}
		return sb.toString();
	}

	public static String getNoTagMarkdownContent(String content) {
		if (StringUtils.isBlank(content)) {
			return content;
		}
		return content.replaceAll("#|\\*", "")//标题、粗体
				.replaceAll("\r|\n|\t|\\s{2,}", "")//代码缩进留空格
				.replaceAll("\\|.*\\|", "")//表格内容,虽然是贪婪匹配,但是对于摘要来说一般不影响
				.replaceAll("\\[TOC\\]", "");//TOC
	}

	public static void main(String[] args) {

		try {
			String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\name.html"));
			System.out.println(getNoTagContent(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
