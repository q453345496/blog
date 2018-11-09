package com.xian.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.custom()
				.setUserAgent("Mozilla/4.0 (compatible;MSIE7.0;WindowsNT5.1;360SE)")//
				.build();
		for (int k = 0; k < 10; k++) {
			RequestConfig requestConfig = RequestConfig.custom()//
					.setSocketTimeout(10 * 1000)//
					.setConnectTimeout(10 * 1000)//
					.setConnectionRequestTimeout(10 * 1000)
					//				.setProxy(new HttpHost("120.92.74.189", 3128))//
					.build();
			for (int i = 0; i < 1; i++) {
				HttpGet httpget = new HttpGet("http://192.168.15.167:9700/ums/app_version.json");
				httpget.setConfig(requestConfig);
				CloseableHttpResponse res = httpclient.execute(httpget);
				System.out.println(EntityUtils.toString(res.getEntity()));
				res.close();

				httpget = new HttpGet("http://192.168.15.153:8088/iepg/app_version.json");
				httpget.setConfig(requestConfig);
				res = httpclient.execute(httpget);
				System.out.println(EntityUtils.toString(res.getEntity()));
				res.close();
			}
		}
		httpclient.close();
	}

}
