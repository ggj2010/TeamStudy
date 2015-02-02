package com.team.gaoguangjin.test.testreflect;

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
	 * 信息: 信息: NormalNew方法花费时间：1ms
	 * 
	 * 信息: 信息: UserReflect方法花费时间：13ms
	 * 
	 * 结论：new 对象的效率高
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			NormalNew nln = new NormalNew();
			nln.getId();
		}
		long endTime = System.currentTimeMillis();
		log.info("NormalNew方法花费时间：" + (endTime - beginTime) + "ms");
		
		Class<?> reflectClass = Class.forName("com.team.gaoguangjin.test.testreflect.UserReflect");
		for (int i = 0; i < 10000; i++) {
			UserReflect userReflect = (UserReflect) reflectClass.newInstance();
			userReflect.getId();
		}
		long endTime2 = System.currentTimeMillis();
		log.info("UserReflect方法花费时间：" + (endTime2 - endTime) + "ms");
	}
}
