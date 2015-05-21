package com.team.gaoguangjin.soa.websocket.demo;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @ClassName:WebSocketTest.java
 * @Description: websocket学习。需要maven引入javax.websocket-api.jar
 * @author gaoguangjin
 * @Date 2015-5-13 下午2:28:06
 */
@ServerEndpoint("/hello/websocket")
public class WebSocketTest {
	
	// 当服务器接收到客户端发送的消息时所调用的方法。
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		// Print the client message for testing purposes
		System.out.println("Received: " + message);
		
		// Send the first message to the client
		session.getBasicRemote().sendText("This is the first server message");
		
		// Send 3 messages to the client every 5 seconds
		int sentMessages = 0;
		while (sentMessages < 3) {
			Thread.sleep(5000);
			session.getBasicRemote().sendText("This is an intermediate server message. Count: " + sentMessages);
			sentMessages++;
		}
		
		// Send a final message to the client
		session.getBasicRemote().sendText("This is the last server message");
	}
	
	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}
	
	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}
}

// @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端。
// 注解的值将被用于监听用户连接的终端访问URL地址。

// onOpen 和 onClose 方法分别被@OnOpen和@OnClose 所注解。
// 这两个注解的作用不言自明：他们定义了当一个新用户连接和断开的时候所调用的方法。

// onMessage 方法被@OnMessage所注解。这个注解定义了当服务器接收到客户端发送的消息时所调用的方法。
// 注意：这个方法可能包含一个javax.websocket.Session可选参数（在我们的例子里就是session参数）。如果有这个参数，容器将会把当前发送消息客户端的连接Session注入进去。
