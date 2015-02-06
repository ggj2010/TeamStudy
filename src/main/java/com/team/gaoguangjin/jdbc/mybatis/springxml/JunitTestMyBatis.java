package com.team.gaoguangjin.jdbc.mybatis.springxml;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team.gaoguangjin.jdbc.mybatis.bean.Subject;
import com.team.gaoguangjin.jdbc.mybatis.mapper.StudentMapper;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/mybatisconfig/spring/springmybatisjdbc.xml" })
public class JunitTestMyBatis {
	// 注入studentMapper
	@Autowired
	StudentMapper studentMapper;
	
	@Test
	public void test() {
		Subject subject = studentMapper.selectSubjectById("1");
		System.out.println(subject.toString());
	}
}
