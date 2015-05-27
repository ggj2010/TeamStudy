package com.team.gaoguangjin.soa.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @ClassName:Demo.java
 * @Description: JMS是一种与厂商无关的 API，用来访问消息收发系统 .类似于JDBC。
 * @author gaoguangjin
 * @Date 2015-5-6 上午11:10:36
 */
public class Sender {
	private static final int SEND_NUMBER = 2;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接
		Connection connection = null; // Connection ：JMS 客户端到JMS Provider 的连接
		Session session;// Session： 一个发送或接收消息的线程
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
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("gaoQueue");// 代码创建queue
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 构造消息，此处写死，项目就是参数，或者方法获取
			sendMessage(session, producer);
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
	
	public static void sendMessage(Session session, MessageProducer producer) throws Exception {
		for (int i = 1; i <= SEND_NUMBER; i++) {
			TextMessage message = session.createTextMessage("ActiveMq 发送的消息" + i);
			// 发送消息到目的地方
			System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
			producer.send(message);
		}
	}
}
