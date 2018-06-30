package com.xian.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xian.blog.common.ueditor.UEditorCatchResult;
import com.xian.blog.common.ueditor.UEditorListResult;
import com.xian.blog.common.ueditor.UEditorRemoteResult;
import com.xian.blog.common.ueditor.UEditorResult;
import com.xian.blog.common.ueditor.UEditorUploadResult;
import com.xian.blog.constants.FTPConstant;
import com.xian.blog.model.Attachment;
import com.xian.blog.service.AttachmentService;

@RequestMapping("/ueditor")
@Controller
public class UEditorController {
	@Resource
	private AttachmentService attachmentService;

	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public UEditorResult uploadImage(@RequestParam("upfile") MultipartFile upfile, @RequestParam("bizId") String bizId,
			@RequestParam("bizType") String bizType) {
		try {
			Attachment attachment = attachmentService.upload(upfile, bizId, "blog", FTPConstant.PATH_IMAGE);
			return new UEditorUploadResult(attachment.getName(), attachment.getPath());
		} catch (Exception e) {
			return UEditorUploadResult.errorResult("upload error");
		}
	}

	@RequestMapping(value = "/uploadVideo")
	@ResponseBody
	public UEditorResult uploadVideo(@RequestParam("upfile") MultipartFile upfile, @RequestParam("bizId") String bizId,
			@RequestParam("bizType") String bizType) {
		try {
			Attachment attachment = attachmentService.upload(upfile, bizId, "blog", FTPConstant.PATH_VIDEO);
			return new UEditorUploadResult(attachment.getName(), attachment.getPath());
		} catch (Exception e) {
			return UEditorUploadResult.errorResult("upload error");
		}
	}

	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public UEditorResult uploadFile(@RequestParam("upfile") MultipartFile upfile, @RequestParam("bizId") String bizId,
			@RequestParam("bizType") String bizType) {
		try {
			Attachment attachment = attachmentService.upload(upfile, bizId, "blog", FTPConstant.PATH_FILE);
			return new UEditorUploadResult(attachment.getName(), attachment.getPath());
		} catch (Exception e) {
			return UEditorUploadResult.errorResult("upload error");
		}
	}

	@RequestMapping(value = "/listFile")
	@ResponseBody
	public UEditorListResult listFile() {
		return UEditorListResult.error();//不提供服务
	}

	@RequestMapping(value = "/listImage")
	@ResponseBody
	public UEditorListResult listImage() {
		return UEditorListResult.error();//不提供服务
	}

	/**
	 * 不使用(@RequestParam("sources[]") String[] resources) 参数是因为地址中可能带有逗号，导致出现问题
	 */
	@RequestMapping(value = "/catchImage")
	@ResponseBody
	public UEditorCatchResult catchImage(@RequestParam("bizId") String bizId, @RequestParam("bizType") String bizType,
			HttpServletRequest request) {
		String[] sources = request.getParameterValues("sources[]");
		UEditorCatchResult result = new UEditorCatchResult();
		if (sources != null) {
			List<UEditorResult> list = new ArrayList<>(sources.length);
			for (String sourceUrl : sources) {
				try {
					Attachment attachment = attachmentService.captureRemoteData(sourceUrl, bizId, "blog",
							FTPConstant.PATH_IMAGE);
					list.add(new UEditorRemoteResult(attachment.getSourceURL(), attachment.getPath()));
				} catch (Exception e) {
					list.add(UEditorResult.errorResult(e.getMessage()));
				}
			}
			result.setList(list);
		} else {
			result.setState("must not be empty");
		}
		return result;
	}
}
