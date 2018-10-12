package com.xian.blog.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xian.blog.service.BlogService;
import com.xian.blog.service.BlogTypeService;

public class MyListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		servletContext.setAttribute("lastBlogList", blogService.listLast(6));
		servletContext.setAttribute("hotBlogList", blogService.listHot(6));
		servletContext.setAttribute("typeList", blogTypeService.listForNav());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
