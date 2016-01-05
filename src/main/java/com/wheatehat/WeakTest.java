package com.wheatehat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Slf4j
public class WeakTest {
	public static String WEAK_PASSWORD_FILE = "weakpassword.txt";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String content = IOUtils.toString(WeakTest.class.getResource(WEAK_PASSWORD_FILE));
		
		BufferedReader br = IOUtils.toBufferedReader(new InputStreamReader(WeakTest.class.getResourceAsStream(WEAK_PASSWORD_FILE)));
		HttpClient client = new HttpClient();
		while ((content = br.readLine()) != null) {
			log.info(content);
			dologin();
		}
		
	}
	
	private static void dologin() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClients = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://ems.mygzb.com/login/check-login");// get
		CloseableHttpResponse response = httpClients.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity);
		log.info(html);
	}
	
}
