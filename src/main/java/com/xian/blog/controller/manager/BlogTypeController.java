package com.xian.blog.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.common.Page;
import com.xian.blog.model.BlogType;
import com.xian.blog.service.BlogTypeService;

/**
 * Date:2016年7月29日下午9:35:11
 * 
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeController {
	@Resource
	private BlogTypeService blogTypeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, BlogType blogType) {
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("name", blogType.getName());
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		return blogTypeService.page(map);
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult listAll() {
		return CommonResult.success(blogTypeService.list(null));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(BlogType blogType) {
		blogTypeService.save(blogType);
		return CommonResult.success(blogType.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(BlogType blogType) {
		blogTypeService.update(blogType);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		BlogType blogType = blogTypeService.get(id);
		return CommonResult.success(blogType);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		blogTypeService.delete(id);
		return CommonResult.success();
	}
}
