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

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.ColumnBlogRelate;
import com.xian.blog.service.ColumnBlogRelateService;

@Controller
@RequestMapping("/admin/columnBlogRelate")
public class ColumnBlogRelateManagerController {
	@Resource
	private ColumnBlogRelateService columnBlogRelateService;

	@RequestMapping(value = "/listRelate", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "columnId", required = true) Long columnId,
			@RequestParam(value = "blogTitle", required = false) String blogTitle,
			@RequestParam(value = "blogTypeId", required = false) Long blogTypeId) {
		if (columnId == null) {
			throw new CheckException("参数不能为空");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("columnId", columnId);
		map.put("blogTitle", blogTitle);
		map.put("blogTypeId", blogTypeId);
		return columnBlogRelateService.pageRelate(new Page<ColumnBlogRelate>(page, rows), map);
	}

	@RequestMapping(value = "/listUnRelate", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult listUnRelate(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "columnId", required = true) Long columnId,
			@RequestParam(value = "blogTitle", required = false) String blogTitle,
			@RequestParam(value = "blogTypeId", required = false) Long blogTypeId) {
		if (columnId == null) {
			throw new CheckException("参数不能为空");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("columnId", columnId);
		map.put("blogTitle", blogTitle);
		map.put("blogTypeId", blogTypeId);
		return columnBlogRelateService.pageUnRelate(new Page<ColumnBlogRelate>(page, rows), map);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(Long columnId, Long[] ids) {
		if (columnId == null || ids == null || ids.length == 0) {
			throw new CheckException("参数不能为空");
		}
		columnBlogRelateService.save(columnId, ids);
		return CommonResult.success();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(ColumnBlogRelate columnBlogRelate) {
		columnBlogRelateService.update(columnBlogRelate);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		ColumnBlogRelate columnBlogRelate = columnBlogRelateService.get(id);
		return CommonResult.success(columnBlogRelate);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(Long[] ids) {
		columnBlogRelateService.delete(ids);
		return CommonResult.success();
	}
}
