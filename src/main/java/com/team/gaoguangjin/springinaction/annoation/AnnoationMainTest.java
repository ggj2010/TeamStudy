package com.team.gaoguangjin.springinaction.annoation;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义注解测试
 * @author gaoguangjin
 * @date 2015-01-28
 */
@Slf4j
public class AnnoationMainTest {
	
	public static void main(String[] args) {
		demo1();
		
	}
	
	private static void demo1() {
		try {
			Class clzz = AnnotationDemo.class;
			Method[] methods = clzz.getMethods();
			for (Method method : methods) {
				JdkAnnoation annotation = method.getAnnotation(JdkAnnoation.class);
				if (null != annotation) {
					if (annotation.value()) {
						
						method.invoke(clzz.newInstance(), null);
						
						System.out.println(method.getName() + " 注解的值为" + annotation.value() + "\t" + "需要测试的噢"
								+ annotation.url() + "\r\n");
					} else {
						System.out.println(method.getName() + " 注解的值为" + annotation.value() + "\t" + "不需要测试的噢"
								+ annotation.url());
					}
				}
			}
		}
		catch (Exception e) {
			log.error("获取注解内容失败" + e.getLocalizedMessage());
		}
	}
}
