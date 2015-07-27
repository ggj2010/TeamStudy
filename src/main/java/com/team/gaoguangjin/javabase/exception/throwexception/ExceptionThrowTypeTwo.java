package com.team.gaoguangjin.javabase.exception.throwexception;

import java.io.FileInputStream;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @ClassName:ExceptionThrow.java
 * @Description: 测试异常抛出与捕获   
 * @author gaoguangjin
 * @Date 2015-7-23 下午4:37:54
 */
@Slf4j
public class ExceptionThrowTypeTwo {
	
	public static void main(String[] args) {
		try {
			ExceptionThrowTypeTwo exception = new ExceptionThrowTypeTwo();
			exception.test2();
		} catch (Exception e) {
			log.error("父类捕获错误");
		}
	}
	
	@Test
	public void test1() throws Exception {
		try {
			new FileInputStream("aaa");
		} catch (Exception e) {
			log.error("catch错误" + e.getLocalizedMessage());
		}
		log.info(" test()1执行------");
	}
	
	@Test
	public void test2() throws Exception {
		try {
			new FileInputStream("aaa");
			log.info(" test2()执行------");
		} catch (Exception e) {
			log.error("test2()：" + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void test3() {
		try {
			test4();
		} catch (Exception e) {
			log.error("test3():" + e.getLocalizedMessage());
		}
	}
	
	public void test4() throws Exception {
		new FileInputStream("aaa");
		log.info("test4直接抛出");
	}
	
	public void test5() {
		Integer.parseInt("a");
	}
	
	@Test
	public void test6() {
		try {
			test5();
		} catch (Exception e) {
			log.error("test5 catch错误" + e.getLocalizedMessage());
		}
	}
	
}
