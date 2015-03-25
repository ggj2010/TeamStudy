package com.team.gaoguangjin.java性能优化.future模式.normal;

public class RealData implements Data {
	
	public RealData() {
		try {
			Thread.sleep(5000);// 模拟获取需要花费好久的时间
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getResult() {
		return "【结果】    长时间的。。。。。。。。结果";
	}
	
}
