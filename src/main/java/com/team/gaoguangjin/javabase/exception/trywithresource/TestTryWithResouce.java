package com.team.gaoguangjin.javabase.exception.trywithresource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @ClassName:TestTryWithResouce.java
 * @Description:  Java SE7新特性之try-with-resources语句  
 * @author gaoguangjin
 * @Date 2015-9-15 上午11:09:45
 */
@Slf4j
public class TestTryWithResouce {
	@Test
	public void test() {
		try (Pool pool = new Pool()) {
			pool.get();
			Integer.parseInt("ddd");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
}
