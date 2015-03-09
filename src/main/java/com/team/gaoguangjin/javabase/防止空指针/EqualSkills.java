package com.team.gaoguangjin.javabase.防止空指针;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:EqualSkills.java
 * @Description: equal方法的写法技巧，防止空指针。"aa".equal(String)，确定的写在前面，不确定的写在equal后面
 * @author gaoguangjin
 * @Date 2015-3-2 上午9:51:20
 */
@Slf4j
public class EqualSkills {
	public static String nullStr = null;
	
	public static void main(String[] args) {
		beforeEqual();
		afterEqual();
	}
	
	private static void afterEqual() {
		try {
			nullStr.equals("123");
			
		}
		catch (Exception e) {
			log.error("afterEqual:" + e.getLocalizedMessage());
		}
		
	}
	
	private static void beforeEqual() {
		try {
			"123".equals(nullStr);
			log.info("常量在前，变量在后.不会导致报错");
		}
		catch (Exception e) {
			log.error("beforeEqual:变量在前，常量在后" + e.getLocalizedMessage());
		}
	}
}
