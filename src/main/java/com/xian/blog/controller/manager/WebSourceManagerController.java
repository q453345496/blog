package com.xian.blog.controller.manager;
import javax.annotation.Resource;

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
import com.xian.blog.model.WebSource;
import com.xian.blog.service.WebSourceService;

@Controller
@RequestMapping("/admin/webSource")
public class WebSourceManagerController {
	@Resource
	private WebSourceService webSourceService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows, WebSource webSource) {
		return webSourceService.page(new Page<WebSource>(page, rows),
				new EntityWrapper<WebSource>().like("name", webSource.getName()));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(WebSource webSource) {
		webSourceService.save(webSource);
		return CommonResult.success(webSource.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(WebSource webSource) {
		webSourceService.update(webSource);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		WebSource webSource = webSourceService.get(id);
		return CommonResult.success(webSource);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long[] ids) {
		webSourceService.delete(ids);
		return CommonResult.success();
	}
}
