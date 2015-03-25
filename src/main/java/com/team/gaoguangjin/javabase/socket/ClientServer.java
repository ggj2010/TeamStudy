package com.team.gaoguangjin.javabase.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ClientServer.java
 * @Description:
 * @author gaoguangjin
 * @Date 2015-3-19 上午9:06:52
 */
@Slf4j
public class ClientServer {
	public static void main(String[] args) throws IOException {
		ServiceServer.init();
		
		test();
	}
	
	private static void test() {
		try {
			Socket client = new Socket("127.0.0.1", 34);
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
			BufferedWriter bo = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bo.write("这是测试");
			bo.flush();
			bo.close();// socket编程连接之后 输出流一定要关掉
			
			System.out.println("结束");
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
