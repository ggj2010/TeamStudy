package com.team.gaoguangjin.jdbc.mybatis.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRoom {
	private String roomName;
	private String roomSize;
	private String id;
	
	public String toString() {
		return "id:" + id + "===:" + roomName + "==roomSize:" + roomSize;
	}
}
