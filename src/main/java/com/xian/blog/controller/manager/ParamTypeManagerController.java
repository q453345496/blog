package com.xian.blog.controller.manager;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.model.ParamType;
import com.xian.blog.service.ParamTypeService;

@Controller
@RequestMapping("/admin/paramType")
public class ParamTypeManagerController {
	@Resource
	private ParamTypeService paramTypeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows, ParamType paramType) {
		return paramTypeService.page(new Page<ParamType>(page, rows), //
				new EntityWrapper<ParamType>()//
						.like("name", paramType.getName())//
						.eq(StringUtils.isNotBlank(paramType.getCode()), "code", paramType.getCode()));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(ParamType paramType) {
		paramTypeService.save(paramType);
		return CommonResult.success(paramType.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	public CommonResult delete(Long[] id) {
		paramTypeService.delete(id);
		return CommonResult.success();
	}
}
