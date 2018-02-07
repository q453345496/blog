//package com.xian.blog.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.Locale;
//import java.util.TimeZone;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.xian.blog.common.CommonResult;
//import com.xian.blog.exception.CheckException;
//
//@Component
//public class ExceptionResolver implements HandlerExceptionResolver {
//	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);
//	private static final ObjectMapper MAPPER = new ObjectMapper();
//	static {
//		MAPPER.setLocale(Locale.CHINA);
//		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
//		MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//	}
//
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
//			Exception e) {
//		LOGGER.error(e.getMessage(), e);
//		if (e instanceof CheckException) {
//			MappingJackson2JsonView jsonView = new MappingJackson2JsonView(MAPPER);
//			jsonView.setAttributesMap(CommonResult.fail(e).toMap());
//			return new ModelAndView(jsonView);
//		}
//		return new ModelAndView("error/500").addObject("error", e.getMessage());
//	}
//
//}