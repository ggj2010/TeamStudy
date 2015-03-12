package com.team.gaoguangjin.javabase.servlet.ioc.enty;

import java.util.Date;

public class StudentFactory {
	public Student createStudent() {
		Student student = new Student();
		student.setStudentName("studentInjectBean高广金");
		student.setStudentBirthday(new Date());
		return student;
	}
}
