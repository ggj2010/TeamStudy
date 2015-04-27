package com.team.gaoguangjin.jdbc.hibernate;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.team.gaoguangjin.jdbc.hibernate.bean.ClassRoom;
import com.team.gaoguangjin.jdbc.hibernate.bean.Student;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/hibernateconfig/springHibernate.xml" })
// 这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
// 这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class JunitHibernateInSpringTranstion extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	SessionFactory sessionFactory;
	
	@Test
	// @Transactional
	// 加了@Transactional 那么就不用手动去开启事物，交给spring去管理了
	public void findtest() {
		
		// 1、手动去管理事物
		
		// doHibernateQuery();
		
		// 2、由spring管理事物
		findSpringManagerTransationQuery();
		
	}
	
	/**
	 * @Description: spring去管理事物
	 */
	@Transactional
	public void findSpringManagerTransationQuery() {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			ClassRoom room = new ClassRoom();
			room.setRoomName("测试5");
			room.setRoomSize("123445");
			log.error("=================");
			
			session.saveOrUpdate(room);
			String a = "dd";
			Integer.parseInt(a);// 添加这个错误信息验证事物是否配置成功,去掉try catch
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: hibernate自己管理事物
	 */
	private void doHibernateQuery() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();// 打开事物
		Query query = session.createQuery("from Student");
		List<Student> student = query.list();
		
		session.getTransaction().commit();// 提交
		System.out.println(student.get(0));
	}
	
	/**
	 * sessionFactory.getCurrentSession()的调用没错，如果调用openSession了，还要调用beginTransaction(),最后还要transaction.commit()，
	 * 试问这样还配spring干啥？自己手动管理事务？
	 */
	
	/**
	 * 这个问题我遇到过了， 解决掉了。由于hibernate4
	 * 自己能够管理事务了，所以spring中就没有hibernateTemp。。类了。spring建议用hibernate原生session。但是在服务器启动后是没有session的
	 * 。所以你取不到session。告诉你没有活动的session。有一招就是在服务器启动的时候就开一个session放到hibernate容器中，这样就好了。代码手头上没有 ，你找找。
	 */
}
