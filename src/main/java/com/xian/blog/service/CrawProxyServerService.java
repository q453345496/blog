package com.xian.blog.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xian.blog.dao.ProxyServerDao;
import com.xian.blog.model.ProxyServer;
import com.xian.blog.util.HttpUtil;
import com.xian.blog.util.parse.ProxyParse;
import com.xian.blog.util.parse.ProxyParse.ProxyHost;

@Service
public class CrawProxyServerService {
	private static final Logger LOG = LoggerFactory.getLogger(CrawProxyServerService.class);
	//	private static final String TEST_HTTP_URL = "http://www.baidu.com";
	//	private static final String TEST_HTTPS_URL = "https://www.baidu.com";
	private static final String TEST_HTTP_URL = "http://httpbin.org/ip";
	private static final String TEST_HTTPS_URL = "https://httpbin.org/ip";

	@Resource
	ProxyServerDao proxyServerDao;
	@Resource
	ProxyServerService proxyServerService;

	public void crawIP() throws IOException {
		List<ProxyParse> parses = ProxyParse.getParses();
		for (ProxyParse proxyParse : parses) {
			List<ProxyHost> hosts = proxyParse.parse();
			for (ProxyHost host : hosts) {
				ProxyServer server = new ProxyServer();
				server.setIp(host.getIp());
				server.setPort(host.getPort());
				server.setType(host.getType());
				server.setProtocol(host.getProtocol());
				server.setLastCheckTime(new Date());
				boolean check = check(server);
				server.setState(check ? ProxyServer.STATE_ENABLE : ProxyServer.STATE_UNABLE);
				proxyServerService.saveNotExistByIp(server);

			}
		}
	}

	public boolean check(ProxyServer proxyServer) {
		try (CloseableHttpClient httpclient = HttpUtil.getHttpClient(proxyServer)) {

			HttpGet httpget = new HttpGet(
					proxyServer.getProtocol().equals(ProxyServer.PROTOCOL_HTTPS) ? TEST_HTTPS_URL : TEST_HTTP_URL);
			httpget.setConfig(HttpUtil.getRequestConfig(proxyServer));
			long currentTimeMillis = System.currentTimeMillis();
			try (CloseableHttpResponse res = httpclient.execute(httpget);) {
				proxyServer.setResponeTime(Long.valueOf(System.currentTimeMillis() - currentTimeMillis).intValue());
				String content = EntityUtils.toString(res.getEntity(), "utf-8");
				if (res.getStatusLine().getStatusCode() == 200 //
						//						&& content.contains("value=\"百度一下\"")//
						&& content.contains("origin")//
				) {
					return true;
				}
			}

		} catch (IOException e) {
			proxyServer.setResponeTime(-1);
			LOG.error("代理ip无效:" //
					+ (proxyServer.getProtocol().equals(ProxyServer.PROTOCOL_HTTPS) ? "https" : "http") //
					+ "://" + proxyServer.getIp() + ":" + proxyServer.getPort()//
					+ "\n" + e.getMessage() //
					+ "\nCaused by:" + e.getCause());
		}
		return false;

	}

}
