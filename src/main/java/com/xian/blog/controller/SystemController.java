package com.xian.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xian.blog.common.CommonResult;
import com.xian.blog.dao.BlogDao;
import com.xian.blog.model.Blog;
import com.xian.blog.service.LuceneService;

@Controller
@RequestMapping("sys")
public class SystemController {
	@Resource
	private BlogDao BlogDao;

	@RequestMapping("/syncIndex")
	@ResponseBody
	public CommonResult syncIndex() {
		List<Blog> selectList = BlogDao.selectList(null);
		for (Blog blog : selectList) {
			LuceneService.addIndex(blog);
		}
		return CommonResult.success();
	}

	@RequestMapping("/search")
	@ResponseBody
	public List<Blog> syncIndex(@RequestParam("q") String q) {

		return LuceneService.search(q);
	}
}
