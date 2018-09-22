package com.team.gaoguangjin.javabase.exception.自定义异常;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:MyExceptionText.java
 * @Description: 测试自定义异常。其实我们可以自定义一个异常父类继承，RuntimeException，然后根据不同的业务抛出不同的异常
 * @author gaoguangjin
 * @Date 2015-4-14 上午11:29:13
 */

// 1、throws出现在方法函数头；而throw出现在函数体。
// 2、throws表示出现异常的一种可能性，并不一定会发生这些异常；throw则是抛出了异常，执行throw则一定抛出了某种异常。
// 3、两者都是消极处理异常的方式（这里的消极并不是说这种方式不好），只是抛出或者可能抛出异常，
// 但是不会由函数去处理异常，真正的处理异常由函数的上层调用处理。
@Slf4j
public class MyExceptionText {
	public static void main(String[] args) {
		
		System.out.println(MyExceptionText.class.getName());
		// demo1();// 直接throw自定义异常，一定出现异常
		try {
			// demo2();//thows异常,可能出现异常。上层调用者捕获
		} catch (Exception e) {
			log.info("异常：" + e);
		}
		
		demo3();
		
		try {
			demo4();// Exception extends Throwable【必须】要抛出的，然后在上一层进行捕获
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		demo5();// 因为MyException extends RuntimeException 继承的是RuntimeException 所以不需要强制抛出
		
	}
	
	private static void demo5() throws MyException {
	}
	
	private static void demo4() throws Exception {
		throw new MyException("这是异常噢！");
	}
	
	private static void demo3() throws MyException {
		try {
			throw new MyException("这是异常噢！");
		} catch (Exception e) {
			log.info(e + "");
		}
	}
	
	private static void demo2() throws MyException {
	}
	
	private static void demo1() {
		throw new MyException("这是异常噢！");
	}
}
