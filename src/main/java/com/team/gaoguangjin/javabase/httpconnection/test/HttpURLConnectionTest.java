package com.team.gaoguangjin.javabase.httpconnection.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionTest {
	public static void main(String[] args) {
		try {
			URL url = new URL(
					"https://github.com/ggj2010/TeamStudy/blob/user/gaoguangjin/main/src/main/java/com/team/hadoop/local/worldcount/WorldCountNew.java");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
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
					sb.append(str + "\r\n");
				}
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("e:wordcount.html")));
			bw.write(sb.toString());
			System.out.println(sb.toString());
			bw.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
