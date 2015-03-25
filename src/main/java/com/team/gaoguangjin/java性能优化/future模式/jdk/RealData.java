package com.team.gaoguangjin.java性能优化.future模式.jdk;

import java.util.concurrent.Callable;

/**
 * @ClassName:RealData.java
 * @Description: jdk自带的feture模式
 * @author gaoguangjin
 * @Date 2015-3-20 下午5:43:34
 */
public class RealData implements Callable<String> {
	String param;
	
	public RealData(String string) {
		this.param = string;
	}
	
	public String call() throws Exception {
		try {
			Thread.sleep(5000);
			// 模拟操作很长时间。。。。。
		} catch (Exception e) {
			// ....
		}
		return "【长时间返回的结果】+啊啊啊啊啊啊啊啊啊啊啊！";
	}
	
}
