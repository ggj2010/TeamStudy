package com.team.gaoguangjin.javabase.servlet.ioc.enty;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectBean {
	
	@Bean(name = "studentInjectBean")
	public Student createStudent() {
		Student student = new Student();
		student.setStudentName("高广金");
		student.setStudentBirthday(new Date());
		return student;
	}
}
