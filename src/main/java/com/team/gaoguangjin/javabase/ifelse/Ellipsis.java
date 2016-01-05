package com.team.gaoguangjin.javabase.ifelse;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @ClassName:Ellipsis.java
 * @Description: if else 省略括号   
 * @author gaoguangjin
 * @Date 2015-9-16 上午10:43:19
 */
@Slf4j
public class Ellipsis {
	
	@Test
	public void test() {
		int a = 1, b = 2;
		
		if (a > b)
			log.info("1");
		log.info("2");
		
	}
	
}
