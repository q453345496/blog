package com.xian.util;

import java.io.IOException;
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

public class RelateJspCreator extends AutoCreator{

	static String Model = "BlogType";
	static String modelCN = "分类";
	static String model = StringUtils.uncapitalize(Model);
	static String modelId = "typeId";
	static String modelNameField = "blogTypeName";
	static String ParentModel = "Column";
	static String parentModelCN = "栏目";
	static String parentModel = StringUtils.uncapitalize(ParentModel);
	static String parentId = "columnId";
	static boolean parentIsTree = false;
	static String relateModel = "columnResource";
	static String relateListUrl = "/admin/" + relateModel + "/listRelate";
	static String relateUrl = "/admin/" + relateModel + "/save";
	static String unRelateListUrl = "/admin/" + relateModel + "/listUnRelate";
	static String unRelateUrl = "/admin/" + relateModel + "/delete";
	
	static String modelFileName = parentModel + "/relate_" + model + SUFFIX_JSP;
	static String parentDataGrid = parentModel + (parentIsTree ? "TreeGrid" : "DataGrid");

	static GroupTemplate gt;
	static Map<String, Object> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		FileResourceLoader resourceLoader = new FileResourceLoader(TEMPLATE_ROOT_JSP, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		gt = new GroupTemplate(resourceLoader, cfg);
		Template t = gt.getTemplate(TEMPLATE_PATH_RELATE_DIALOG);

		map.put("model", model);
		map.put("modelCN", modelCN);
		map.put("parentModelCN", parentModelCN);
		map.put("parentIsTree", parentIsTree);
		
		map.put("dialogId", parentModel + "Relate" + model + "Dialog");
		map.put("contentRelateTabs", model + "RelateTabs");

		map.put("refreshTabFunc", "refresh" + Model + "TabFunc");
		map.put("modeId", modelId);//id field
		map.put("modelNameField", modelNameField);//name field
		
		map.put("parentDataGrid", parentDataGrid);//programTypeDataGrid
		map.put("parentId", parentId);
		map.put("openRelateDialogFunc", "openRelate" + Model + "DialogFunc");

		map.put("relatedDataGridToolbar", model + "RelatedDataGridToolbar");
		map.put("delUnRelateBtn", model + "UnrelateBtn");
		map.put("delRelateFunc", "delRelate" + Model + "Func");
		map.put("doSearchRFunc", "doSearch" + Model + "RFunc");
		map.put("relatedDataGrid", model + "RelatedDataGrid");
		map.put("relatedDataGridUrl", relateListUrl);//relateListUrl
		map.put("relate_url", relateUrl);//relateUrl

		map.put("unRelatedDataGridToolbar", model + "UnRelatedDataGridToolbar");
		map.put("addRelateBtn", model + "AddRelateBtn");
		map.put("addRelateFunc", model + "AddRelateFunc");
		map.put("doSearchURFunc", "doSearch" + Model + "URFunc");
		map.put("unRelatedDataGrid", model + "UnRelatedDataGrid");
		map.put("unRelatedDataGridUrl", unRelateListUrl);//unrelateListUrl
		map.put("unRelate_url", unRelateUrl);//unrelateUrl

		t.binding(map);

		Path path = Paths.get(ROOT, CLASS_WEB, ADMIN_PATH, modelFileName);
		path.toFile().getParentFile().mkdirs();

		t.renderTo(Files.newBufferedWriter(path, CHARSET_UTF8, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING));

	}

}
