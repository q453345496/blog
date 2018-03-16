package com.xian.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xian.blog.common.ueditor.UEditorCatchResult;
import com.xian.blog.common.ueditor.UEditorListResult;
import com.xian.blog.common.ueditor.UEditorResult;
import com.xian.blog.common.ueditor.UEditorUploadResult;
import com.xian.blog.common.ueditor.UEditorUtil;
import com.xian.blog.constants.UEditorConstant;
import com.xian.blog.model.Attachment;
import com.xian.blog.service.AttachmentService;

@RequestMapping("/ueditor")
@Controller
public class UEditorController {
	@Resource
	private AttachmentService attachmentService;

	@RequestMapping(value = "/uploadImage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorResult uploadImage(@RequestParam("upfile") MultipartFile upfile, @RequestParam("id") String id) {
		try {
			Attachment attachment = attachmentService.upload(upfile, id, "blog", UEditorConstant.IMAGE_PATH);
			return new UEditorUploadResult(attachment.getName(), attachment.getPath());
		} catch (Exception e) {
			return UEditorUploadResult.errorResult("upload error");
		}
	}

	@RequestMapping(value = "/uploadVideo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorResult uploadVideo(@RequestParam("upfile") MultipartFile upfile, @RequestParam("id") String id) {
		try {
			Attachment attachment = attachmentService.upload(upfile, id, "blog", UEditorConstant.VIDEO_PATH);
			return new UEditorUploadResult(attachment.getName(), attachment.getPath());
		} catch (Exception e) {
			return UEditorUploadResult.errorResult("upload error");
		}
	}

	@RequestMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorResult uploadFile(@RequestParam("upfile") MultipartFile upfile, @RequestParam("id") String id) {
		try {
			Attachment attachment = attachmentService.upload(upfile, id, "blog", UEditorConstant.FILE_PATH);
			return new UEditorUploadResult(attachment.getName(), attachment.getPath());
		} catch (Exception e) {
			return UEditorUploadResult.errorResult("upload error");
		}
	}

	@RequestMapping(value = "/listFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorListResult listFile() {
		return UEditorListResult.error();//不提供服务
	}

	@RequestMapping(value = "/listImage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorListResult listImage() {
		return UEditorListResult.error();//不提供服务
	}

	/**
	 * 不使用(@RequestParam("sources[]") String[] resources) 参数是因为地址中可能带有逗号，导致出现问题
	 */
	@RequestMapping(value = "/catchImage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorCatchResult catchImage(@RequestParam("id") String id, HttpServletRequest request) {
		String[] sources = request.getParameterValues("sources[]");
		return UEditorUtil.capture(id, sources);
	}
}
