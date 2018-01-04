package com.xian.blog.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xian.blog.service.BlogService;

public class MyListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		WebApplicationContext requiredWebApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		BlogService blogService = (BlogService) requiredWebApplicationContext.getBean("blogService");
		System.err.println("呵呵");
		System.err.println(blogService);
		servletContext.setAttribute("lastBlogList", blogService.listLast(6));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
