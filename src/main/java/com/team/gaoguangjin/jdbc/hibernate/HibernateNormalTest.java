package com.team.gaoguangjin.jdbc.hibernate;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.team.gaoguangjin.jdbc.hibernate.bean.Student;

@Slf4j
public class HibernateNormalTest {
	public static void main(String[] args) {
		// 简单初始化
		initCrateTable();
		
	}
	
	private static void initCrateTable() {
		ClassPathResource classPathResource = new ClassPathResource("jdbcconfig/hibernateconfig/normal/hibernate.cfg.xml");
		String fileName = classPathResource.getFilename();
		log.info("文件名称:" + fileName);
		
		try {
			Configuration config = new Configuration().configure(classPathResource.getFile());
			SessionFactory sessionFatcory = config.buildSessionFactory();
			Session session = sessionFatcory.openSession();
			// Session session = sessionFatcory.getCurrentSession();
			
			Query query = session.createQuery("from Student");
			List<Student> student = query.list();
			System.out.println(student.get(0));
			
			session.close();
			sessionFatcory.close();
		} catch (HibernateException e) {
			log.error("初始化失败! " + e.getLocalizedMessage());
		} catch (IOException e) {
			log.error("初始化文件加载失败! " + e.getLocalizedMessage());
		}
		
	}
}
