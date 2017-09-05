package com.xian.blog.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.common.Page;
import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;

/**
 * Date:2016年7月29日下午9:35:11
 * 
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogManagerController {
	private static final Logger LOG = LoggerFactory.getLogger(BlogManagerController.class);
	@Resource
	private BlogService blogService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, Blog blog) {
		DataGridResult vo = new DataGridResult();
		try {
			Page p = new Page(page, rows);
			Map<String, Object> map = new HashMap<>();
			map.put("start", p.getStart());
			map.put("size", p.getPageSize());
			vo.setTotal(blogService.getTotal(map));
			vo.setRows(blogService.list(map));
		} catch (Exception e) {
			LOG.error("BlogTypeController list Error", e);
		}
		return vo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(Blog blog) {
		try {
			if (blog.getId() == null) {
				blogService.save(blog);
			} else {
				blogService.update(blog);
			}
			return CommonResult.success(blog.getId());
		} catch (Exception e) {
			LOG.error("BlogTypeController save Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		try {
			Blog blog = blogService.get(id);
			return CommonResult.success(blog);
		} catch (Exception e) {
			LOG.error("get detail Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value="/toAdd")
	public ModelAndView toAddPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/blog/modifyBlog");
		return mv;
	}

	@RequestMapping(value = "/toEdit/{id}")
	public ModelAndView toEditPage(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/blog/modifyBlog");
		mv.addObject("id", id);
		return mv;
	}
}
