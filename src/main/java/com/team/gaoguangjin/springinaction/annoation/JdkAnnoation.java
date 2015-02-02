package com.team.gaoguangjin.springinaction.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// 申明注解保留期限
@Target(ElementType.METHOD)
// 申明可以使用该注解的类型
public @interface JdkAnnoation {// 定义注解
	boolean value() default true;// 申明注解成员
	
	String url();
}