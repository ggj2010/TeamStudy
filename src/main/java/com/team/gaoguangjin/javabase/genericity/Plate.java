package com.team.gaoguangjin.javabase.genericity;

/**
 * @author:gaoguangjin
 * @date 2017/6/21 13:41
 */
public class Plate<T> {
	
	private T item;
	
	public Plate(T t) {
		item = t;
	}
	
	public void set(T t) {
		item = t;
	}
	
	public T get() {
		return item;
	}
}
