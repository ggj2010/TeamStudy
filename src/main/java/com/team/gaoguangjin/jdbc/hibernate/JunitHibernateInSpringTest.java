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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team.gaoguangjin.jdbc.hibernate.bean.Student;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/hibernateconfig/springHibernate.xml" })
public class JunitHibernateInSpringTest {
	@Autowired
	SessionFactory sessionFactory;
	
	@Test
	public void test() {
		
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
