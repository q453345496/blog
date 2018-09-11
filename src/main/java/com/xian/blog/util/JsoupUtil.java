package com.xian.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtil {

	public static String getFirstImgURL(Document htmlDoc) {
		Elements imgs = htmlDoc.getElementsByTag("img");
		if (imgs.isEmpty()) {
			return "";
		}
		return imgs.get(0).attr("src");
	}

	public static String getFirstImgURL(String html) {
		Document htmlDoc = Jsoup.parse(html);
		return getFirstImgURL(htmlDoc);
	}

	public static void main(String[] args) {

	}
}
