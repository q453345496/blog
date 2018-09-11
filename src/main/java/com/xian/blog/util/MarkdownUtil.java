package com.xian.blog.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.options.DataHolder;

public class MarkdownUtil {
	static final DataHolder OPTIONS = PegdownOptionsAdapter.flexmarkOptions(Extensions.ALL);

	private static final Parser PARSER = Parser.builder(OPTIONS).build();
	private static final HtmlRenderer HTML_RENDERER = HtmlRenderer.builder(OPTIONS).build();

	public static String toHtml(String text) {
		if (StringUtils.isBlank(text)) {
			return "";
		}
		return HTML_RENDERER.render(PARSER.parse(text));
	}

	public static void main(String[] args) {

		try {
			String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\name.html"));
			String html = toHtml(text);
			Document htmlDoc = Jsoup.parse(html);
			System.out.println(StringEscapeUtils.escapeHtml4(htmlDoc.text()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
