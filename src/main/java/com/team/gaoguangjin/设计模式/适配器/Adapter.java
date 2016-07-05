package com.team.gaoguangjin.设计模式.适配器;

/**
 * 类适配器  因为 Adapter 类既继承了 Adaptee （被适配类），也实现了 Target 接口（因为 Java 不支持多继承，所以这样来实现），在 Client 类中我们可以根据需要选择并创建任一种符合需求的子类，来实现具体功能。
 * @author 高广金
 * 
 */
public class Adapter extends Adaptee implements Target {
	
	public void request() {
		super.specificRequest();
	}
	
}
