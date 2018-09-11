package com.xian.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;

@Controller

public class BlogController {
	@Resource
	private BlogService blogService;

	@RequestMapping("/blog/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("index");
		Blog blog = blogService.detail(id);
		modelAndView.addObject("blog", blog);
		modelAndView.addObject("mainPage", "view/blog/blog.jsp");
		return modelAndView;
	}

	@RequestMapping("/{typeId:\\d+}")
	public ModelAndView listByType(@PathVariable("typeId") Long typeId,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
		Map<String, Object> map = new HashMap<>();
		map.put("typeId", typeId);
		map.put("status", Blog.ONLINE);
		Page<Blog> pageInfo = new Page<Blog>(page, 10);
		List<Blog> list = blogService.list(pageInfo, map);
		ModelAndView view = new ModelAndView("index");
		view.addObject("blogs", list);
		view.addObject("mainPage", "view/blog/list.jsp");
		return view;
	}

}
