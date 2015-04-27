package com.team.gaoguangjin.jdbc.springJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/springjdbc/springjdbc.xml" })
public class JunitTestSpringJdbc {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	DriverManagerDataSource dataSource;
	
	@Test
	public void testJdbcTemplate() throws SQLException {
		String result = jdbcTemplate.queryForObject("select sysdate from dual", String.class);
		log.info("result:" + result);
		// jdbcTemplate.execute("");
	}
	
	@Test
	public void test() {
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("select sysdate from dual");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("sysdate"));
			}
		} catch (SQLException e) {
			log.error("spring testDriverManagerDataSource()方法查询异常！" + e.getLocalizedMessage());
		}
	}
}
