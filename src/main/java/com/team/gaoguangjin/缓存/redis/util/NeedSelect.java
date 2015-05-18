package com.team.gaoguangjin.缓存.redis.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// type方法
public @interface NeedSelect {
	public boolean isSelect() default true;
}
