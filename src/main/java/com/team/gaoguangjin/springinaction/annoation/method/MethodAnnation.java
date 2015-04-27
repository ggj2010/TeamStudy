package com.team.gaoguangjin.springinaction.annoation.method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// type方法
public @interface MethodAnnation {
	public boolean isTest();
}
