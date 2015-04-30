package com.team.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName:HttpURLConnectionUtil.java
 * @Description: HttpURL公共api
 * @author gaoguangjin
 * @Date 2015-4-14 下午2:09:34
 */
@Slf4j
public class HttpURLConnectionUtil {
	
	public static void main(String[] args) throws IOException {
		// test();
		test2();
	}
	
	private static void test2() throws IOException {
		
		String param = "code_type=1&mobile_no=18638217959&randomCode=ffn7";
		String url = "http://www.sd-express.net/main/public/send/msg/code.html";
		String callBack = getURLConnectionMessage(url, true, param);
		System.out.println(callBack);
		
	}
	
	/*
	 * 'main方法代码无论post 还是get都可以，但是web项目嗲用只能用get方法
	 */
	public static void test() throws IOException {
		// 中文转英文
		// String str = "from=zh&query=您好&simple_means_flag=3&to=en&transtype=realtime";
		// String url = "http://fanyi.baidu.com/v2transapi";
		// String callBack = getURLConnectionMessage(url, true, str);
		
		// String str = "from=en&query=test&simple_means_flag=3&to=zh&transtype=realtime";
		// String url = "http://fanyi.baidu.com/v2transapi";
		// String callBack = getURLConnectionMessage(url, true, str);
		
		String message = "aa学习";
		// if (message != null)
		// message = StringUtil.stringToUnicode(message);
		
		String str = "from=zh&query=" + message + "&simple_means_flag=3&to=en&transtype=realtime";
		// String str = "from=en&query=test&simple_means_flag=3&to=zh&transtype=realtime";
		// String url = "http://fanyi.baidu.com/v2transapi?" + str;
		String url = "http://fanyi.baidu.com/v2transapi";
		String callBack = getURLConnectionMessage(url, true, str);
		System.out.println(callBack);
		JSONObject transResultObject = (JSONObject) JSONObject.parseObject(callBack).get("trans_result");
		JSONObject dataObject = (JSONObject) ((JSONArray) transResultObject.get("data")).get(0);
		String zh = dataObject.getString("dst");
		System.out.println(zh);
		
	}
	
	/**
	 * @Description: 模拟http连接公共api
	 * @param obj
	 * @return:String 第一个参数为url 第二参数为是否为post提交 如果是post提交 是否带有参数
	 * @throws IOException
	 */
	public static String getURLConnectionMessage(Object... obj) throws IOException {
		String postData = null;
		String urlstr = null;
		boolean isPost = false;
		if (obj.length == 2 || obj.length == 3) {
			urlstr = (String) obj[0];
			isPost = (Boolean) obj[1];
			if (isPost)
				postData = (String) obj[2];
		} else {
			log.error("getURLConnectionMessage()方法调用时候，传的参数个数不在规定范围内");
			return null;
		}
		
		URL url = new URL(urlstr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		// 是否为post提交
		connection.setDoOutput(isPost);
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		connection.setRequestProperty("Host", "fanyi.baidu.com");
		connection.setRequestProperty("Referer", "http://fanyi.baidu.com/#auto/zh/");
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		connection.setRequestProperty("Accept-Language", "zh-cn");
		connection.connect();
		
		if (isPost) {
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(postData);
			out.flush();// out 必须要调用flush,输出流的缓冲，不然参数是无法传过去的
		}
		
		if (!isPost)
			connection.disconnect();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		
		StringBuilder stringBuilder = new StringBuilder();
		String callBackStr = null;
		while ((callBackStr = br.readLine()) != null) {
			stringBuilder.append(callBackStr);
		}
		br.close();
		connection.disconnect();
		
		return stringBuilder.toString();
	}
}
