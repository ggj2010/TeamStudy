package com.team.gaoguangjin.javabase.messagequeue;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * 模拟服务器端
 * @author lyw
 * 
 */
public class Server {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// 端口9999
		ServerSocket ser = new ServerSocket(9999);
		// 处理消息线程
		Thread handle = new Handle();
		handle.start();
		
		while (true) {
			// 等待连接
			Socket s = ser.accept();
			// 获取输入流
			InputStream in = s.getInputStream();
			InputStreamReader r = new InputStreamReader(in);
			
			// 请求读取结果
			StringBuffer sb = new StringBuffer();
			char[] cbuf = new char[3];
			// 循环读取数据
			while (true) {
				int length = r.read(cbuf);
				if (length < 1) {
					break;
				}
				// 拼接每次读取结果
				sb.append(new String(Arrays.copyOf(cbuf, length)));
			}
			
			if (MessageQueue.queue.size() == MessageQueue.MAX_SIZE) {
				System.out.println("队列已满，等待添加..");
			}
			// 堵塞添加队列消息
			MessageQueue.queue.put(sb.toString());
			r.close();
			in.close();
		}
		
	}
	
}
