package com.xian.util;

import java.io.IOException;
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

/**
 * 测试Guns模板引擎
 *
 */
public class RelateJspCreator {
	static Charset chartSet = StandardCharsets.UTF_8;
	static String jsp_suffix = ".jsp";

	static String root = "D:/workspace/blog";
	static String webPath = "src/main/webapp";
	static String jspPath = "/admin";

	static String Model = "Blog";
	static String modelCN = "文章";
	static String model = StringUtils.uncapitalize(Model);
	static String ParentModel = "SpecialTopic";
	static String parentModelCN = "专题";
	static String parentModel = StringUtils.uncapitalize(ParentModel);

	static String relateListUrl = "";
	static String relateUrl = "";
	static String unRelateListUrl = "";
	static String unRelateUrl = "";
	static String modelFileName = ParentModel + "/relate_" + model + jsp_suffix;
	static String programTypeDataGrid = parentModel + "DataGrid";

	static GroupTemplate gt;
	static String templateRoot = "D:/workspace/blog/src/test/java/com/xian/util/template/jsp";
	static String relateDialogTemplatePath = "relateDialog.btl";

	static Map<String, Object> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		FileResourceLoader resourceLoader = new FileResourceLoader(templateRoot, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		gt = new GroupTemplate(resourceLoader, cfg);
		Template t = gt.getTemplate(relateDialogTemplatePath);

		map.put("dialogId", "contentRelateDialog");

		map.put("contentRelateTabs", "blogRelateTabs");
		map.put("model", model);
		map.put("modelCN", modelCN);
		map.put("parentModelCN", parentModelCN);

		map.put("refreshTabFunc", "refresh" + Model + "TabFunc");
		map.put("modeId", model + "Id");//id field
		map.put("parentDataGrid", programTypeDataGrid);//programTypeDataGrid

		map.put("openRelateDialogFunc", model + "OpenRelateDialogFunc");

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

		Path path = Paths.get(root, webPath, jspPath, modelFileName);
		path.toFile().getParentFile().mkdirs();

		t.renderTo(Files.newBufferedWriter(path, chartSet, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING));

		String str = t.render();
		System.out.println(str);
	}

	public static long getTime() {
		return System.currentTimeMillis();
	}
}
