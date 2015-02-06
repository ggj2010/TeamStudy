package com.team.gaoguangjin.深入java虚拟机.运行时常量池;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author gaoguangjin
 * 
 */
public class RuntimeConstanPoolOOM {
	public static void main(String[] args) {
		// list保持着常量池的引用，编码Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
