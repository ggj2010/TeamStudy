package com.team.gaoguangjin.java性能优化.future模式.normal;

public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		// 假如获取data需要很长的时间，那么我就返回一个代理的data
		Data data = client.request("aaaa");
		
		// 这直接可以调用别的数据
		
		for (int i = 0; i < 10; i++) {
			System.out.println("做别的事情//////////");
		}
		
		// 真正调用的时候再去调用真实的data
		System.out.println(data.getResult());
	}
}
