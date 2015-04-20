package com.team.gaoguangjin.javabase.messagequeue;

/**
 * 消息处理线程
 * @author lyw
 * 
 */
public class Handle extends Thread {
	
	public void run() {
		while (true) {
			// 弹出队列
			String res = MessageQueue.queue.poll();
			if (res == null) {
				try {
					// 没有信息睡眠1秒
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			System.out.println("poll:" + res);
		}
	}
}