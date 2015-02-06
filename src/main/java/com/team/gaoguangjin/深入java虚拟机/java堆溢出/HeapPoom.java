package com.team.gaoguangjin.深入java虚拟机.java堆溢出;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author gaoguangjin
 * 
 */
public class HeapPoom {
	static class OMject {
	}
	
	// 堆溢出
	public static void main(String[] args) {
		List<OMject> list = new ArrayList<OMject>();
		
		while (true) {
			list.add(new OMject());
		}
		
	}
}
