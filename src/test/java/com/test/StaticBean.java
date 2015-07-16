package com.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticBean {
	private String name;
	
	public StaticBean(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
}
