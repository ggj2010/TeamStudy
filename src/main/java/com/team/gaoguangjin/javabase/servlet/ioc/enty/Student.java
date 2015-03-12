package com.team.gaoguangjin.javabase.servlet.ioc.enty;

import java.util.Date;

public class Student {
	private String studentName;
	private String studentAddress;
	private int studentAge;
	private Date studentBirthday;
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getStudentAddress() {
		return studentAddress;
	}
	
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	
	public int getStudentAge() {
		return studentAge;
	}
	
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	
	public Date getStudentBirthday() {
		return studentBirthday;
	}
	
	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
}
