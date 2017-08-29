package com.xian.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Date:2016年8月23日下午9:14:17
 * 
 */
public class HttpTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
		HttpGet httpget = new HttpGet("http://localhost:8080/blog/index");
		httpget.setConfig(requestConfig);
		// CloseableHttpResponse response = httpclient.execute(httpget);
		// try {
		// System.out.println(response.getStatusLine());
		URIBuilder weiXinUri = new URIBuilder();
		weiXinUri.setPath("cgi-bin/qrcode/create");

		System.out.println(weiXinUri);
		// } finally {
		// try {
		// // response.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
	}

}
