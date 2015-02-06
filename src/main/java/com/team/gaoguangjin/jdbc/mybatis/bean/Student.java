package com.team.gaoguangjin.jdbc.mybatis.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private ClassRoom room;
	private String studentName;
	private String studentSex;
	private String id;
	
	public String toString() {
		return "学生信息：" + studentName + ":" + studentSex + "\r\n" + "房间信息：" + room.getRoomSize() + ":"
				+ room.getRoomName();
	}
	
}
