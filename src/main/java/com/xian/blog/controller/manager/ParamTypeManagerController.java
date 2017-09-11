package com.xian.blog.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG = LoggerFactory.getLogger(ParamTypeManagerController.class);
	@Resource
	private ParamTypeService paramTypeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, ParamType paramType) {
		DataGridResult vo = new DataGridResult();
		try {
			Page p = new Page(page, rows);
			Map<String, Object> map = new HashMap<>();
			map.put("name", paramType.getName());
			map.put("code", paramType.getCode());
			map.put("start", p.getStart());
			map.put("size", p.getPageSize());
			vo.setTotal(paramTypeService.getTotal(map));
			vo.setRows(paramTypeService.list(map));
		} catch (Exception e) {
			LOG.error("list Error", e);
		}
		return vo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(ParamType paramType) {
		try {
			paramTypeService.save(paramType);
			return CommonResult.success(paramType.getId());
		} catch (Exception e) {
			LOG.error("save Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult update(ParamType paramType) {
		try {
			paramTypeService.update(paramType);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("update Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		try {
			ParamType paramType = paramTypeService.get(id);
			return CommonResult.success(paramType);
		} catch (Exception e) {
			LOG.error("get detail Error", e);
			return CommonResult.fail(e);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			paramTypeService.delete(id);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("delete Error", e);
			return CommonResult.fail(e);
		}
	}
}
