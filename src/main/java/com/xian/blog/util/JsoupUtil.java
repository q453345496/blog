package com.xian.blog.util;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
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
		try {
			Document htmlDoc = Jsoup.parse(new File("C:/Users/Administrator/Desktop/aaa.html"), "utf-8");
			Element content = htmlDoc.select("div.show-content-free").first();
			Elements elementsByTag = content.getElementsByTag("img");
			for (Element element : elementsByTag) {
				String src = element.attr("data-original-src");
				if (src.startsWith("//")) {
					element.attr("src", "http:" + src);
				}
			}
			String cleanHtml = Jsoup.clean(content.outerHtml(),
					Whitelist.none()//
							.addTags("h1", "h2", "h3", "h4", "h5", "h6", "p", "img", "pre")//
							.addAttributes("img", "height", "src", "title", "width")//
							.addProtocols("img", "src", "http", "https"));
			//			System.out.println(cleanHtml);
			Document cleanDoc = Jsoup.parseBodyFragment(cleanHtml);
			Elements allElements = cleanDoc.body().children();
			StringBuilder sb = new StringBuilder(cleanHtml.length());
			for (Element element : allElements) {
				String tagName = element.tagName();
				if ("img".equals(tagName)) {
					sb.append("![](").append(element.attr("src")).append(")");
				} else if ("pre".equals(tagName)) {
					sb.append("```\r\n").append(element.text()).append("\r\n```\r\n");
				} else if ("h1".equals(tagName)) {
					sb.append("#").append(element.text());
				} else if ("h2".equals(tagName)) {
					sb.append("##").append(element.text());
				} else if ("h3".equals(tagName)) {
					sb.append("###").append(element.text());
				} else if ("h4".equals(tagName)) {
					sb.append("####").append(element.text());
				} else if ("h5".equals(tagName)) {
					sb.append("#####").append(element.text());
				} else if ("h6".equals(tagName)) {
					sb.append("######").append(element.text());
				} else {
					sb.append(element.text());
				}
				sb.append("\r\n");
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
