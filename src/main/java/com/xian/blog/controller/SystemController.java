package com.xian.blog.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xian.blog.common.CommonResult;
import com.xian.blog.dao.BlogDao;
import com.xian.blog.model.Blog;
import com.xian.blog.service.LuceneService;
import com.xian.blog.service.SystemService;
import com.xian.blog.util.MarkdownUtil;

@Controller
@RequestMapping("sys")
public class SystemController {
	@Resource
	private BlogDao BlogDao;

	@RequestMapping("/syncIndex")
	@ResponseBody
	public CommonResult syncIndex() {
		List<Blog> selectList = BlogDao.selectList(null);
		LuceneService.cleanAll();
		for (Blog blog : selectList) {
			String html = MarkdownUtil.toHtml(blog.getContent());
			Document htmlDoc = Jsoup.parse(html);
			blog.setContentNoTag(StringEscapeUtils.escapeHtml4(htmlDoc.text()));
			LuceneService.addIndex(blog);
		}
		return CommonResult.success();
	}

	@RequestMapping("/reload")
	@ResponseBody
	public CommonResult reload(HttpServletRequest request) {
		SystemService.reload(request.getServletContext());
		return CommonResult.success();
	}

}
