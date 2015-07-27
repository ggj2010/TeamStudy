package com.team.gaoguangjin.jdbc.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.team.gaoguangjin.jdbc.hibernate.bean.ClassRoom;
import com.team.gaoguangjin.jdbc.hibernate.bean.Student;

/**
 * @ClassName:SpringHiberateTest.java
 * @Description: 不用junit去获取数据库对象测试hibernate
 * @author gaoguangjin
 * @Date 2015-3-11 下午2:21:46
 */
// @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class SpringHiberateTest {
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcconfig/hibernateconfig/springHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		get(sessionFactory);
	}
	
	private static void get(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ClassRoom room = new ClassRoom();
		room.setRoomName("测试aa");
		room.setId("1");
		session.update(room);
		
		/* 对更新字段较多的情况下 有点性能优化，可以优化生成的SQL语句，提高SQL执行效率，最终可以提高系统性能。但是需要先查询出来 */
		/* update gao.tc_classroom set room_name=? where id=? 用到了dynamic-update="true" */
		/* 如果没用dynamic-update="true" 那么就是update gao.tc_classroom set room_name=?, room_size=? where id=? */
		// ClassRoom cr = (ClassRoom) session.load(ClassRoom.class, "1");
		// cr.setRoomName("测试aa3");
		// session.update(cr);
		
		Query query = session.createQuery("from Student");
		List<Student> student = query.list();
		System.out.println(student.get(0));
		session.getTransaction().commit();
	}
}
