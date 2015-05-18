package com.team.gaoguangjin.javabase.httpconnection.pachong;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName:SimplePaChong.java
 * @Description: 去网站爬去链接然后保存
 * @author gaoguangjin
 * @Date 2015-5-15 下午4:26:14
 */
@Slf4j
public class SimplePaChong {
	public static final String BEGIN_URL = "http://www.tuicool.com/articles/";
	// 推酷上面的 科技和数码栏目 pageNumber 0到20
	// http://www.tuicool.com/ah/0/20?lang=0//科技
	// http://www.tuicool.com/ah/101050000/20?lang=0//数码
	public static final String BEGIN_KJ_URL = "http://www.tuicool.com/ah/0/pageNumber?lang=0";
	public static final String BEGIN_SM_URL = "http://www.tuicool.com/ah/101050000/pageNumber?lang=0";
	
	public static final String KJ_PATH = "d:pachong/科技/";
	public static final String SM_PATH = "d:pachong/数码/";
	
	static Map<String, String> map = new HashMap<String, String>();
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		start();
		// http://www.tuicool.com/ah/0/20?lang=0//科技
		// http://www.tuicool.com/ah/101050000/20?lang=0//数码
	}
	
	private static void start() throws ClientProtocolException, IOException {
		for (int i = 0; i <= 20; i++) {
			praseHtml(BEGIN_KJ_URL.replace("pageNumber", "" + i), KJ_PATH + i, true);
			praseHtml(BEGIN_SM_URL.replace("pageNumber", "" + i), SM_PATH + i, true);
		}
		
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			praseHtml(iter.next(), "d:pachong/", false);
		}
		
	}
	
	/**
	 * @Description: isHomePage需要解析里面的内容。
	 * @param url
	 * @param isHomePage
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return:void
	 */
	private static void praseHtml(String url, String path, boolean isHomePage) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClients = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);// get
		CloseableHttpResponse response = httpClients.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity);
		long beginTime = System.currentTimeMillis();
		praseContent(html, path, isHomePage, url);
		long endTime = System.currentTimeMillis();
		log.info("解析主页链接耗费时间：" + (endTime - beginTime) + "ms");
		httpClients.close();
	}
	
	private static void praseContent(String html, String path, boolean isHomePage, String url) throws IOException {
		String title = "";
		if (isHomePage) {
			title = html.split("<title>")[1].split("</title>")[0].replaceAll(" ", "").replaceAll("\n", "") + ".html";
			putUrl(html);
		} else {
			title = map.get(url);
		}
		saveHtml(html, title, path); // 保存到本地
	}
	
	private static void putUrl(String html) {
		String[] bb = html.split("<a href=\"/articles/");
		// <a href="/articles/3Mbe63y" class="article-list-title" target="_blank" title="Nexpaq Modular Smartphone Case
		for (int i = 1; i < bb.length; i++) {
			String url = BEGIN_URL + bb[i].split("\"")[0];
			System.out.println();
			String title = bb[i].split("title=\"")[1].split("\"")[0] + ".html";
			title = title.replaceAll("\\/", "").replaceAll("\\*", "").replaceAll("\\?", "").replaceAll("\\|", "");
			map.put(url, title);
		}
	}
	
	private static void saveHtml(String html, String title, String path) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + title)));
		bw.write(html);
		bw.close();
	}
}
