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
import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;
import com.xian.blog.util.HtmlTagUtils;
import com.xian.blog.util.RegexUtils;

@Controller
public class IndexController {
	@Resource
	private BlogService blogService;

	@RequestMapping(value = { "/index" })
	public ModelAndView index(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", Blog.ONLINE);
		Page<Blog> pageInfo = new Page<Blog>(page, 10);
		List<Blog> list = blogService.list(pageInfo, map);
		long total = pageInfo.getTotal();
		for (Blog blog : list) {
			blog.setThumb(RegexUtils.getFirstImgURL(blog.getContent()));
		}
		ModelAndView view = new ModelAndView("index");
		view.addObject("blogs", list);
		view.addObject("mainPage", "view/blog/list.jsp");
		view.addObject("pageCode", HtmlTagUtils.createPage(page, 10, total, null));
		return view;
	}

	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "admin/main";
	}

}
