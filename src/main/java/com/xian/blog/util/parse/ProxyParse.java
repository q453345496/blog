package com.xian.blog.util.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xian.blog.model.ProxyServer;

public abstract class ProxyParse {
	private static List<ProxyParse> parses = new ArrayList<ProxyParse>();
	static {
		parses.add(new KuaiDaiLiProxyParse());
	}

	public abstract List<ProxyHost> parse() throws IOException;

	protected int getType(String type) {
		if (type.contains("高") || type.contains("high")) {
			return ProxyServer.TYPE_HIGH_ANONYMOUS;
		} else if (type.contains("透明") || type.contains("transparent")) {
			return ProxyServer.TYPE_TRANSPARENT;
		} else {
			return ProxyServer.TYPE_ANONYMOUS;
		}
	}

	protected int getProtocol(String protocol) {
		if ("https".equalsIgnoreCase(protocol)) {
			return ProxyServer.PROTOCOL_HTTPS;
		} else if ("http".equalsIgnoreCase(protocol)) {
			return ProxyServer.PROTOCOL_HTTP;
		} else {
			return ProxyServer.PROTOCOL_OHTER;
		}
	}

	protected boolean filterType(int type) {
		return ProxyServer.TYPE_TRANSPARENT == type;
	}

	protected boolean filterProtocol(int protocol) {
		return ProxyServer.PROTOCOL_OHTER == protocol;
	}

	public static List<ProxyParse> getParses() {
		return parses;
	}

	public static class ProxyHost {
		public String ip;
		public int port;
		public int type;
		public int protocol;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getProtocol() {
			return protocol;
		}

		public void setProtocol(int protocol) {
			this.protocol = protocol;
		}

	}
}
