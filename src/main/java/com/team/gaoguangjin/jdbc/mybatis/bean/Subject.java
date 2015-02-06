package com.team.gaoguangjin.jdbc.mybatis.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subject {
	private String id;
	private String subjectName;
	private String subjectTeacherName;
	private List<Student> studentList;
	
	public String toString() {
		StringBuilder outPutString = new StringBuilder();
		outPutString.append("选修课程名称：" + subjectName + "\t" + "授课老师姓名：" + subjectTeacherName + "\r\n");
		
		if (null != studentList && studentList.size() > 0) {
			outPutString.append("选课总人数有：【" + studentList.size() + "】人" + "\r\n");
			for (Student student : studentList) {
				outPutString.append("学生姓名：" + student.getStudentName() + "\t" + "教室名称："
						+ student.getRoom().getRoomName() + "\r");
			}
		}
		return outPutString.toString();
	}
}
