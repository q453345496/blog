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
import com.xian.blog.model.Param;
import com.xian.blog.service.ParamService;

@Controller
@RequestMapping("/admin/param")
public class ParamManagerController {
	@Resource
	private ParamService paramService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows, Param param) {
		return paramService.page(new Page<Param>(page, rows), //
				new EntityWrapper<Param>()//
						.eq(StringUtils.isNotBlank(param.getTypeCode()), "type_code", param.getTypeCode())//
						.like("`key`", param.getKey()));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(Param param) {
		paramService.save(param);
		return CommonResult.success(param.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(Param param) {
		paramService.update(param);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		Param param = paramService.get(id);
		return CommonResult.success(param);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		paramService.delete(id);
		return CommonResult.success();
	}
}
