package com.team.gaoguangjin.log;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName:TestLogback.java
 * @Description: logback使用,
 * 
 * 测试的时候需要将/src/main/resources/目录下面的logback.xml.brk文件名修改成logback.xml还有将pom.xml里面的架包注释去掉
 * @author gaoguangjin
 * @Date 2015-2-27 上午10:31:17
 */
@Slf4j
public class TestLogback {
	
	// 定义一个全局的记录器，通过LoggerFactory获取
	private final static Logger logger = LoggerFactory.getLogger(TestLogback.class);
	
	/**
	 * @Description:logback的配置文件有以下两种，logback-test.xml；logback.xml，如果都不存在就用，默认的
	 */
	public static void main(String[] args) {
		// self4j
		log.error("错误");
		log.info("info");
		log.debug("debug");
		
	}
}
