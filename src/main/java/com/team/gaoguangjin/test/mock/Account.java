package com.team.gaoguangjin.test.mock;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account implements Serializable {
	public Account(String acctName) {
		// TODO Auto-generated constructor stub
	}
	
	private Long id;
	private String name;
	private String pwd;
}
