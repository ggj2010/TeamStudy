package com.team.youdao.controller.redis;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;

/**
 * @ClassName:Subscriber.java
 * @Description: JedisPubSub是抽象类，pub/sub模式必须要手动实现JedisPubSub里面的某些方法
 * @author gaoguangjin
 * @Date 2015-5-14 下午1:07:21
 */
@Slf4j
public class JedisPubSubDemo extends JedisPubSub {
	private HttpServletResponse response;
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void onMessage(String channel, String message) {
		
		if (message.equals("quit")) {
			this.unsubscribe(channel);
		} else {
			PrintWriter out = null;
			try {
				HttpServletResponse res = response;
				out = res.getWriter();
				out.write(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				out.flush();
				out.close();
				this.unsubscribe(channel);
				log.info("客户端subscribe连接被关闭");
			}
			
			log.info("Message received. Channel: {}, Msg: {}", channel, message);
		}
	}
	
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("1");
	}
	
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("2");
	}
	
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("3");
	}
	
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		System.out.println("4");
	}
	
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out.println("5");
	}
}