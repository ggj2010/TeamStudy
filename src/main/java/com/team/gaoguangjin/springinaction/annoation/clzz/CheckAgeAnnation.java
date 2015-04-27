package com.team.gaoguangjin.springinaction.annoation.clzz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// type描述类
public @interface CheckAgeAnnation {
	public boolean isNeed();
}
