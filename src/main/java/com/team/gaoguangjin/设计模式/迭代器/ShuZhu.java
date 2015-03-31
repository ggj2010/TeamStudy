package com.team.gaoguangjin.设计模式.迭代器;

import java.util.Iterator;

public class ShuZhu {
	public String[] str;
	
	public ShuZhu(String[] str) {
		this.str = str;
	}
	
	public Iterator createIterator() {
		return new ShuZuIterator(str);
	}
}
