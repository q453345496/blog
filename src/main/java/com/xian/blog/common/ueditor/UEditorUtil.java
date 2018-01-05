package com.xian.blog.common.ueditor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
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
				.append((Math.random() + "").substring(2, 8))//
				.append(suffix);
		return sb.toString();
	}

	public static String getSavepPath(String title, String path) {
		Date now = new Date();
		String format = new SimpleDateFormat("yyyyMMdd").format(now);
		StringBuilder sb = new StringBuilder();
		sb.append(path)//
				.append("/").append(format)//
				.append("/").append(title);//
		// .append(suffix);
		return sb.toString();
	}

	public static UEditorResult upload(MultipartFile upfile, String path) {
		if (upfile == null) {
			return UEditorResult.errorResult("empty content");
		}

		String originalFilename = upfile.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
		String title = getTitle(suffix);
		String remote = UEditorUtil.getSavepPath(title, path);

		FtpAdapter ftpAdapter = new FtpAdapter();
		try {
			ftpAdapter.connect();
			ftpAdapter.upload(upfile.getInputStream(), remote);

			return new UEditorUploadResult(title, originalFilename, remote);

		} catch (Exception e) {
			LOG.error(String.format("上传异常[file=%s,path=%s]", upfile.getOriginalFilename(), path), e);
			return UEditorUploadResult.errorResult("upload error");
		} finally {
			FtpAdapter.closeFtpAdapter(ftpAdapter);
		}
	}

	public static UEditorListResult listFiles(String path, int start, int size) {
		UEditorListResult uEditorListResult = new UEditorListResult();
		uEditorListResult.setStart(start);
		FtpAdapter ftpAdapter = new FtpAdapter();
		try {
			ftpAdapter.connect();
			List<UEditorResult> list = new ArrayList<>();
			int currentCount = 0;
			currentCount = listDir(list, ftpAdapter, path, start, start + size, currentCount);

			uEditorListResult.setState(UEditorConstant.SUCCESS);
			uEditorListResult.setTotal(currentCount);
			uEditorListResult.setList(list);
		} catch (IOException e) {
			LOG.error("listFiles ERROR", e);
			uEditorListResult.setState(e.getMessage());
		} finally {
			FtpAdapter.closeFtpAdapter(ftpAdapter);
		}
		return uEditorListResult;
	}

	private static int listDir(List<UEditorResult> list, FtpAdapter ftpAdapter, String dir, int first, int last,
			int currentCount) throws IOException {
		int tmp = currentCount;
		FTPFile[] files = ftpAdapter.listFiles(dir);
		for (FTPFile ftpFile : files) {
			if (ftpFile.isDirectory()) {
				tmp = listDir(list, ftpAdapter, dir + "/" + ftpFile.getName(), first, last, tmp);
			} else {
				if (tmp >= last || tmp < first) {
					tmp++;
					continue;
				}
				if (tmp >= first && tmp < last) {
					UEditorResult result = new UEditorResult(dir + "/" + ftpFile.getName());
					list.add(result);
					tmp++;
				}
			}
		}
		return tmp;
	}

	public static UEditorCatchResult capture(String[] sources) {
		UEditorCatchResult result = new UEditorCatchResult();
		result.setState(UEditorConstant.SUCCESS);
		List<UEditorResult> list = new ArrayList<>();
		for (String source : sources) {
			list.add(captureRemoteData(source));
		}
		result.setList(list);
		return result;

	}

	private static UEditorResult captureRemoteData(String sourceUrl) {
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
			String savepPath = getSavepPath(getTitle(suffix), UEditorConstant.CATCH_IMAGE_PATH);

			FtpAdapter ftpAdapter = new FtpAdapter();
			try {
				ftpAdapter.connect();
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
		System.out.println((getSavepPath(getTitle(".java"), "image")));
	}
}
