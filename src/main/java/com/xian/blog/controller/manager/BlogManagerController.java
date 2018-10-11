package com.xian.blog.controller.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.CommonResult;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.model.Blog;
import com.xian.blog.service.BlogService;
import com.xian.blog.service.BlogTypeService;
import com.xian.blog.util.JsoupUtil;
import com.xian.blog.util.MarkdownUtil;

@Controller
@RequestMapping("/admin/blog")
public class BlogManagerController {
	@Resource
	private BlogService blogService;
	@Resource
	private BlogTypeService blogTypeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows, Blog blog) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", blog.getTitle());
		map.put("typeId", blog.getTypeId());
		return blogService.page(new Page<Blog>(page, rows), map);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(Blog blog) {
		String html = MarkdownUtil.toHtml(blog.getContent());
		Document htmlDoc = Jsoup.parse(html);
		if (StringUtils.isBlank(blog.getSummary())) {
			blog.setSummary(StringUtils.substring(StringEscapeUtils.escapeHtml4(htmlDoc.text()), 0, 200));
		}
		if (Blog.DRAFT == blog.getStatus()) {//草稿的保存后成为正式使用
			blog.setStatus(Blog.ONLINE);
			blog.setCreateTime(new Date());
		}
		String thumb = JsoupUtil.getFirstImgURL(htmlDoc);
		if (StringUtils.isBlank(thumb)) {
			thumb = blogTypeService.get(blog.getTypeId()).getImgPathURL();
		}
		blog.setThumb(thumb);
		blogService.update(blog);
		return CommonResult.success(blog.getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		Blog blog = blogService.detail(id);
		return CommonResult.success(blog);
	}

	@RequestMapping(value = "/toAdd")
	public ModelAndView toAddPage() {
		Long draftId = blogService.getDraftId();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/blog/modifyBlog2");
		mv.addObject("id", draftId);
		return mv;
	}

	@RequestMapping(value = "/toEdit/{id}")
	public ModelAndView toEditPage(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/blog/modifyBlog2");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(Long[] id) {
		blogService.delete(id);
		return CommonResult.success();
	}
}
