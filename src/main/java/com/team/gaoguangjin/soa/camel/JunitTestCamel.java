package com.team.gaoguangjin.soa.camel;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:com/team/gaoguangjin/soa/camel/spring/camel.xml" })
public class JunitTestCamel {
	
	@Test
	public void test() throws IOException {
		System.in.read();
	}
	
}
