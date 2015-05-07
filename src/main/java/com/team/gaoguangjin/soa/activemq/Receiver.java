package com.team.gaoguangjin.soa.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

/**
 * @ClassName:Receiver.java
 * @Description: activeMq消息接收端.我们打开activeMQ的管理控制台，然后手动去发送消息！
 * @see:先运行 Receiver，目的是注册这个客户端（好让消息中间件服务器为这个客户保存消息），然后关了这个 Receiver， 启动 Sender，发现消息，再启动 Receiver 就可以收到离线消息。
 * @author gaoguangjin
 * @Date 2015-5-6 下午12:47:00
 */
@Slf4j
public class Receiver {
	public static void main(String[] args) {
		
		// receiveByTopic("a");// topic 发布和订阅模式
		// receiveByTopic("b");// topic 发布和订阅模式
		
		// receiverByQueue()这个方法调用两次，但是获取的信息只有最新注册的那一条，因为这是点对点方式
		receiverByQueue();// 点对点的方式
		// receiverByQueue();// 点对点的方式
	}
	
	private static void receiverByQueue() {
		ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接
		Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接
		Session session;// Session： 一个发送或接收消息的线程
		Destination destination;// Destination ：消息的目的地;消息发送给谁.
		MessageConsumer consumer; // 消费者，消息接收者
		try {
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
					"tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			// 创建消息的Destination
			Queue queue = new ActiveMQQueue("gaoQueue");
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 接受者
			consumer = session.createConsumer(queue);
			
			connection.start();
			
			// testReceive(consumer);// 消息的消费者接收消息的第一种方式
			testListener(consumer);// 消息的消费者接收消息的第二种方式
		} catch (JMSException e) {
			log.info("" + e.getMessage());
		}
		
	}
	
	private static void receiveByTopic(String clientID) {
		ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接
		Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接
		Session session;// Session： 一个发送或接收消息的线程
		Destination destination;// Destination ：消息的目的地;消息发送给谁.
		MessageConsumer consumer; // 消费者，消息接收者
		
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
				"tcp://localhost:61616");
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// //clientID必须唯一
			connection.setClientID(clientID);
			// 获取操作连接,打开会话
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			
			// 创建账号新增topic
			Topic userAddTopic = new ActiveMQTopic("user.add.topic");
			// 创建账号变更topic
			
			// DurableSubscriber 持久订阅者。
			// 参数1：发送主题目的地，参数2：持久订阅者名字，参数3：消息过滤条件，参数4：是否只接收同一clientID的信息,默认false
			consumer = session.createDurableSubscriber(userAddTopic, "userAddTopic");
			
			connection.start();
			// testReceive(consumer);// 消息的消费者接收消息的第一种方式
			testListener(consumer);// 消息的消费者接收消息的第二种方式
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testReceive(MessageConsumer consumer) throws JMSException {
		Message recvMessage = consumer.receive();
		System.out.println(((TextMessage) recvMessage).getText());
		
	}
	
	/**
	 * @Description:消息的消费者接收消息可以采用两种方式：
	 * @see:1、consumer.receive() 或 consumer.receive(int timeout)；
	 * @see:2、注册一个MessageListener。
	 * @see:采用第一种方式，消息的接收者会一直等待下去，直到有消息到达，或者超时。
	 * @see:后一种方式会注册一个监听器，当有消息到达的时候，会回调它的onMessage()方法。
	 * @param userAdd
	 * @throws JMSException
	 * @return:void
	 */
	private static void testListener(MessageConsumer consumer) throws JMSException {
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				try {
					System.out.println("【testListener】：被调用");
					String userAdd = ((TextMessage) message).getText();
					System.out.println(userAdd);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
