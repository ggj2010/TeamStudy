package com.team.gaoguangjin.javabase.httpconnection.HttpClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName:HttpClientTest.java
 * @Description: httpClient是封装了HttpUrlConnection
 * @author gaoguangjin
 * @Date 2015-4-27 上午8:58:00
 */
@Slf4j
public class HttpClientTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// demo();
		// cookie();// httclient带cookie
		
		pageGetTest();// 网页抓取两种方式输出性能比较
	}
	
	// 1. 创建HttpClient对象。
	// 2. 创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。
	// 3. 如果需要发送请求参数，可调用HttpGet、HttpPost共同的setParams(HetpParams params)方法来添加请求参数；对于HttpPost对象而言，也可调用setEntity(HttpEntity
	// entity)方法来设置请求参数。
	// 4. 调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse。
	// 5. 调用HttpResponse的getAllHeaders()、getHeaders(String
	// name)等方法可获取服务器的响应头；调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。
	// 6. 释放连接。无论执行方法是否成功，都必须释放连接
	
	private static void demo() throws ClientProtocolException, IOException {
		// 得到HttpClient对象,不同版本的写法
		HttpClient httpClient = new HttpClient();
		HttpClient httpClient2 = new HttpClient();
		HttpMethodBase getMethod = new GetMethod("http://www.baidu.com/");
		HttpMethodBase postMethod = new PostMethod("http://www.qk365.com/");
		// 执行getMethod,返回状态,以刺激执行一个
		int statusCode1 = httpClient.executeMethod(getMethod);
		testByMethod(getMethod);
		
		int statusCode2 = httpClient2.executeMethod(postMethod);
		testByMethod(postMethod);
		
		// 创建默认的httpClient实例.
		// CloseableHttpClient httpClients = HttpClients.createDefault();
		//
		// HttpPost httpPost = new HttpPost("http://www.qk365.com/");// post提交
		// HttpGet httpGet = new HttpGet("http://www.qk365.com/");// get
		//
		// httpClients.execute(httpPost);
		// httpClients.execute(httpGet);
		
	}
	
	private static void testByMethod(HttpMethodBase method) {
		try {
			InputStream inputStream = method.getResponseBodyAsStream();// 输入流
			if ("GET".equals(method.getName())) {
				OutputStream outPut = new FileOutputStream("d:qinke.html");
				IOUtils.copy(inputStream, outPut);// 利用工具类拷贝
			} else {
				String html = IOUtils.toString(inputStream);
				log.info(html);
			}
		} catch (IOException e) {
			log.error("" + e.getLocalizedMessage());
		}
		finally {
			// 释放连接
			method.releaseConnection();
		}
	}
	
	private static void cookie() {
		
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod("http://www.sd-express.net/main/public/register.html");
		PostMethod postMethod2 = new PostMethod("http://localhost:8080/TeamStudy/youdao/httpclient2.do");
		int stats = 0;
		try {
			stats = client.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		postMethod.releaseConnection();
		
		try {
			client.executeMethod(postMethod2);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
		Cookie[] cookies = cookiespec.match("http://www.sd-express.net/main/public/register.html", 80/* 端口 */, "/", false, client.getState()
				.getCookies());
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + "##" + cookie.getValue());
		}
		
		// HttpMethod method = null;
		// String encode = "utf-8";// 页面编码,按访问页面改动
		// String referer = "http://域名";// http://www.163.com
		// method = new GetMethod("url2");// 后续操作
		// method.getParams().setParameter("http.useragent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows 98)");
		// method.setRequestHeader("Referer", referer);
		//
		// client.getParams().setContentCharset(encode);
		// client.getParams().setSoTimeout(300000);
		// client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(10, true));
		//
		// try {
		// stats = client.executeMethod(method);
		// } catch (HttpException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// if (stats == HttpStatus.SC_OK) {
		// System.out.println("提交成功!");
		//
		// }
		
	}
	
	private static String getContentByHttpClient(String url) throws HttpException, IOException {
		CloseableHttpClient httpClients = HttpClients.createDefault();
		// DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);// get
		CloseableHttpResponse response = httpClients.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity);
		log.info(html);
		return html;
	}
	
	private static String getContentByCommonsHttpClient(String url) throws HttpException, IOException {
		// 获取到的是输入流
		HttpClient httpClient = new HttpClient();
		HttpMethodBase postMethod = new PostMethod(url);
		int statusCode1 = httpClient.executeMethod(postMethod);
		InputStream inputStream = postMethod.getResponseBodyAsStream();
		String html = IOUtils.toString(inputStream);
		log.info(html);
		return html;
	}
	
	private static void pageGetTest() throws HttpException, IOException {
		try {
			long time = System.currentTimeMillis();
			for (int i = 0; i < 10; i++)
				getContentByHttpClient("http://www.tuicool.com/");// 这个性能好点
			// getContentByCommonsHttpClient("http://www.tuicool.com/");
			long endtime = System.currentTimeMillis();
			System.out.println((endtime - time));
		} catch (Exception e) {
		}
		// 创建默认的httpClient实例.
		// String[] contents = content.split("<a href=\"");
	}
}
