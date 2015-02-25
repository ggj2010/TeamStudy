package com.team.gaoguangjin.深入java虚拟机.类解析;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ErrorMethod.java
 * @Description:方法会报错，因为List<String>与List<Integer>编译之后都会被擦除类型，变成了原生类型List<E>
 * @author gaoguangjin
 * @Date 2015-2-15 下午3:10:22
 */
@Slf4j
public class ErrorMethod {
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<Integer> list2 = new ArrayList<Integer>();
		// demo1(list1);
		// demo1(list2);
	}
	
	// private static void demo1(List<Integer> list2) {
	// log.info("2");
	// }
	
	// private static void demo1(List<String> list1) {
	// log.info("1");
	// }
}
