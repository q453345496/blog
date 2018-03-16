package com.xian.blog.common.ueditor;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xian.blog.constants.UEditorConstant;
import com.xian.blog.util.FtpAdapter;

/**
 * Date:2016年8月7日下午9:34:26
 * 
 */
public final class UEditorUtil {

	private static final Logger LOG = LoggerFactory.getLogger(UEditorUtil.class);
	private static final String BLOG = "blog/";

	public static String getTitle(String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis())//
				.append((Math.random() + "").substring(2, 8))//随机六位
				.append(suffix);
		return sb.toString();
	}

	private static String getSavepPath(String id, String title, String path) {
		StringBuilder sb = new StringBuilder();
		sb.append(BLOG).append(id)//
				.append("/").append(path)//
				.append("/").append(title);//
		return sb.toString();
	}

	public static UEditorCatchResult capture(String id, String[] sources) {
		UEditorCatchResult result = new UEditorCatchResult();
		result.setState(UEditorConstant.SUCCESS);
		List<UEditorResult> list = new ArrayList<>();
		for (String source : sources) {
			list.add(captureRemoteData(id, source));
		}
		result.setList(list);
		return result;

	}

	private static UEditorResult captureRemoteData(String id, String sourceUrl) {
		try {
			URL url = new URL(sourceUrl);
			String host = url.getHost();
			if ("192.168.15.165".equals(host)) {
				return UEditorResult.errorResult("本地图片不用再上传");
			}
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(10 * 1000);
			connection.setReadTimeout(10 * 1000);
			connection.setDefaultUseCaches(true);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;MSIE7.0;WindowsNT5.1;360SE)");
			if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
				LOG.error("Error Response Code:" + connection.getResponseCode());
				return UEditorResult.errorResult("Error Response Code");
			}
			String suffix = UEditorConstant.getSuffix(connection.getContentType());
			String savepPath = getSavepPath(id, getTitle(suffix), UEditorConstant.IMAGE_PATH);

			FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
			try {
				ftpAdapter.upload(connection.getInputStream(), savepPath);
			} finally {
				FtpAdapter.closeFtpAdapter(ftpAdapter);
			}

			return new UEditorRemoteResult(sourceUrl, savepPath);
		} catch (Exception e) {
			LOG.error("captureRemoteData", e);
			return UEditorResult.errorResult(e.getMessage());
		}
	}

	public static void main(String[] args) {
		System.out.println((getSavepPath("2", getTitle(".java"), UEditorConstant.IMAGE_PATH)));
	}
}
