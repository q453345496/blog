package com.xian.blog.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xian.blog.exception.HttpException;
import com.xian.blog.model.ProxyServer;

public class HttpUtil {

	private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

	private HttpUtil() {
	}

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-
	 * For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getClientIP(HttpServletRequest request) {
		final String[] headers = { "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP",
				"HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
		String ip = null;
		try {
			for (String header : headers) {
				ip = request.getHeader(header);
				if (!isUnkonwIp(ip)) {
					return getMultistageReverseProxyIp(ip);
				}
			}
			ip = request.getRemoteAddr();
		} catch (Exception e) {
			LOG.error("getClientIP error ", e);
		}
		return ip;
	}

	public static boolean isUnkonwIp(String ip) {
		return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
	}

	public static String getMultistageReverseProxyIp(String ip) {
		// 多级反向代理检测
		if (ip != null && ip.indexOf(",") > 0) {
			final String[] ips = ip.trim().split(",");
			for (String subIp : ips) {
				if (!isUnkonwIp(subIp)) {
					return subIp;
				}
			}
		}
		return ip;
	}

	public static String urlEncode(String content, Charset charset) {
		return urlEncode(content, charset.name());
	}

	public static String urlEncode(String content, String charsetStr) {
		if (StringUtils.isBlank(content)) {
			return content;
		}

		String encodeContent = null;
		try {
			encodeContent = URLEncoder.encode(content, charsetStr);
		} catch (UnsupportedEncodingException e) {
			throw new HttpException("urlEncode error");
		}
		return encodeContent;
	}

	public static String urlDecode(String content, Charset charset) {
		return urlDecode(content, charset.name());
	}

	public static String urlDecode(String content, String charsetStr) {
		if (StringUtils.isBlank(content)) {
			return content;
		}
		String encodeContnt = null;
		try {
			encodeContnt = URLDecoder.decode(content, charsetStr);
		} catch (UnsupportedEncodingException e) {
			throw new HttpException("urlEncode error");
		}
		return encodeContnt;
	}

	public static boolean isAjax(HttpServletRequest request) {
		String xmlHttpRequest = request.getHeader("X-Requested-With");
		if (xmlHttpRequest != null && xmlHttpRequest.indexOf("XMLHttpRequest") > -1) {
			return true;
		}
		return false;
	}

	public static boolean isUrl(String url) {
		try {
			new java.net.URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	public static boolean isHttps(String url) {
		return url.toLowerCase().startsWith("https");
	}

	public static boolean inHostList(String urlStr, String... hosts) {
		URL url;
		try {
			url = new URL(urlStr);
		} catch (Exception e) {
			throw new HttpException("an unknown protocol is found, or is null");
		}
		String host = url.getHost();
		for (String tmp : hosts) {
			if (StringUtils.equals(host, tmp)) {
				return true;
			}
		}
		return false;
	}

	private static final String[] USER_AGENTS = new String[] {
			"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0", //
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)", //
			"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko", //
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)", //
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)", //
			"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)", //
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)", //
			"Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1", //
			"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36", //
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",//
	};

	private static String randomAgent(String ip) {
		int mod = ip.hashCode() % 10;
		return USER_AGENTS[mod < 0 ? -mod : mod];
	}

	public static CloseableHttpClient getHttpClient(ProxyServer proxyServer) {
		HttpClientBuilder builder = HttpClients.custom();
		if (proxyServer != null) {
			builder.setUserAgent(randomAgent(proxyServer.getIp()));
		} else {
			builder.setUserAgent(USER_AGENTS[0]);
		}
		return builder.build();
	}

	public static RequestConfig getRequestConfig(ProxyServer proxyServer) {
		Builder builder = RequestConfig.custom()//
				.setConnectTimeout(30 * 1000)//
				.setSocketTimeout(30 * 1000)//
				.setConnectionRequestTimeout(30 * 1000);
		if (proxyServer != null) {
			builder.setProxy(new HttpHost(proxyServer.getIp(), proxyServer.getPort(),
					proxyServer.getProtocol().equals(ProxyServer.PROTOCOL_HTTPS) //
							? ProxyServer.SCHEME_HTTPS : ProxyServer.SCHEME_HTTP));
		}
		return builder.build();//

	}

	public static InputStream get(String url) throws IOException {
		return get(url, null);
	}

	public static InputStream get(String url, ProxyServer proxyServer) throws IOException {
		try (CloseableHttpClient httpclient = getHttpClient(proxyServer)) {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(getRequestConfig(proxyServer));
			CloseableHttpResponse res = httpclient.execute(httpGet);
			byte[] byteArray = EntityUtils.toByteArray(res.getEntity());
			return new ByteArrayInputStream(byteArray);
		}
	}

}
