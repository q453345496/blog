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

import com.xian.blog.common.Page;
import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;
import com.xian.blog.util.HtmlTagUtils;
import com.xian.blog.util.RegexUtils;

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

	@RequestMapping("/{typeId}")
	public ModelAndView listByType(@PathVariable("typeId") Long typeId,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
		Page p = new Page(page, 10);
		Map<String, Object> map = new HashMap<>();
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		map.put("typeId", typeId);
		List<Blog> list = blogService.list(map);
		Integer total = blogService.getTotal(map);
		for (Blog blog : list) {
			blog.setThumb(RegexUtils.getFirstImg(blog.getContent()));
		}
		ModelAndView view = new ModelAndView("index");
		view.addObject("blogs", list);
		view.addObject("mainPage", "view/blog/list.jsp");
		view.addObject("pageCode", HtmlTagUtils.createPage(p.getPage(), 10, total, null));
		return view;
	}

}
