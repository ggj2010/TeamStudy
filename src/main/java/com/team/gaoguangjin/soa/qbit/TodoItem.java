package com.team.gaoguangjin.soa.qbit;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoItem {
	
	private String description;
	private String name;
	private Date due;
}
