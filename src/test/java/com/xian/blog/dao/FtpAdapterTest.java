package com.xian.blog.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Test;

import com.xian.blog.util.FtpAdapter;

/**
 * Date:2016年8月7日下午11:10:25
 * 
 */
public class FtpAdapterTest {
	@Test
	public void testUpload() {
		FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
		try {
			InputStream is = new FileInputStream("C:\\Users\\xian\\Desktop\\QQ图片20150809225329.png");
			ftpAdapter.upload(is, "0/test/01/20150809225329.png");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FtpAdapter.close(ftpAdapter);
		}
	}

	@Test
	public void testList() {
		FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
		try {
			Arrays.asList(ftpAdapter.listDirectories()).stream().forEach(f -> System.out.println(f.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		FtpAdapter.close(ftpAdapter);
	}

}
