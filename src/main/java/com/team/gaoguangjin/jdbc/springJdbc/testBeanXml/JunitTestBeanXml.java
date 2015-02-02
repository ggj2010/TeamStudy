package com.team.gaoguangjin.jdbc.springJdbc.testBeanXml;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//指定测试用例的运行器 这里是指定了Junit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/springjdbc/springjdbc.xml" })
public class JunitTestBeanXml {
	// 如果同时注解两个同意的类@Qualifier 用来识别唯一的。@Resource用来识别注解的Bean名称
	@Qualifier("studentBean2")
	@Resource
	public Student student;
	
	@Test
	public void testBean() {
		System.out.println(student.toString());
	}
}
