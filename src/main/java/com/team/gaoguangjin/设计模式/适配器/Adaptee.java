package com.team.gaoguangjin.设计模式.适配器;

/**
 * 需要被装饰的类
 * @author 高广金
 * 
 */
public class Adaptee {
	public void specificRequest() {
		System.out.println("被适配类具有 特殊功能...");
	}
}
