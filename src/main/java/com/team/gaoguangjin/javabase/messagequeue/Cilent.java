package com.team.gaoguangjin.javabase.messagequeue;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 模拟客户端
 * @author lyw
 * 
 */
public class Cilent extends Thread {
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				// 发送10次请求
				Socket s = new Socket("127.0.0.1", 9999);
				OutputStream out = s.getOutputStream();
				out.write("123456789123456789".getBytes());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 10个线程请求10次
		for (int i = 0; i < 10; i++) {
			Thread t = new Cilent();
			t.start();
		}
	}
}
