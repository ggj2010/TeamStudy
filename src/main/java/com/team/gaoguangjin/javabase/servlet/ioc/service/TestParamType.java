package com.team.gaoguangjin.javabase.servlet.ioc.service;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.team.gaoguangjin.javabase.servlet.ioc.enty.InjectBean;
import com.team.gaoguangjin.javabase.servlet.ioc.enty.ParamType;
import com.team.gaoguangjin.javabase.servlet.ioc.enty.Student;

public class TestParamType {
	
	static Logger logger = Logger.getLogger(TestParamType.class);
	
	public static void main(String[] args) {
		try {
			// ApplicationContext appliactionContext =BeanContextHelper.getApplicationContext();
			ApplicationContext cpac = new ClassPathXmlApplicationContext("reflact/reflact.xml");
			doDemo1(cpac);
		} catch (Exception e) {
			logger.error("解析xml配置文件失败：" + e.getLocalizedMessage());
		}
		
	}
	
	private static void doDemo1(ApplicationContext cpac) {
		try {
			
			ParamType paramType = (ParamType) cpac.getBean("paramType");
			Student student = (Student) cpac.getBean("student1");
			
			// 这是通过java类提供configuration 注解的bean
			ApplicationContext annationConfig = new AnnotationConfigApplicationContext(InjectBean.class);
			
			Student studentInjectBean = (Student) annationConfig.getBean("studentInjectBean", Student.class);
			
			logger.info(paramType);
			logger.info(student.getStudentName() + "++" + (new SimpleDateFormat().format(student.getStudentBirthday())));
			logger.info(studentInjectBean.getStudentName() + "++" + (new SimpleDateFormat().format(studentInjectBean.getStudentBirthday())));
			
		} catch (Exception e) {
			logger.error("" + e.getLocalizedMessage());
		}
		
	}
}
