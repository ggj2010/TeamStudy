package com.team.gaoguangjin.soa.integration;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/**
 * @ClassName:WebSocketFirst.java
 * @Description:
 * @author gaoguangjin
 * @Date 2015-5-27 下午3:05:50
 */
@Slf4j
@ServerEndpoint("/integration")
public class WebSocketFirst {
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		log.info("【1】websocket接受数据" + message);
		// 启动生产者，处理数据
		
		final String messages = "【1】" + message;
		
		// 启动消费者 接受数据
		
		// new Thread() {
		// public void run() {
		initproducer(messages);
		// }
		// }.start();
		
		String backMessage = initcommuser();
		
		session.getBasicRemote().sendText("websoket=>生产者=》消费者=》返回到websocket数据：" + backMessage);
		
		// session.close();
	}
	
	private String initcommuser() {
		ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接
		Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接
		javax.jms.Session session;// Session： 一个发送或接收消息的线程
		Destination destination;// Destination ：消息的目的地;消息发送给谁.
		MessageConsumer consumer; // 消费者，消息接收者
		try {
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
					"tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			// 创建消息的Destination
			Queue queue = new ActiveMQQueue("gaoQueue");
			
			session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			// 接受者
			consumer = session.createConsumer(queue);
			
			connection.start();
			Message recvMessage = consumer.receive();
			return "【4】消费者获取到处理之后的数据：" + ((TextMessage) recvMessage).getText();
		} catch (JMSException e) {
			log.info("" + e.getMessage());
		}
		return null;
		
	}
	
	private void initproducer(String messages) {
		ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接
		Connection connection = null; // Connection ：JMS 客户端到JMS Provider 的连接
		javax.jms.Session session;// Session： 一个发送或接收消息的线程
		Destination destination;// Destination ：消息的目的地;消息发送给谁.
		MessageProducer producer;// MessageProducer：消息发送者
		TextMessage message;// Message 消息
		
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");// tcp开头的地址
		
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接，打开session
			session = connection.createSession(Boolean.TRUE, javax.jms.Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("gaoQueue");// 代码创建queue
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 构造消息，此处写死，项目就是参数，或者方法获取
			
			TextMessage smessage = session.createTextMessage("【2】利用activemq当生产者发送数据：" + messages);
			// 发送消息到目的地方
			producer.send(smessage);
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
		
	}
	
	@OnOpen
	public void onOpen(Session session) {
		log.info("Client connected ");
	}
	
	@OnClose
	public void onClose() {
		log.info("Connection closed");
	}
}
