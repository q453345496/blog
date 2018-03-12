package com.xian.blog.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Date:2016年7月27日下午10:31:16
 * 
 */
public final class UEditorConstant {
	public static final String IMAGE_PATH = "image";
	public static final String VIDEO_PATH = "video";
	public static final String FILE_PATH = "file";
//	public static final String CATCH_IMAGE_PATH = "catchImage";

	public static final String SUCCESS = "SUCCESS";

	public static final Map<String, String> IMAGE_TYPES = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("image/gif", ".gif");
			put("image/jpeg", ".jpg");
			put("image/jpg", ".jpg");
			put("image/png", ".png");
			put("image/bmp", ".bmp");
		}
	};

	public static String getSuffix(String mime) {
		return IMAGE_TYPES.get(mime);
	}
}
