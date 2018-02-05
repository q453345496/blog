package com.xian.blog.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class MyFilter implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(MyFilter.class);
	private final PathMatcher pathMatcher = new AntPathMatcher();
	private final String resourcesPath = "/resources/**";

	public MyFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (!pathMatcher.match(resourcesPath, req.getRequestURI())) {
			LOG.info(req.getRequestURI());
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
