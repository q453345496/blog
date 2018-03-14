package com.xian.blog.common.ueditor;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.xian.blog.constants.UEditorConstant;
import com.xian.blog.util.FtpAdapter;

/**
 * Date:2016年8月7日下午9:34:26
 * 
 */
public final class UEditorUtil {

	private static final Logger LOG = LoggerFactory.getLogger(UEditorUtil.class);

	public static String getTitle(String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis())//
				.append((Math.random() + "").substring(2, 8))//随机六位
				.append(suffix);
		return sb.toString();
	}

	public static String getSavepPath(String id, String title, String path) {
		StringBuilder sb = new StringBuilder();
		sb.append("blog/").append(id)//
				.append("/").append(path)//
				.append("/").append(title);//
		return sb.toString();
	}

	public static UEditorResult upload(String id, MultipartFile upfile, String path) {
		if (upfile == null) {
			return UEditorResult.errorResult("empty content");
		}

		String originalFilename = upfile.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
		String title = getTitle(suffix);
		String remote = UEditorUtil.getSavepPath(id, title, path);

		FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
		try {
			ftpAdapter.upload(upfile.getInputStream(), remote);

			return new UEditorUploadResult(title, originalFilename, remote);

		} catch (Exception e) {
			LOG.error(String.format("上传异常[file=%s,path=%s]", upfile.getOriginalFilename(), path), e);
			return UEditorUploadResult.errorResult("upload error");
		} finally {
			FtpAdapter.closeFtpAdapter(ftpAdapter);
		}
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
