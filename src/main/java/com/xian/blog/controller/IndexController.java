package com.xian.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.constants.Constants;
import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;

@Controller
public class IndexController {
	@Resource
	private BlogService blogService;

	@RequestMapping(value = { "/index" })
	public ModelAndView index(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", Blog.ONLINE);
		Page<Blog> pageInfo = new Page<Blog>(page, Constants.DEFAULT_PAGE_SIZE);
		List<Blog> list = blogService.list(pageInfo, map);
		ModelAndView view = new ModelAndView("index");
		if (list.isEmpty()) {
			view.addObject("mainPage", Constants.PAGE_404);
		} else {
			view.addObject("isIndex", true);
			view.addObject("blogs", list);
			view.addObject("mainPage", "view/blog/list.jsp");
			view.addObject("page", pageInfo);
		}
		return view;
	}

	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "admin/main";
	}

}
