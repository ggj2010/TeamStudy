package com.team.gaoguangjin.javaUtil.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class Bean {
	private String name;
	private String price;
	private String description;
	private String calories;
	
	public String toString() {
		return "name=" + name + "||" + "price=" + price;
	}
}
