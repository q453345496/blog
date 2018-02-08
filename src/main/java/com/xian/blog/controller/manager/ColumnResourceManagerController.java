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
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.ColumnResource;
import com.xian.blog.service.ColumnResourceService;

@Controller
@RequestMapping("/admin/columnResource")
public class ColumnResourceManagerController {
	@Resource
	private ColumnResourceService columnResourceService;

	@RequestMapping(value = "/listRelate", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "columnId", required = true) Long columnId) {
		if (columnId == null) {
			throw new CheckException("参数不能为空");
		}
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		map.put("columnId", columnId);
		
		DataGridResult vo = new DataGridResult();
		vo.setTotal(columnResourceService.getRelateTotal(map));
		vo.setRows(columnResourceService.listRelate(map));
		return vo;
	}
	
	@RequestMapping(value = "/listUnRelate", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult listUnRelate(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "columnId", required = true) Long columnId) {
		if (columnId == null) {
			throw new CheckException("参数不能为空");
		}
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		map.put("columnId", columnId);
		
		DataGridResult vo = new DataGridResult();
		vo.setTotal(columnResourceService.getUnRelateTotal(map));
		vo.setRows(columnResourceService.listUnRelate(map));
		return vo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(Long columnId, Long[] ids) {
		if (columnId == null || ids == null || ids.length == 0) {
			throw new CheckException("参数不能为空");
		}
		columnResourceService.save(columnId, ids);
		return CommonResult.success();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(ColumnResource columnResource) {
		columnResourceService.update(columnResource);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		ColumnResource columnResource = columnResourceService.get(id);
		return CommonResult.success(columnResource);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(Long[] ids) {
		columnResourceService.delete(ids);
		return CommonResult.success();
	}
}
