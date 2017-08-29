package com.xian.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xian.blog.service.BlogService;

@Controller
public class IndexController {
	@Resource
	private BlogService blogService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return "index";
	}

	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
//		return "index";
		return "admin/main";
	}
	
	@RequestMapping("/bootstrap-fileinput-4.4.2/examples/post")
	@ResponseBody
	public Object upload(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("ret", "ok");
		return map;
//		return "admin/main";
	}
	
}
