package com.xian.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;
import com.xian.blog.util.RegexUtils;

@Controller
public class IndexController {
	@Resource
	private BlogService blogService;

	@RequestMapping(value = { "/index" })
	public String index(Model model) {
		Map<String, Object> map = new HashMap<>();
		List<Blog> list = blogService.list(map);
		for (Blog blog : list) {
			blog.setThumb(RegexUtils.getFirstImg(blog.getContent()));
		}
		model.addAttribute("blogs", list);
		return "index";
	}

	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "admin/main";
	}

}
