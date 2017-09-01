package com.xian.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

public class CrudCreator {

	public static void main(String[] args) throws IOException {

		String root = "";
		FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

		Template t = gt.getTemplate("/dao.btl");
		Map<String, Object> map = new HashMap<>();
		String packageName = "";
		String Model = "";
		String model = "";
		map.put("packageName", packageName);
		map.put("Model", Model);
		map.put("model", model);
		t.binding(map);

		String str = t.render();
		System.out.println(str);
	}

	public static long getTime() {
		return System.currentTimeMillis();
	}
}
