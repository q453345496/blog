package com.xian.blog.util.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KuaiDaiLiProxyParse extends ProxyParse {
	@Override
	public List<ProxyHost> parse() throws IOException {
		Document htmlDoc = Jsoup.parse(new File("C:/Users/Administrator/Desktop/ip.html"), "utf-8");
		Elements elementsByClass = htmlDoc.select("tbody tr");
		List<ProxyHost> hosts = new ArrayList<>(elementsByClass.size());
		for (Element element : elementsByClass) {
			ProxyHost host = new ProxyHost();
			host.setIp(element.select("[data-title=IP]").text());
			host.setPort(Integer.valueOf(element.select("[data-title=PORT]").text()));
			String type = element.select("[data-title=匿名度]").text();
			int anonymousType = getType(type);
			if (filterType(anonymousType)) {
				continue;
			}
			host.setType(anonymousType);
			String protocolText = element.select("[data-title=类型]").text();
			int protocol = getProtocol(protocolText);
			if (filterProtocol(protocol)) {
				continue;
			}
			host.setProtocol(protocol);
			hosts.add(host);
		}
		return hosts;
	}
}
