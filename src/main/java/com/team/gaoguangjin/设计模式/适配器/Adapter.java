package com.team.gaoguangjin.设计模式.适配器;

/**
 * 适配器
 * @author 高广金
 * 
 */
public class Adapter extends Adaptee implements Target {
	
	public void request() {
		super.specificRequest();
	}
	
}
