package com.xian.blog.controller.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
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
	public DataGridResult list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows, BlogType blogType) {
		return blogTypeService.page(new Page<>(page, rows), //
				new EntityWrapper<BlogType>()//
						.like("name", blogType.getName())//
						.like("code", blogType.getCode()));
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult listAll() {
		return CommonResult.success(blogTypeService.list(null));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(BlogType blogType, MultipartFile img) {
		blogTypeService.save(blogType, img);
		return CommonResult.success(blogType.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(BlogType blogType, MultipartFile img) {
		blogTypeService.update(blogType, img);
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
	public CommonResult delete(@RequestParam(value = "id", required = true) Long[] ids) {
		blogTypeService.delete(ids);
		return CommonResult.success();
	}
}
