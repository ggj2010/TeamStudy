package com.team.gaoguangjin.thread.pdf;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName:BlockQuenThread.java
 * @Description: 阻塞队列使用,常用与生产者消费者。ArrayBlockingQueue和LinkedBlockingqueue和SynchronousQueue
 * @author gaoguangjin
 * @Date 2015-3-20 下午6:05:01
 */
public class BlockQuenThread {
	public static void main(String[] args) throws InterruptedException {
		demo();
		// demo1();
	}
	
	private static void demo1() throws InterruptedException {
		BlockingQueue<Object> blockingQ = new ArrayBlockingQueue<Object>(2);
		blockingQ.put("132");
		blockingQ.take();
		// String aa = (String) blockingQ.take();
		
		if (blockingQ.isEmpty()) {
			System.out.println("空值了");
		}
		
	}
	
	private static void demo() throws InterruptedException {
		final BlockingQueue<Object> blockingQ = new ArrayBlockingQueue<Object>(5);
		// blockingQ.put("132");
		
		Thread thread = new Thread("cosumer thread") {
			public void run() {
				for (int i = 0; i < 10; i++) {
					Object object = null;
					// object = blockingQ.poll();// 不等待就直接返回，如果blockingQ里面没有值那就返回null,全部都执行
					
					try {
						
						Thread.sleep(1000);
						// object = blockingQ.poll(1, TimeUnit.SECONDS);//
						object = blockingQ.take();// 等到有数据才继续的噢，如果没有值，那就一直等待的
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					handle(object);
				}
			}
			
			private void handle(Object object) {
				System.out.println(object);
			}
		};
		thread.start();
		
		for (int i = 0; i < 10; i++) {
			blockingQ.put(i); // 如果队列满则阻塞 比如设定的是5个 放了
		}
		
	}
}
