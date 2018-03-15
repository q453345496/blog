package com.xian.blog.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.common.Page;
import com.xian.blog.exception.CheckException;
import com.xian.blog.service.SpecialTopicResourceService;

@Controller
@RequestMapping("/admin/topicResource")
public class SpecialTopicResourceManagerController {
	@Resource
	private SpecialTopicResourceService specialTopicResourceService;

	@RequestMapping(value = "/listRelate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult listRelate(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "topicId", required = true) Long topicId,
			@RequestParam(value = "blogTitle", required = false) String blogTitle,
			@RequestParam(value = "blogTypeId", required = false) Long blogTypeId) {
		if (topicId == null) {
			throw new CheckException("参数不能为空");
		}
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		map.put("topicId", topicId);
		map.put("blogTitle", blogTitle);
		map.put("blogTypeId", blogTypeId);

		return specialTopicResourceService.pageRelate(map);
	}

	@RequestMapping(value = "/listUnRelate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataGridResult listUnRelate(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "topicId", required = true) Long topicId,
			@RequestParam(value = "blogTitle", required = false) String blogTitle,
			@RequestParam(value = "blogTypeId", required = false) Long blogTypeId) {
		if (topicId == null) {
			throw new CheckException("参数不能为空");
		}
		Page p = new Page(page, rows);
		Map<String, Object> map = new HashMap<>();
		map.put("start", p.getStart());
		map.put("size", p.getPageSize());
		map.put("topicId", topicId);
		map.put("blogTitle", blogTitle);
		map.put("blogTypeId", blogTypeId);

		return specialTopicResourceService.pageUnRelate(map);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(Long topicId, Long[] ids) {
		if (topicId == null || ids == null || ids.length == 0) {
			throw new CheckException("参数不能为空");
		}
		specialTopicResourceService.save(topicId, ids);
		return CommonResult.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(Long[] ids) {
		if (ids != null && ids.length > 0) {
			specialTopicResourceService.delete(ids);
		}
		return CommonResult.success();
	}
}
