package com.team.gaoguangjin.java性能优化.future模式.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @ClassName:Main.java
 * @Description: 通过realData构造futureTask,将这个单独的线程运行，进行并行开发
 * @author gaoguangjin
 * @Date 2015-3-20 下午5:44:45
 */
public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long beingtime = System.currentTimeMillis();
		FutureTask<String> future = new FutureTask<String>(new RealData("testString"));
		ExecutorService excetor = Executors.newFixedThreadPool(1);
		
		excetor.submit(future);
		
		System.out.println("请求完毕");
		
		try {
			Thread.sleep(5000);// 耗费5000
			System.out.println("做点别的事情");
		} catch (InterruptedException e) {
		}
		System.out.println(future.get());// 需要耗费5000
		excetor.shutdown();
		long endtime = System.currentTimeMillis();
		
		System.out.println("总花费时间:" + (endtime - beingtime));// 总花费时间:5006
	}
}
