package com.team.gaoguangjin.jdbc.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.team.gaoguangjin.jdbc.hibernate.bean.ClassRoom;
import com.team.gaoguangjin.jdbc.hibernate.bean.Student;

/**
 * @ClassName:SpringHiberateTest.java
 * @Description: 不用junit去获取数据库对象测试hibernate
 * @author gaoguangjin
 * @Date 2015-3-11 下午2:21:46
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class SpringHiberateTest {
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcconfig/hibernateconfig/springHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		getBySessionFactory(sessionFactory);
	}
	
	private static void getBySessionFactory(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		ClassRoom room = new ClassRoom();
		room.setRoomName("测试44");
		room.setRoomSize("12344");
		room.setId("1");
		session.update(room);
		System.out.println("===");
		Query query = session.createQuery("from Student");
		List<Student> student = query.list();
		
		System.out.println(student.get(0));
	}
}
