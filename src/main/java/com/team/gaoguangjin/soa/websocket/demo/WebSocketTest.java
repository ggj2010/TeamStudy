package com.team.gaoguangjin.soa.websocket.demo;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:WebSocketTest.java
 * @Description: websocket学习。toamct7、lib下面的这个websocket-api.jar
 * @see:需要jdk7环境
 * @author gaoguangjin
 * @Date 2015-5-13 下午2:28:06
 */
@Slf4j
@ServerEndpoint("/websocket")
public class WebSocketTest {
	
	// websocket 是线程安全的，和servlet不一样，全局变量 每连接一次，都是一个新的.所以如果共享全局变量那就加 static
	private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();
	
	// 当前台页面ws.send()方法时候，后台就开始受到信息，然后反馈回去
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		
		log.info("" + sessionMap.size());
		// Print the client message for testing purposes
		log.info("Received: " + message);
		
		session.getBasicRemote().sendText("我说：" + message);
		
		for (Iterator iterator = sessionMap.keySet().iterator(); iterator.hasNext();) {
			Object sessionId = iterator.next();
			Session sessions = sessionMap.get(sessionId.toString());
			if (session != sessions) {
				if (sessions.isOpen())
					sessions.getBasicRemote().sendText("群聊：" + message);
			}
		}
		
		// session.close();
	}
	
	@OnOpen
	public void onOpen(Session session) {
		String id = session.getId();
		if (null == sessionMap.get(id)) {
			sessionMap.put(id, session);
		}
		log.info("Client connected " + id + ":" + session);
	}
	
	@OnClose
	public void onClose() {
		log.info("Connection closed");
	}
}

// @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端。
// 注解的值将被用于监听用户连接的终端访问URL地址。

// onOpen 和 onClose 方法分别被@OnOpen和@OnClose 所注解。
// 这两个注解的作用不言自明：他们定义了当一个新用户连接和断开的时候所调用的方法。

// onMessage 方法被@OnMessage所注解。这个注解定义了当服务器接收到客户端发送的消息时所调用的方法。
// 注意：这个方法可能包含一个javax.websocket.Session可选参数（在我们的例子里就是session参数）。如果有这个参数，容器将会把当前发送消息客户端的连接Session注入进去。
