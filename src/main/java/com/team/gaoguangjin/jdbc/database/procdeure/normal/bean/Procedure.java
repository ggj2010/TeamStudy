package com.team.gaoguangjin.jdbc.database.procdeure.normal.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Procedure {
	private int id;
	private String name;
	private String content;
	private Date createdate;
}
