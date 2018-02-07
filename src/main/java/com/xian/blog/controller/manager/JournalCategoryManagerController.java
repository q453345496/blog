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
import com.xian.blog.model.JournalCategory;
import com.xian.blog.service.JournalCategoryService;

@Controller
@RequestMapping("/admin/journalCategory")
public class JournalCategoryManagerController {
	@Resource
	private JournalCategoryService journalCategoryService;

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public List<JournalCategory> tree(@RequestParam(value = "id", required = false, defaultValue = "-1") Long parentId) {
		Map<String, Object> map = new HashMap<>();
		map.put("parentId", parentId);
		List<JournalCategory> list = journalCategoryService.list(map);
		return list;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(JournalCategory journalCategory) {
		journalCategoryService.save(journalCategory);
		return CommonResult.success(journalCategory.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult update(JournalCategory journalCategory) {
		journalCategoryService.update(journalCategory);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		JournalCategory journalCategory = journalCategoryService.get(id);
		return CommonResult.success(journalCategory);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		journalCategoryService.delete(id);
		return CommonResult.success();
	}
}
