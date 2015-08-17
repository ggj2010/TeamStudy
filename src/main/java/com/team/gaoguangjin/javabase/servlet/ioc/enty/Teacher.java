package com.team.gaoguangjin.javabase.servlet.ioc.enty;

public class Teacher {
	private String teacherName;
	private String teacherMajor;
	private Person person;
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getTeacherMajor() {
		return teacherMajor;
	}
	
	public void setTeacherMajor(String teacherMajor) {
		this.teacherMajor = teacherMajor;
	}
	
}
