package com.xian.blog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;

@Controller

public class BlogController {
	@Resource
	private BlogService blogService;

	@RequestMapping("/blog/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("index");
		Blog blog = blogService.get(id);
		modelAndView.addObject("blog", blog);
		modelAndView.addObject("mainPage", "view/blog/blog.jsp");
		return modelAndView;
	}

}
