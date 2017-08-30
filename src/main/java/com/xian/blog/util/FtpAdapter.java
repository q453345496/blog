package com.xian.blog.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Date:2016年8月4日下午10:17:20
 * 
 */
public class FtpAdapter {
	private static final boolean AT_HOME = false;
	private static final String HOSTNAME = AT_HOME ? "127.0.0.1" : "192.168.15.165";
	private static final int PORT = AT_HOME ? 2121 : 21;
	private static final String USER_NAME = "ftper";
	private static final String PASSWORD = "ftper";
	private static final String ROOT_PATH = "xian_test";
	private FTPClient ftpClient = null;

	public FtpAdapter() {
	}

	public FTPClient connect() throws SocketException, IOException {
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(HOSTNAME, PORT);
			ftpClient.login(USER_NAME, PASSWORD);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.enterLocalPassiveMode();
			ftpClient.setBufferSize(1024);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				throw new IOException(String.format("FTP server refused connection:[%d]", reply));
			}
			if (!ftpClient.changeWorkingDirectory(ROOT_PATH)) {
				if (ftpClient.makeDirectory(ROOT_PATH)) {
					ftpClient.changeWorkingDirectory(ROOT_PATH);
				} else {
					throw new IOException("makeDirectory fail");
				}
			}
		} catch (Exception e) {
			closeFtpClient(ftpClient);
			ftpClient = null;
			throw new IOException("ftp连接异常", e);
		}
		return ftpClient;
	}

	public static void closeFtpAdapter(FtpAdapter ftpAdapter) {
		if (ftpAdapter == null) {
			return;
		}
		closeFtpClient(ftpAdapter.getFtpClient());

	}

	private static void closeFtpClient(FTPClient ftpClient) {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void upload(InputStream is, String remote) throws SocketException, IOException {
		try {
			int lastIndexOf = remote.lastIndexOf("/");
			if (lastIndexOf > -1) {
				String name = remote.substring(lastIndexOf + 1);
				String path = remote.substring(0, lastIndexOf);
				upload(is, name, path);
			} else {
				ftpClient.storeFile(new String(remote.getBytes("UTF-8"), "iso-8859-1"), is);
			}
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	public void upload(InputStream is, String remote, String path) throws SocketException, IOException {
		try {
			if (path != null) {
				String[] dirNames = path.split("/");
				for (String dir : dirNames) {
					if (!ftpClient.changeWorkingDirectory(dir)) {
						if (ftpClient.makeDirectory(dir)) {
							ftpClient.changeWorkingDirectory(dir);
						} else {
							throw new IOException("makeDirectory fail");
						}
					}
				}
			}
			ftpClient.storeFile(new String(remote.getBytes("UTF-8"), "iso-8859-1"), is);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	public boolean rename(String from, String to) throws IOException {
		return ftpClient.rename(from, to);
	}

	public boolean deleteFile(String pathname) throws IOException {
		return ftpClient.deleteFile(pathname);
	}

	public boolean removeDirectory(String pathname) throws IOException {
		return ftpClient.removeDirectory(pathname);
	}

	public boolean makeDirectory(String pathname) throws IOException {
		return ftpClient.makeDirectory(pathname);
	}

	public FTPFile[] listFiles() throws IOException {
		return ftpClient.listFiles();
	}

	public FTPFile[] listFiles(String pathname) throws IOException {
		return ftpClient.listFiles(pathname);
	}

	public FTPFile[] listFiles(String pathname, FTPFileFilter filter) throws IOException {
		return ftpClient.listFiles(pathname, filter);
	}

	public FTPFile[] listDirectories() throws IOException {
		return ftpClient.listDirectories();
	}

	public FTPFile[] listDirectories(String parent) throws IOException {
		return ftpClient.listDirectories(parent);
	}

	public boolean changeWorkingDirectory(String pathname) throws IOException {
		return ftpClient.changeWorkingDirectory(pathname);
	}

	public boolean changeToParentDirectory() throws IOException {
		return ftpClient.changeToParentDirectory();
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

}
