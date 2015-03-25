package com.test;

import java.util.List;

public class ObjectBean {
	List<String> list;
	
	public ObjectBean(List<String> list) {
		this.list = list;
		list.add("123");
	}
	
}
