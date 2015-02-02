package com.team.gaoguangjin.jdbc.springJdbc.testBeanXml;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private Teacher teacher;
	private String studentName;
	private String studentSex;
	
	public String toString() {
		return studentName + ":" + studentSex + "\r\n" + teacher.getTeacherName() + ":" + teacher.getTeacherAge();
	}
}
