package com.xian.blog.controller.manager;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.common.Page;
import com.xian.blog.model.JournalCategory;
import com.xian.blog.service.JournalCategoryService;

/**
 * Date:2016年7月29日下午9:35:11
 * 
 */
@Controller
@RequestMapping("/admin/journalCategory")
public class JournalCategoryController {
	private static final Logger LOG = LoggerFactory.getLogger(JournalCategoryController.class);
	@Resource
	private JournalCategoryService categoryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public DataGridResult list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows, JournalCategory category) {
		DataGridResult vo = new DataGridResult();
		try {
			Page p = new Page(page, rows);
			category.setParentId(0l);
			Map<String, Object> map = PropertyUtils.describe(category);
			map.put("start", p.getStart());
			map.put("size", p.getPageSize());
			vo.setTotal(categoryService.getTotal(map));
			vo.setRows(categoryService.list(map));
		} catch (Exception e) {
			LOG.error("BlogTypeController list Error", e);
		}
		return vo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(JournalCategory category) {
		try {
			categoryService.save(category);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("BlogTypeController save Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(JournalCategory category) {
		try {
			categoryService.update(category);
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("BlogTypeController update Error", e);
			return CommonResult.fail(e);
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public CommonResult delete(JournalCategory category) {
		try {
			categoryService.delete(category.getId());
			return CommonResult.success();
		} catch (Exception e) {
			LOG.error("BlogTypeController delete Error", e);
			return CommonResult.fail(e);
		}
	}
}
