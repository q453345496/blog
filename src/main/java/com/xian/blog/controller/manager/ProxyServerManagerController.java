package com.xian.blog.controller.manager;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.xian.blog.model.ProxyServer;
import com.xian.blog.service.ProxyServerService;

@Controller
@RequestMapping("/admin/proxyServer")
public class ProxyServerManagerController {
	@Resource
	private ProxyServerService proxyServerService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridResult list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows,
			ProxyServer proxyServer) {
		return proxyServerService.page(new Page<ProxyServer>(page, rows), //
				new EntityWrapper<ProxyServer>()//
						.eq(StringUtils.isNotBlank(proxyServer.getIp()), "ip", proxyServer.getIp())//
						.eq(proxyServer.getPort() != null, "port", proxyServer.getPort())//
						.eq(proxyServer.getProtocol() != null, "protocol", proxyServer.getProtocol())//
						.eq(proxyServer.getState() != null, "state", proxyServer.getState())//
						.eq(proxyServer.getType() != null, "type", proxyServer.getType())//
						.orderBy("last_check_time", false)
		);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult save(ProxyServer proxyServer) {
		proxyServerService.save(proxyServer);
		return CommonResult.success(proxyServer.getId());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(ProxyServer proxyServer) {
		proxyServerService.update(proxyServer);
		return CommonResult.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult detail(@PathVariable("id") Long id) {
		ProxyServer proxyServer = proxyServerService.get(id);
		return CommonResult.success(proxyServer);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam(value = "id", required = true) Long[] ids) {
		proxyServerService.delete(ids);
		return CommonResult.success();
	}
}
