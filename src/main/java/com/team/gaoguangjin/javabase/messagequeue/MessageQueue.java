package com.team.gaoguangjin.javabase.messagequeue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消息队列
 * @author lyw
 * 
 */
public class MessageQueue {
	/**
	 * 初始大小
	 */
	public static final int MAX_SIZE = 5;
	/**
	 * 消息队列
	 */
	public static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(MAX_SIZE);
}
