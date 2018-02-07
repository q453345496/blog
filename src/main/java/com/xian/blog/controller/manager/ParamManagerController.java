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
import com.xian.blog.model.Param;
import com.xian.blog.service.ParamService;

@Controller
@RequestMapping("/admin/param")
public class ParamManagerController {
	@Resource
	private ParamService paramService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, Param param) {
		DataGridResult vo = new DataGridResult();
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("typeCode", param.getTypeCode());
		map.put("key", param.getKey());
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		vo.setTotal(paramService.getTotal(map));
		vo.setRows(paramService.list(map));
		return vo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(Param param) {
		paramService.save(param);
		return CommonResult.success(param.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
