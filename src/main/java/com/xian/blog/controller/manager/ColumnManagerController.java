package com.xian.blog.controller.manager;

import java.util.HashMap;
import java.util.List;
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
import com.xian.blog.model.Column;
import com.xian.blog.service.ColumnService;

@Controller
@RequestMapping("/admin/column")
public class ColumnManagerController {
	@Resource
	private ColumnService columnService;

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public List<Column> tree(@RequestParam(value = "id", required = false, defaultValue = "-1") Long parentId) {
		Map<String, Object> map = new HashMap<>();
		map.put("parentId", parentId);
		List<Column> list = columnService.list(map);
		return list;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(Column column) {
		columnService.save(column);
		return CommonResult.success(column.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult update(Column column) {
		columnService.update(column);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		Column column = columnService.get(id);
		return CommonResult.success(column);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		columnService.delete(id);
		return CommonResult.success();
	}
}
