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
import com.xian.blog.model.SpecialTopic;
import com.xian.blog.service.SpecialTopicService;

@Controller
@RequestMapping("/admin/specialTopic")
public class SpecialTopicManagerController {
	@Resource
	private SpecialTopicService specialTopicService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, SpecialTopic specialTopic) {
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		map.put("name", specialTopic.getName());
		return specialTopicService.page(map);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(SpecialTopic specialTopic) {
		specialTopicService.save(specialTopic);
		return CommonResult.success(specialTopic.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult update(SpecialTopic specialTopic) {
		specialTopicService.update(specialTopic);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		SpecialTopic specialTopic = specialTopicService.get(id);
		return CommonResult.success(specialTopic);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		specialTopicService.delete(id);
		return CommonResult.success();
	}
}
