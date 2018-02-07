package com.xian.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

public class DataGridCreator extends AutoCreator {

	static String Model = "journalCategory";
	static String modelCN = "流水类型";
	static boolean isTree = true;
	static String model = StringUtils.uncapitalize(Model);
	static String modelFileName = model + "/" + model + SUFFIX_JSP;

	protected static GroupTemplate gt;
	protected static Map<String, Object> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		FileResourceLoader resourceLoader = new FileResourceLoader(TEMPLATE_ROOT_JSP, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		gt = new GroupTemplate(resourceLoader, cfg);

		map.put("Model", Model);
		map.put("model", model);
		map.put("modelCN", modelCN);

		map.put("listUrl", ADMIN_PATH + ("/" + model + (isTree ? "/tree" : "/list")));
		map.put("saveUrl", ADMIN_PATH + ("/" + model + "/save"));
		map.put("deleteUrl", ADMIN_PATH + ("/" + model + "/delete"));
		map.put("updateUrl", ADMIN_PATH + ("/" + model + "/update"));

		Template t = gt.getTemplate(isTree ? TEMPLATEPATH_TREE_GRID : TEMPLATE_PATH_DATA_GRID);
		t.binding(map);

		Path path = Paths.get(ROOT, CLASS_WEB, ADMIN_PATH, modelFileName);
		path.toFile().getParentFile().mkdirs();

		t.renderTo(Files.newBufferedWriter(path, CHARSET_UTF8, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING));
		System.out.println(t.render());
	}

}
