package com.team.gaoguangjin.缓存.缓存和数据库测试;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.team.gaoguangjin.jdbc.hibernate.bean.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/hibernateconfig/csdn/springHibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class JunitTestMySql {
	@Autowired
	SessionFactory sessionFactory;
	
	@Test
	@Transactional(readOnly = false)
	public void test() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:/ftp/6_csdn.sql")), "gbk"));
		// BufferedReader br = new BufferedReader(new FileReader(new File("d:/ftp/5_csdn.sql")));
		String str = "";
		int i = 0;
		while ((str = br.readLine()) != null) {
			String[] strs = str.trim().split("#");
			UserInfo u = new UserInfo();
			if (strs.length == 3 && !StringUtils.isEmpty(strs[0]) && !StringUtils.isEmpty(strs[1]) && !StringUtils.isEmpty(strs[2])) {
				u.setName(strs[0].trim());
				u.setPassword(strs[1].trim());
				u.setEmail(strs[2].trim());
				session.save(u);
				System.out.println("" + strs[0] + ":" + strs[1]);
				i++;
			}
			if (i == 100) {
				session.flush();
				session.clear();
				i = 0;
			}
		}
	}
	
	// @Test
	// @Transactional(readOnly = true)
	public void select() {
		Session session = sessionFactory.getCurrentSession();
		UserInfo ui = (UserInfo) session.load(UserInfo.class, Long.parseLong("1"));
		ui.toString();
		
		UserInfo ui2 = (UserInfo) session.load(UserInfo.class, Long.parseLong("1"));
		ui2.toString();
	}
}
