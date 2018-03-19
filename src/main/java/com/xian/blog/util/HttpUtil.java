package com.xian.blog.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xian.blog.common.ssl.DefaultHostnameVerifier;
import com.xian.blog.common.ssl.DefaultTrustManager;
import com.xian.blog.exception.HttpException;

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

	public static HttpURLConnection getConnection(String urlStr)
			throws IOException, NoSuchAlgorithmException, KeyManagementException {
		if (StringUtils.isBlank(urlStr)) {
			throw new HttpException("url is blank");
		}
		if (!isUrl(urlStr)) {
			throw new HttpException(urlStr + " is not a url!");
		}
		URL url = new URL(urlStr);
		HttpURLConnection connection = null;
		if (isHttps(urlStr)) {
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
			httpsURLConnection.setHostnameVerifier(new DefaultHostnameVerifier());
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new DefaultTrustManager() };
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			httpsURLConnection.setSSLSocketFactory(ssf);
			connection = httpsURLConnection;
		} else {
			connection = (HttpURLConnection) url.openConnection();
		}
		connection.setConnectTimeout(10 * 1000);
		connection.setReadTimeout(10 * 1000);
		connection.setDefaultUseCaches(true);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;MSIE7.0;WindowsNT5.1;360SE)");
		return connection;
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
	
}
