package com.team.gaoguangjin.javabase.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinallyReturn {
	
	//
	// 1、不管有木有出现异常，finally块中代码都会执行；
	// 2、当try和catch中有return时，finally仍然会执行；
	// 3、finally是在return后面的表达式运算后执行的（此时并没有返回运算后的值，而是先把要返回的值保存起来，管finally中的代码怎么样，返回的值都不会改变，任然是之前保存的值），所以函数返回值是在finally执行前确定的；
	// 4、finally中最好不要包含return，否则程序会提前退出，返回值不是try或catch中保存的返回值。
	public static void main(String[] args) {
		
		String name = getString();
		log.info(name);
	}
	
	private static String getString() {
		String name = "B";
		try {
			// Integer.parseInt("a");
		} catch (Exception e) {
			log.error("出错了！" + e.getLocalizedMessage());
		}
		finally {
			name = "A";
			// finally是在return后面的表达式运算后执行的（此时并没有返回运算后的值，而是先把要返回的值保存起来，管finally中的代码怎么样，返回的值都不会改变，任然是之前保存的值），所以函数返回值是在finally执行前确定的
			log.info("执行finlly:name=" + name);
		}
		name = "c";
		return name;
	}
}
