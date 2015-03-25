package com.team.gaoguangjin.java性能优化.future模式.normal;

public class Client {
	
	public Data request(String str) {
		final FutureData data = new FutureData();
		new Thread() {
			public void run() {
				RealData realData = new RealData();
				data.setRealData(realData);
			}
		}.start();
		return data;
	}
}
