package com.team.gaoguangjin.jdbc.springJdbc.transaction;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

//不能少！！！！
//@Transactional
@Transactional(readOnly = true)
public class TransactionDaoImp extends JdbcDaoSupport implements TransactionDao {
	// JdbcTemplate jdbcTemplate;
	//
	// public JdbcTemplate getJdbcTemplate() {
	// return jdbcTemplate;
	// }
	//
	// public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }
	
	public void findValule() {
		getJdbcTemplate().execute("insert into gao.tc_classroom values('11','测试放假','20')");
		// getJdbcTemplate().execute("insert into gao.tc_classroom values('4','测试放假','20')");
		Integer.parseInt("a");
	}
	
}
