package com.xian.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xian.blog.common.ueditor.UEditorCatchResult;
import com.xian.blog.common.ueditor.UEditorListResult;
import com.xian.blog.common.ueditor.UEditorResult;
import com.xian.blog.common.ueditor.UEditorUtil;
import com.xian.blog.constants.UEditorConstant;

@RequestMapping("/ueditor")
@Controller
public class UEditorController {

	@RequestMapping(value = "/uploadImage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorResult uploadImage(@RequestParam("upfile") MultipartFile upfile, HttpServletRequest request,
			HttpServletResponse response) {
		return UEditorUtil.upload(upfile, UEditorConstant.IMAGE_PATH);
	}

	@RequestMapping(value = "/uploadVideo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorResult uploadVideo(@RequestParam("upfile") MultipartFile upfile, HttpServletRequest request,
			HttpServletResponse response) {
		return UEditorUtil.upload(upfile, UEditorConstant.VIDEO_PATH);
	}

	@RequestMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorResult uploadFile(@RequestParam("upfile") MultipartFile upfile, HttpServletRequest request,
			HttpServletResponse response) {
		return UEditorUtil.upload(upfile, UEditorConstant.FILE_PATH);
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
	 * 不使用(@RequestParam("sources[]") String[] resources)
	 * 参数是因为地址中可能带有逗号，导致出现问题
	 */
	@RequestMapping(value = "/catchImage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public UEditorCatchResult catchImage(HttpServletRequest request) {
		String[] sources = request.getParameterValues("sources[]");
		return UEditorUtil.capture(sources);
	}
}
