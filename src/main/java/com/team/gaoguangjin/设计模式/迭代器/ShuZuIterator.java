package com.team.gaoguangjin.设计模式.迭代器;

import java.util.Iterator;

public class ShuZuIterator implements Iterator {
	
	public int m;
	public String[] str;
	
	public ShuZuIterator(String[] str) {
		this.str = str;
	}
	
	public boolean hasNext() {
		if (m >= str.length || str[m] == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Object next() {
		String strs = str[m];
		m += 1;
		return strs;
	}
	
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
