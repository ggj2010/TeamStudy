package com.team.gaoguangjin.jdbc.hibernate.annation.bean;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Table;

@Getter
@Setter
@Table(appliesTo = "")
public class ClassRoom {
	private String roomName;
	private String roomSize;
	private String id;
}
