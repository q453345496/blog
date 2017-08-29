package com.xian.blog.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.junit.Test;

import com.xian.blog.util.FtpAdapter;

/**
 * Date:2016年8月7日下午11:10:25
 * 
 */
public class FtpAdapterTest {
	@Test
	public void testUpload() {
		FtpAdapter ftpAdapter = new FtpAdapter();
		try {
			ftpAdapter.connect();
			InputStream is = new FileInputStream("C:\\Users\\xian\\Desktop\\QQ图片20150809225329.png");
			ftpAdapter.upload(is, "0/test/01/20150809225329.png");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FtpAdapter.closeFtpAdapter(ftpAdapter);
		}
	}

	// @Test
	public void testList() {
		FtpAdapter ftpAdapter = new FtpAdapter();
		try {
			ftpAdapter.connect();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FtpAdapter.closeFtpAdapter(ftpAdapter);
		}
	}

}
