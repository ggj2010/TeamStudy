package com.team.gaoguangjin.jdbc.hibernate;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.team.gaoguangjin.jdbc.hibernate.bean.Student;

@Slf4j
public class JunitHibernateInTest {
	
	@Test
	public void test() {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcconfig/hibernateconfig/springHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		// hibernate4必须要启动事物
		session.beginTransaction();
		// session.load(Student.class, "1");
		
		Query query = session.createQuery("from Student");
		List<Student> student = query.list();
		
		// Iterator student2 = query.iterate();
		
		session.getTransaction().commit();
		System.out.println(student.get(0));
		
		// session.close();
		
	}
}
