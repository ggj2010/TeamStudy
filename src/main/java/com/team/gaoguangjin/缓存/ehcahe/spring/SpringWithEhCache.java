package com.team.gaoguangjin.缓存.ehcahe.spring;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName:SpringWithCache.java
 * @Description: 测试spring cache
 * @author gaoguangjin
 * @Date 2015-4-27 下午4:12:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:cache/springCache.xml" })
@Slf4j
public class SpringWithEhCache {
	@Autowired
	private CacheClass cc;// 必须要注解进去!!!!!!，不然看不到效果的 卧槽啊
	
	@Test
	public void test1() {
		CacheBean cb1 = cc.cache(1);
		CacheBean cb2 = cc.cache(1);
		log.info((cb1 == cb2) + "==");
	}
}
