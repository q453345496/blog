package com.xian.blog.controller.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import com.xian.blog.model.JournalCategory;
import com.xian.blog.service.JournalCategoryService;

@Controller
@RequestMapping("/admin/journalCategory")
public class JournalCategoryManagerController {
	private static final Logger LOG = LoggerFactory.getLogger(JournalCategoryManagerController.class);
	@Resource
	private JournalCategoryService journalCategoryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<JournalCategory> list(@RequestParam(value = "id", required = false, defaultValue = "0") Long parentId) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("parentId", parentId);
			List<JournalCategory> list = journalCategoryService.list(map);
			return list;
		} catch (Exception e) {
			LOG.error("BlogTypeController list Error", e);
		}
		return Collections.emptyList();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult save(JournalCategory journalCategory) {
		try {
			journalCategoryService.save(journalCategory);
			return CommonResult.success(journalCategory.getId());
		} catch (Exception e) {
			LOG.error("save Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CommonResult update(JournalCategory journalCategory) {
		try {
			journalCategoryService.update(journalCategory);
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
			JournalCategory journalCategory = journalCategoryService.get(id);
			return CommonResult.success(journalCategory);
		} catch (Exception e) {
			LOG.error("get detail Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			journalCategoryService.delete(id);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("delete Error", e);
			return CommonResult.fail(e);
		}
	}
}
