package com.team.gaoguangjin.javabase.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
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
//		 init();//异步实现
		// demo();
		 demo1();

//		brownsower();// 浏览器上面输入 http://localhost:34/
	}
	
	private static void brownsower() throws IOException {
		ServerSocket server = new ServerSocket(34);// 默认端口
		while (true) {
			Socket client = server.accept();
			// BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
			PrintStream ps = new PrintStream(client.getOutputStream());
			// System.out.println(br.readLine());
			String errorMessage = "123";
			ps.print(errorMessage);
			client.close();
		}
		
	}
	
	public static void init() throws IOException {
		final ServerSocket server = new ServerSocket(34);// 默认端口
		
		// 同步改成异步去处理。多个客户端连接同一个服务端
		
		while (true) {
			final Socket client = server.accept();
			new Thread() {
				public void run() {
					try {
						doClient(client);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			
		}
	}
	
	protected static void doClient(Socket client) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
		// PrintStream ps = new PrintStream(client.getOutputStream());
		Writer writer = new OutputStreamWriter(client.getOutputStream());
		String clientMessage = "";
		while (!"quit".equals((clientMessage = br.readLine()))) {// 因为socket是InputStream是阻塞式的！
			System.out.println(clientMessage);
			writer.write("你好");
		}
		client.close();
	}
	
	private static void demo1() throws IOException {
		final ServerSocket server = new ServerSocket(34);
		Socket client = server.accept();// 要在循环里面，一直等待socke建立连接
		while (true) {
			System.out.println("=====");
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
			PrintStream ps = new PrintStream(client.getOutputStream());
			String clientMessage = br.readLine();
			System.out.println(clientMessage);
			ps.write("aaabc \r\n".getBytes());
			ps.flush();//
			if ("quit".equals(clientMessage)) {
				client.close();
				break;
			}
		}
	}
	
	private static void demo() throws IOException {
		final ServerSocket server = new ServerSocket(34);
		// 启动一个服务器的线程，一直监听 server.accept()
		new Thread() {
			public void run() {
				while (true) {
					Socket client = null;
					try {
						client = server.accept();// 要在循环里面，一直等待socke建立连接
						System.out.println("==============" + client.getRemoteSocketAddress() + "开始=======");
						BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
						PrintStream ps = new PrintStream(client.getOutputStream());
						String clientMessage = br.readLine();
						System.out.println(clientMessage);
						ps.write("aaabc \r\n".getBytes());
						ps.flush();//
						
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
