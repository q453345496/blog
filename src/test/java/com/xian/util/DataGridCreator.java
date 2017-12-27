package com.xian.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

public class DataGridCreator {
	static Charset chartSet = StandardCharsets.UTF_8;
	static String jsp_suffix = ".jsp";

	static String root = "D:/workspace/blog";
	static String webPath = "src/main/webapp";
	static String jspPath = "/admin";

	static String Model = "Role";
	static String modelCN = "角色";
	static String model = StringUtils.uncapitalize(Model);
	static String modelFileName = model + "/" + model + jsp_suffix;

	static GroupTemplate gt;
	static String templateRoot = "D:/workspace/blog/src/test/java/com/xian/util/template/jsp";
	static String dataGridTemplatePath = "dataGrid.btl";
	static String treeGridTemplatePath = "treeGrid.btl";

	static Map<String, Object> map = new HashMap<>();;

	public static void main(String[] args) throws Exception {
		FileResourceLoader resourceLoader = new FileResourceLoader(templateRoot, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		gt = new GroupTemplate(resourceLoader, cfg);

		map.put("Model", Model);
		map.put("model", model);
		map.put("modelCN", modelCN);

		map.put("listUrl", "/admin" + ("/" + model + "/list"));
		map.put("saveUrl", "/admin" + ("/" + model + "/save"));
		map.put("deleteUrl", "/admin" + ("/" + model + "/delete"));
		map.put("updateUrl", "/admin" + ("/" + model + "/update"));
		boolean tree = false;
		
		Template t = gt.getTemplate(tree ? treeGridTemplatePath : dataGridTemplatePath);
		t.binding(map);
		
		Path path = Paths.get(root, webPath, jspPath, modelFileName);
		path.toFile().getParentFile().mkdirs();
		
		t.renderTo(Files.newBufferedWriter(path, chartSet, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING));
		System.out.println(t.render());
	}

}