package com.team.gaoguangjin.test.testmethod;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * 
 * @author gaoguangjin
 * 
 */

@Slf4j
public class JunitTestMethod {
	/**
	 * 信息: StaticMethod方法花费时间：1ms
	 * 
	 * 信息: NotStaticMethod方法花费时间：7ms
	 * 
	 * 结论：类名直接调用效率高的。因为每次new对象都是要消耗内存的时间的
	 */
	@Test
	public void test() {
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			StaticMethod.demo();
		}
		long endTime = System.currentTimeMillis();
		log.info("StaticMethod方法花费时间：" + (endTime - beginTime) + "ms");
		
		for (int i = 0; i < 100; i++) {
			NotStaticMethod nsm = new NotStaticMethod();
			nsm.demo();
		}
		long endTime2 = System.currentTimeMillis();
		log.info("NotStaticMethod方法花费时间：" + (endTime2 - endTime) + "ms");
	}
	
}
