package com.team.hadoop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetClassContent {
	public static String getClassContent(String className) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL("https://github.com/ggj2010/TeamStudy/blob/user/gaoguangjin/main/src/main/java/com/team/hadoop/local/" + className);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String str = "";
			boolean flagBody = true;
			boolean flagFile = true;
			while ((str = br.readLine()) != null) {
				if (str.contains("<body") && flagBody) {
					sb.append(str + "\r\n");
					flagBody = false;
				}
				if (str.contains("class=\"file\"") && flagFile) {
					flagFile = false;
				}
				
				if (flagBody || !flagFile) {
					if (!str.contains(".js"))// 跨域问题
						sb.append(str + "\r\n");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
