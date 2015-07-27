package com.team.gaoguangjin.javabase.exception.throwexception;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ExceptionThrow.java
 * @Description: 测试异常抛出与捕获   
 * @author gaoguangjin
 * @Date 2015-7-23 下午4:37:54
 */
@Slf4j
public class ExceptionThrowTypeOne {
	
	public static void main(String[] args) {
		try {
			test();
			test2();
		} catch (Exception e) {
			log.error("父类捕获错误");
		}
		
		test3();
		
		try {
			throwTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void throwTest() throws Exception {
		try {
			throwTwo();
		} catch (Exception e) {
			throw new Exception("throwTest");
		}
		
	}
	
	private static void throwTwo() throws Exception {
		try {
			throwThree();
		} catch (Exception e) {
			throw new Exception("throwTwo");
		}
		
	}
	
	private static void throwThree() {
		Integer.parseInt("a");
		
	}
	
	private static void test3() {
		log.info("3");
		
	}
	
	private static void test2() {
		log.info("2");
		
	}
	
	private static void test() throws Exception {
		try {
			Integer.parseInt("a");
		} catch (Exception e) {
			log.error("catch错误");
		}
		
		Integer.parseInt("b");
		
	}
}
