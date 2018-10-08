package com.xian.blog.controller.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xian.blog.common.CommonResult;
import com.xian.blog.model.BlogType;
import com.xian.blog.service.BlogTypeService;

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeManagerController {
	@Resource
	private BlogTypeService blogTypeService;
	
	@RequestMapping(value = "/listGroup", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult listAll() {
		return CommonResult.success(blogTypeService.listGroup());
	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public List<BlogType> tree(@RequestParam(value = "id", required = false, defaultValue = "-1") Long parentId) {
		List<BlogType> list = blogTypeService.list(new EntityWrapper<BlogType>().eq("parent_id", parentId));
		return list;
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
