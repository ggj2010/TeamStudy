package com.team.gaoguangjin.javabase.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ServiceServer.java
 * @Description: socket服务端
 * @author gaoguangjin
 * @Date 2015-3-19 上午9:07:30
 */
@Slf4j
public class ServiceServer {
	public static void main(String[] args) throws UnknownHostException, IOException {
		init();
	}
	
	public static void init() throws IOException {
		final ServerSocket server = new ServerSocket(34);
		
		// 启动一个服务器的线程，一直监听 server.accept()
		new Thread() {
			public void run() {
				while (true) {
					System.out.println("==============循环开始=======");
					Socket client = null;
					try {
						client = server.accept();// 要在循环里面，一直等待socke建立连接
						BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
						PrintStream ps = new PrintStream(client.getOutputStream());
						System.out.println(br.readLine());
						ps.write("aaabc \r\n".getBytes());
						br.close();
						ps.close();// 输出流一定要关掉;
					
						System.out.println("==============循环结束=======");
					} catch (Exception e) {
					}
					finally {
						try {
							client.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
		
	}
}
