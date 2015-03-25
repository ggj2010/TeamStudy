package com.team.gaoguangjin.javabase.statics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	public String name;
	
	public String toString() {
		return "not static==>:name:" + name;
	}
}
