package com.team.gaoguangjin.javabase.servlet.ioc.enty;

public class ClassRoom {
	private String roomName;
	private float roomSize;
	
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public float getRoomSize() {
		return roomSize;
	}
	
	public void setRoomSize(float roomSize) {
		this.roomSize = roomSize;
	}
	
	public String toOutPut() {
		return "教室名称：" + roomName + "-----" + "教室面积" + roomSize;
	}
}
