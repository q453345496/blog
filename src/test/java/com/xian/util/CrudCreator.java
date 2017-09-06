package com.xian.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
import org.beetl.core.resource.FileResourceLoader;

public class CrudCreator {
	static Charset chartSet = StandardCharsets.UTF_8;
	static String java_suffix = ".java";
	static String xml_suffix = ".xml";

	static String root = "D:/workspace/blog";
	static String main = "src/main/java";
	static String resource = "src/main/resources";
	static String packPath = "com/xian/blog";

	static String packageName = "com.xian.blog";
	
	static String table = "t_role";// table name
	static String Model = "Role";
	static String model = StringUtils.uncapitalize(Model);
	static boolean manager = true;
	static String requestMapping = (manager ? "/admin" : "") + "/" + model;

	// Model
	static String modelPath = "model";
	static String modeClass = Model;
	static String modelFileName = modeClass + java_suffix;

	// Dao
	static String daoPath = "dao";
	static String daoClass = Model + "Dao";
	static String daoFileName = daoClass + java_suffix;

	// Mapper.xml
	static String mapperPath = "mappers";
	static String modelMapper = modeClass + "Mapper";
	static String mapperFileName = modelMapper + xml_suffix;

	// Service
	static String servicePath = "service";
	static String serviceClass = Model + "Service";
	static String serviceFileName = serviceClass + java_suffix;

	// Controller
	static String controllerPath = manager ? ("controller" + "/manager") : "controller";
	static String controllerClass = Model + (manager ? "Manager" : "") + "Controller";
	static String controllerFileName = controllerClass + java_suffix;

	static GroupTemplate gt;
	static String templateRoot = "D:/workspace/blog/src/test/java/com/xian/util/template/crud";
	static String modelTemplatePath = "model.btl";
	static String daoTemplatePath = "dao.btl";
	static String mapperTemplatePath = "mapper.btl";
	static String serviceTemplatePath = "service.btl";
	static String controllerTemplatePath = "controller.btl";
	static Map<String, Object> map = new HashMap<>();;

	public static void main(String[] args) throws Exception {
		FileResourceLoader resourceLoader = new FileResourceLoader(templateRoot, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		gt = new GroupTemplate(resourceLoader, cfg);

		map.put("packageName", packageName);
		map.put("manager", manager);
		map.put("table", table);

		map.put("Model", Model);
		map.put("model", model);

		map.put("ModelDao", daoClass);
		map.put("modelDao", StringUtils.uncapitalize(daoClass));

		map.put("ModelService", serviceClass);
		map.put("modelService", StringUtils.uncapitalize(serviceClass));

		map.put("ModelController", controllerClass);
		map.put("modelController", StringUtils.uncapitalize(controllerClass));

		map.put("requestMapping", requestMapping);
		//
		createModel();
		createDao();
		createMapper();
		createService();
		createController();
	}

	private static void createModel() throws BeetlException, IOException {
		Template t = gt.getTemplate(modelTemplatePath);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(root, main, packPath, modelPath, modelFileName), chartSet,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createDao() throws BeetlException, IOException {
		Template t = gt.getTemplate(daoTemplatePath);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(root, main, packPath, daoPath, daoFileName), chartSet,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createMapper() throws BeetlException, IOException {
		Template t = gt.getTemplate(mapperTemplatePath);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(root, resource, packPath, mapperPath, mapperFileName), chartSet,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createService() throws BeetlException, IOException {
		Template t = gt.getTemplate(serviceTemplatePath);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(root, main, packPath, servicePath, serviceFileName), chartSet,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createController() throws BeetlException, IOException {
		Template t = gt.getTemplate(controllerTemplatePath);

		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(root, main, packPath, controllerPath, controllerFileName),
				chartSet, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}
}
