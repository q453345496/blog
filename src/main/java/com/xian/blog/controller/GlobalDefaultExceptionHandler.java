package com.xian.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xian.blog.common.CommonResult;
import com.xian.blog.exception.CheckException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	@ExceptionHandler({ MissingServletRequestParameterException.class })
	@ResponseBody
	public CommonResult miss(MissingServletRequestParameterException ex) {
		return CommonResult.fail(ex);
	}

	@ExceptionHandler({ CheckException.class })
	@ResponseBody
	public CommonResult check(CheckException ex) {
		LOG.error(ex.getMessage(), ex);
		return CommonResult.fail(ex);
	}

	@ExceptionHandler()
	public ModelAndView all(Exception ex) {
		LOG.error(ex.getMessage(), ex);
		return new ModelAndView("error/500").addObject("error", ex.getMessage());
	}
}