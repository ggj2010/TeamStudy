package com.team.gaoguangjin.springinaction.annoation.seconddemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// 用于描述域
public @interface ColorAnnation {
	public String name();
	
	public String color();
	
}
