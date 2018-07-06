package com.xian.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xian.blog.service.AttachmentService;

@RequestMapping("/editorMd")
@Controller
public class EditorMdController {
	@Resource
	private AttachmentService attachmentService;

	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestParam("editormd-image-file") MultipartFile upfile,
			@RequestParam(name = "bizId", required = false) String bizId, @RequestParam("bizType") String bizType) {
		Map<String, Object> map = new HashMap<>();
		try {
			//		Attachment attachment = attachmentService.upload(upfile, bizId, "blog", FTPConstant.PATH_IMAGE);
			map.put("success", 1);
			map.put("url", "http://192.168.15.165/rem/material/oUYs25e9yiZ0/2018/06/27/BiWCERSGqio4.jpg");
		} catch (Exception e) {
			map.put("success", 0);
			map.put("message", "upload error");
		}
		return map;
	}

}
