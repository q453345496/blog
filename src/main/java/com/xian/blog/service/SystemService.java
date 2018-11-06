package com.xian.blog.service;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xian.blog.constants.Constants;

public class SystemService {

	public static void reload(ServletContext servletContext) {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		ColumnService columnService = (ColumnService) applicationContext.getBean("columnService");
		//最新
		servletContext.setAttribute("lastBlogList", blogService.listLast(Constants.DEFAULT_COUNT));
		//最热
		servletContext.setAttribute("hotBlogList", blogService.listHot(Constants.DEFAULT_COUNT));
		//今日
		servletContext.setAttribute("todayBlogList", blogService.listByColumnCode(Constants.COLUMN_TODAY_RECOMMEND));
		//导航分类
		servletContext.setAttribute("navList", blogTypeService.listForNav());
		//首页分类
		servletContext.setAttribute("catList", columnService.listForCat());

	}

}
