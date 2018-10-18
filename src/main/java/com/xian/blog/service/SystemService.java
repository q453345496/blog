package com.xian.blog.service;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SystemService {

	public static void reload(ServletContext servletContext) {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		//最新
		servletContext.setAttribute("lastBlogList", blogService.listLast(6));
		//最热
		servletContext.setAttribute("hotBlogList", blogService.listHot(6));
		//导航分类
		servletContext.setAttribute("typeList", blogTypeService.listForNav());
	}

}
