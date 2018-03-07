package com.xian.blog.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class RegexUtils {

	private static final String IMG_URL_REGEX = "<img.*?src=\"(.*?)\"[^>]*?>";

	private static final Pattern IMG_PATTERN = Pattern.compile(IMG_URL_REGEX);

	private static final String TAG_REGEX = "<!--.*-->|<[^>]*>";
	private static final Pattern TAG_PATTERN = Pattern.compile(TAG_REGEX);

	public static String getFirstImg(String text) {
		Matcher matcher = IMG_PATTERN.matcher(text);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	public static List<String> getAllImg(String text) {
		Matcher matcher = IMG_PATTERN.matcher(text);
		List<String> imgs = new ArrayList<>();
		while (matcher.find()) {
			imgs.add(matcher.group(1));
		}
		return imgs;
	}

	public static String getNoTagContent(String content) {
		if (content == null || content.length() == 0) {
			return content;
		}
		Matcher matcher = TAG_PATTERN.matcher(content);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sb.append(matcher.replaceAll("").replaceAll("\\s", "").replaceAll("&.*?;", ""));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\test.html"));
			System.out.println("==========");
			System.out.println(getNoTagContent(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
