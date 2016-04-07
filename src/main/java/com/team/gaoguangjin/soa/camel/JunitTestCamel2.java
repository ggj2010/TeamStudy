package com.team.gaoguangjin.soa.camel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:com/team/gaoguangjin/soa/camel/spring/camel2.xml" })
public class JunitTestCamel2 {
	
	@Test
	public void test() throws IOException {
		System.in.read();
	}
	
}
