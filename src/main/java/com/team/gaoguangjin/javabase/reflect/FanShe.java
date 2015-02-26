package com.team.gaoguangjin.javabase.reflect;

public class FanShe {
	public String id;
	private String name;
	
	static {
		
		System.out.println("初始化static代码块");
	}
	
	public FanShe(String id, String name) {
		this.id = id;
		this.name = name;
		System.out.println("【1】实例化带参数【public公有的】的构造方法");
	}
	
	public FanShe() {
		System.out.println("【1】实例化不带参数【pulbic 公有的】的构造方法");
	}
	
	private FanShe(String message) {
		System.out.println("【1】实例化不带参数【private私有的】的构造方法");
	}
	
	public String getString() {
		return name + id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void getOutPut() {
		System.out.println("【2】" + name + "==" + id);
	}
	
	public String getParameter(String message) {
		return message;
	}
}
