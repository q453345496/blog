package com.xian.blog.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
	private static final Logger LOG = LoggerFactory.getLogger(BlogTypeController.class);
	@Resource
	private BlogTypeService blogTypeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, BlogType blogType) {
		DataGridResult vo = new DataGridResult();
		try {
			Page p = new Page(page, rows);
			Map<String, Object> map = new HashMap<>();
			map.put("name", blogType.getName());
			map.put("start", p.getStart());
			map.put("size", p.getPageSize());
			vo.setTotal(blogTypeService.getTotal(map));
			vo.setRows(blogTypeService.list(map));
		} catch (Exception e) {
			LOG.error("BlogTypeController list Error", e);
		}
		return vo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(BlogType blogType) {
		try {
			blogTypeService.save(blogType);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("BlogTypeController save Error", e);
			return CommonResult.bulid(500, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(BlogType blogType) {
		try {
			blogTypeService.update(blogType);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("BlogTypeController update Error", e);
			return CommonResult.bulid(500, e.getMessage(), null);
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public CommonResult delete(BlogType blogType) {
		try {
			blogTypeService.delete(blogType.getId());
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("BlogTypeController delete Error", e);
			return CommonResult.bulid(500, e.getMessage(), null);
		}
	}
}
