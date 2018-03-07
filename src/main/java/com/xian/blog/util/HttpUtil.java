package com.xian.blog.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
				if (!isUnkonw(ip)) {
					return getMultistageReverseProxyIp(ip);
				}
			}
			ip = request.getRemoteAddr();
		} catch (Exception e) {
			LOG.error("getClientIP error ", e);
		}
		return ip;
	}

	public static boolean isUnkonw(String ip) {
		return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
	}

	public static String getMultistageReverseProxyIp(String ip) {
		// 多级反向代理检测
		if (ip != null && ip.indexOf(",") > 0) {
			final String[] ips = ip.trim().split(",");
			for (String subIp : ips) {
				if (!isUnkonw(subIp)) {
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
}
