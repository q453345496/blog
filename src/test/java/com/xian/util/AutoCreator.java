package com.xian.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class AutoCreator {
	public static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
	public static final String SUFFIX_JAVA = ".java";
	public static final String SUFFIX_XML = ".xml";
	public static final String SUFFIX_JSP = ".jsp";

	public static final String ROOT = "D:/workspace/blog";
	public static final String CLASS_MAIN = "src/main/java";
	public static final String CLASS_WEB = "src/main/webapp";
	public static final String CLASS_RESOURCE = "src/main/resources";
	public static final String PACK_PATH = "com/xian/blog";
	public static final String ADMIN_PATH = "/admin";

	public static final String PACKAGE_NAME = "com.xian.blog";

	public static final String TEMPLATE_ROOT_JAVA = "D:/workspace/blog/src/test/java/com/xian/util/template/crud";
	public static final String TEMPLATE_PATH_MODEL = "model.btl";
	public static final String TEMPLATE_PATH_DAO = "dao.btl";
	public static final String TEMPLATE_PATH_MAPPER = "mapper.btl";
	public static final String TEMPLATE_PATH_SERVICE = "service.btl";
	public static final String TEMPLATE_PATH_CONTROLLER = "controller.btl";

	public static final String TEMPLATE_ROOT_JSP = "D:/workspace/blog/src/test/java/com/xian/util/template/jsp";
	public static final String TEMPLATE_PATH_DATA_GRID = "dataGrid.btl";
	public static final String TEMPLATE_PATH_TREE_GRID = "treeGrid.btl";
	public static final String TEMPLATE_PATH_RELATE_DIALOG = "relateDialog.btl";

}
