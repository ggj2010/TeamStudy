package com.team.gaoguangjin.log;

import org.apache.log4j.Logger;

/**
 * @ClassName:TestLog4j.java
 * @Description: 单纯的log4j.引入架包是org.apache.log4j.Logger
 * @author gaoguangjin
 * @Date 2015-2-27 上午10:23:06
 */
public class TestLog4j {
	
	// 获取log4j对象方式
	public final static Logger log4 = Logger.getLogger(TestLog4j.class);
	
	// 只要在classpath下面有一个log4j.propeties就可以使用
	public static void main(String[] args) {
		// 普通的log4j
		log4.error("错误信息信息log4");
		log4.info("info信息信息log4");
		log4.debug("debug信息信息log4");
	}
	
}
