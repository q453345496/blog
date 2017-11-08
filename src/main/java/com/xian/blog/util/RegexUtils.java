package com.xian.blog.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class RegexUtils {

	public static final String IMG_URL = "<img.*?src=\"(.*?)\"[^>]*?>";

	public static String getFirstImg(String text) {
		Pattern pattern = Pattern.compile(IMG_URL);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	public static List<String> getAllImg(String text) {
		Pattern pattern = Pattern.compile(IMG_URL);
		Matcher matcher = pattern.matcher(text);
		List<String> imgs = new ArrayList<>();
		while (matcher.find()) {
			imgs.add(matcher.group(1));
		}
		return imgs;
	}

	public static void main(String[] args) {
		try {
			String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\test.html"));
			System.out.println(getFirstImg(text));
			System.out.println("==========");
			for (String s : getAllImg(text)) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
