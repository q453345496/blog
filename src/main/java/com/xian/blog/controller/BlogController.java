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
import com.xian.blog.constants.Constants;
import com.xian.blog.model.Blog;
import com.xian.blog.model.BlogType;
import com.xian.blog.service.BlogService;
import com.xian.blog.service.BlogTypeService;
import com.xian.blog.service.LuceneService;

@Controller

public class BlogController {
	@Resource
	private BlogService blogService;
	@Resource
	private BlogTypeService blogTypeService;

	@RequestMapping("/b/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("index");
		Blog blog = blogService.detail(id);
		if (blog == null) {
			modelAndView.addObject("mainPage", Constants.PAGE_404);
		} else {
			modelAndView.addObject("blog", blog);
			modelAndView.addObject("lastBlog", blogService.getLast(id));
			modelAndView.addObject("nextBlog", blogService.getNext(id));
			modelAndView.addObject("relateBlogList", blogService.listRelate(id, blog.getTypeId(), Constants.DEFAULT_COUNT));
			modelAndView.addObject("mainPage", "view/blog/blog.jsp");
			blogService.addClick(id, 1);
		}
		return modelAndView;
	}

	@RequestMapping("/t/{typeCode:[a-z]+}")
	public ModelAndView listByType(@PathVariable("typeCode") String typeCode,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
		ModelAndView view = new ModelAndView("index");
		BlogType blogType = blogTypeService.getByCode(typeCode);
		if (blogType == null) {
			view.addObject("mainPage", Constants.PAGE_404);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("typeId", blogType.getId());
			map.put("status", Blog.ONLINE);
			Page<Blog> pageInfo = new Page<Blog>(page, Constants.DEFAULT_PAGE_SIZE);
			List<Blog> list = blogService.list(pageInfo, map);
			if (list.isEmpty()) {
				view.addObject("mainPage", Constants.PAGE_404);
			} else {
				view.addObject("blogs", list);
				view.addObject("mainPage", "view/blog/page.jsp");
				view.addObject("page", pageInfo);
			}
		}
		return view;
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("kw") String kw,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
		ModelAndView view = new ModelAndView("index");
		Page<Blog> pageInfo = new Page<Blog>(page, Constants.DEFAULT_PAGE_SIZE);
		LuceneService.search(kw, pageInfo, true);
		view.addObject("blogs", pageInfo.getRecords());
		view.addObject("mainPage", "view/blog/search.jsp");
		view.addObject("page", pageInfo);
		view.addObject("kw", kw);
		return view;
	}

}
