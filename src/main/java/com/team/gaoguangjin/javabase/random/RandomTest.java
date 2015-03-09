package com.team.gaoguangjin.javabase.random;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @ClassName:RandomTest.java
 * @Description: random类的测试
 * @author gaoguangjin
 * @Date 2015-3-5 上午10:39:00
 */
@Slf4j
public class RandomTest {
	@Test
	public void test() {
		// 直接用math.random获取随机数
		double random = Math.random();
		log.info("" + Math.random());
		
		// 使用random类 获取 nextInt nextDouble等等方法
		Random rd = new Random();
		log.info("" + rd.nextInt(1000));
		
	}
}
