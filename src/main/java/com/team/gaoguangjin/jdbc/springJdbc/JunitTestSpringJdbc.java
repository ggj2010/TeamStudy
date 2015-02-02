package com.team.gaoguangjin.jdbc.springJdbc;

import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/springjdbc/springjdbc.xml" })
public class JunitTestSpringJdbc {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Test
	public void testJdbcTemplate() throws SQLException {
		String result = jdbcTemplate.queryForObject("select sysdate from dual", String.class);
		log.info("result:" + result);
	}
}
