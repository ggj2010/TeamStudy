package com.team.gaoguangjin.java性能优化.future模式.normal;

public class FutureData implements Data {
	boolean isReady = false;
	RealData realData;
	
	@Override
	public synchronized String getResult() {
		System.out.println("获取值");
		while (!isReady) {
			try {
				System.out.println("值还没生产，继续等待");
				wait();
				System.out.println("值已经获取到，被通知了");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return realData.getResult();
	}
	
	public synchronized void setRealData(RealData realData) {
		if (isReady)
			return;
		this.realData = realData;
		isReady = true;
		notifyAll();
		System.out.println("值生产好了！！调用notifall()通知");
	}
	
}
