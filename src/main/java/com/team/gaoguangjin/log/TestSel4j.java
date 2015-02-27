package com.team.gaoguangjin.log;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName:TestLog4j.java
 * @Description:SLF4J结合Log4j
 * @author gaoguangjin
 * @Date 2015-2-27 上午10:23:06
 */
@Slf4j
public class TestSel4j {
	// SLF4J获得logger对象 LoggerFactory,这个与log4j获得对象是有区别的。注意！
	public static final Logger logSlf4j = LoggerFactory.getLogger(TestSel4j.class);
	
	// 只要在classpath下面有一个log4j.propeties就可以使用
	public static void main(String[] args) {
		slf4j();
		
		// 使用注解式引入的logSlf4j @Slf4j，需要用到lombok这个架包才可以省略显示获取日志对象，直接获取log
		annotationSlf4j();
		
		System.out.println(log == logSlf4j);// true
	}
	
	/**
	 * @Description: log等价于logSlf4j 对象
	 */
	private static void annotationSlf4j() {
		log.error("错误信息信息:注解式@Slf4j");
		log.info("info信息信息:注解式@Slf4j");
		log.debug("debug信息信息:注解式@Slf4j");
		
	}
	
	/**
	 * @Description:slf4j是日志的接口（只定义了一些方法而没有去实现），和commons-logging一样。而log4j是具体的实现（即怎么来打印日志等），和logback是一样的。
	 */
	private static void slf4j() {
		logSlf4j.error("错误信息信息logSefl：LoggerFactory.getLogger");
		logSlf4j.info("info信息信息logSefl：LoggerFactory.getLogger");
		logSlf4j.debug("debug信息信息logSefl：LoggerFactory.getLogger");
	}
}
