package com.team.gaoguangjin.javabase.exception.自定义异常;

/**
 * @ClassName:MyException.java
 * @Description: * 继承至rumtime exception 不必强制throws
 * @author gaoguangjin
 * @Date 2015-4-14 下午12:15:50
 */
public class MyException extends RuntimeException {
	public MyException() {
		super();
	}
	
	public MyException(String message) {
		super(message);
	}
	
	public MyException(String message, Throwable ruable) {
		super(message, ruable);
	}
}
