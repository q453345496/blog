package com.xian.util;

import java.io.IOException;
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

public class CrudCreator extends AutoCreator {

	static String table = "t_column_resource";// table name
	static String Model = "ColumnResource";
	static boolean isTree = false;
	static String model = StringUtils.uncapitalize(Model);
	static boolean manager = true;
	static String requestMapping = (manager ? ADMIN_PATH : "") + "/" + model;

	// Model
	static String modelPath = "model";
	static String modeClass = Model;
	static String modelFileName = modeClass + SUFFIX_JAVA;

	// Dao
	static String daoPath = "dao";
	static String daoClass = Model + "Dao";
	static String daoFileName = daoClass + SUFFIX_JAVA;

	// Mapper.xml
	static String mapperPath = "mappers";
	static String modelMapper = modeClass + "Mapper";
	static String mapperFileName = modelMapper + SUFFIX_XML;

	// Service
	static String servicePath = "service";
	static String serviceClass = Model + "Service";
	static String serviceFileName = serviceClass + SUFFIX_JAVA;

	// Controller
	static String controllerPath = manager ? ("controller" + "/manager") : "controller";
	static String controllerClass = Model + (manager ? "Manager" : "") + "Controller";
	static String controllerFileName = controllerClass + SUFFIX_JAVA;
	
	protected static GroupTemplate gt;
	protected static Map<String, Object> map = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		FileResourceLoader resourceLoader = new FileResourceLoader(TEMPLATE_ROOT_JAVA, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		gt = new GroupTemplate(resourceLoader, cfg);

		map.put("isTree", isTree);

		map.put("packageName", PACKAGE_NAME);
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
		Template t = gt.getTemplate(TEMPLATE_PATH_MODEL);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(ROOT, CLASS_MAIN, PACK_PATH, modelPath, modelFileName),
				CHARSET_UTF8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createDao() throws BeetlException, IOException {
		Template t = gt.getTemplate(TEMPLATE_PATH_DAO);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(ROOT, CLASS_MAIN, PACK_PATH, daoPath, daoFileName), CHARSET_UTF8,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createMapper() throws BeetlException, IOException {
		Template t = gt.getTemplate(TEMPLATE_PATH_MAPPER);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(ROOT, CLASS_RESOURCE, PACK_PATH, mapperPath, mapperFileName),
				CHARSET_UTF8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createService() throws BeetlException, IOException {
		Template t = gt.getTemplate(TEMPLATE_PATH_SERVICE);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(ROOT, CLASS_MAIN, PACK_PATH, servicePath, serviceFileName),
				CHARSET_UTF8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	private static void createController() throws BeetlException, IOException {
		Template t = gt.getTemplate(TEMPLATE_PATH_CONTROLLER);
		t.binding(map);
		t.renderTo(Files.newBufferedWriter(Paths.get(ROOT, CLASS_MAIN, PACK_PATH, controllerPath, controllerFileName),
				CHARSET_UTF8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}
}
