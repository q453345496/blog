package com.xian.blog.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.common.Page;
import com.xian.blog.model.ParamType;
import com.xian.blog.service.ParamTypeService;

@Controller
@RequestMapping("/admin/paramType")
public class ParamTypeManagerController {
	@Resource
	private ParamTypeService paramTypeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, ParamType paramType) {
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("name", paramType.getName());
		map.put("code", paramType.getCode());
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		return paramTypeService.page(map);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(ParamType paramType) {
		paramTypeService.save(paramType);
		return CommonResult.success(paramType.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult update(ParamType paramType) {
		paramTypeService.update(paramType);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		ParamType paramType = paramTypeService.get(id);
		return CommonResult.success(paramType);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		paramTypeService.delete(id);
		return CommonResult.success();
	}
}
