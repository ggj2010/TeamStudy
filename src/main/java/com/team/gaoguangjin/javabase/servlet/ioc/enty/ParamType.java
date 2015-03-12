package com.team.gaoguangjin.javabase.servlet.ioc.enty;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParamType {
	
	private String userName;
	private int age;
	private String nullTest;
	
	private List<String> listString;
	private String[] st;
	private Set set;
	
	public Set getSet() {
		return set;
	}
	
	public void setSet(Set set) {
		this.set = set;
	}
	
	public String getNullTest() {
		return nullTest;
	}
	
	public void setNullTest(String nullTest) {
		this.nullTest = nullTest;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<String> getListString() {
		return listString;
	}
	
	public void setListString(List<String> listString) {
		this.listString = listString;
	}
	
	public String[] getSt() {
		return st;
	}
	
	public void setSt(String[] st) {
		this.st = st;
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	private Map<String, String> map;
	
	// 构造器注入用到
	public ParamType(String userName, int age) {
		this.userName = userName;
		this.age = age;
	}
	
	public ParamType() {
		System.out.println("BeanXml解析时候 嗲用构造器");
	}
	
	public String toString() {
		String st1 = "属性赋值：" + userName + ":" + age + "\r\n";
		String st2 = "空值测试" + nullTest + "\r\n";
		String st3 = "这是list" + listString.get(0) + "--" + listString.get(1) + "\r\n";
		String st4 = "数组测试" + st[0] + st[1] + "\r\n";
		String st5 = "map测试" + map.get("map1") + map.get("map2") + "\r\n";
		String st6 = "set测试" + set.iterator().next() + "\r\n";
		// return
		// userName+":"+age+"==这是空值测试"+nullTest+"==这是list:"+listString.get(0)+"--"+listString.get(1)+"==数组"+st[0];
		return st1 + st2 + st3 + st4 + st5 + st6;
	}
	
}
