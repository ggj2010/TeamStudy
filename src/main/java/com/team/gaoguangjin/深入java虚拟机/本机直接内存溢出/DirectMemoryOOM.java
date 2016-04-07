package com.team.gaoguangjin.深入java虚拟机.本机直接内存溢出;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xmx10M -Xms10M -XX:-HeapDumpOnOutOfMemoryError
 *  java.lang.OutOfMemoryError: Java heap space
 * @author gaoguangjin
 * 
 */

public class DirectMemoryOOM {
	private static final int _1MB = 1024 * 1024;
	
	public static void main(String[] args) {

		List<DirectMemoryOOM> list=new ArrayList<DirectMemoryOOM>();
		while(true){
			list.add(new DirectMemoryOOM());
		}
	}
	
}
