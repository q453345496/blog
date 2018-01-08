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
import com.xian.blog.model.SpecialTopic;
import com.xian.blog.service.SpecialTopicService;

@Controller
@RequestMapping("/admin/specialTopic")
public class SpecialTopicManagerController {
	private static final Logger LOG = LoggerFactory.getLogger(SpecialTopicManagerController.class);
	@Resource
	private SpecialTopicService specialTopicService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, SpecialTopic specialTopic) {
		DataGridResult vo = new DataGridResult();
		try {
			Page p = new Page(page, rows);
			Map<String, Object> map = new HashMap<>();
			map.put("start", p.getStart());
			map.put("size", p.getPageSize());
			map.put("name", specialTopic.getName());
			vo.setTotal(specialTopicService.getTotal(map));
			vo.setRows(specialTopicService.list(map));
		} catch (Exception e) {
			LOG.error("list Error", e);
		}
		return vo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(SpecialTopic specialTopic) {
		try {
			specialTopicService.save(specialTopic);
			return CommonResult.success(specialTopic.getId());
		} catch (Exception e) {
			LOG.error("save Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult update(SpecialTopic specialTopic) {
		try {
			specialTopicService.update(specialTopic);
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
			SpecialTopic specialTopic = specialTopicService.get(id);
			return CommonResult.success(specialTopic);
		} catch (Exception e) {
			LOG.error("get detail Error", e);
			return CommonResult.fail(e);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			specialTopicService.delete(id);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("delete Error", e);
			return CommonResult.fail(e);
		}
	}
}
