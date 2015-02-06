package com.team.gaoguangjin.深入java虚拟机.栈溢出;

import lombok.extern.slf4j.Slf4j;

/**
 * -Xss2m
 * @author gaoguangjin
 * 
 */
@Slf4j
public class JavaVMStatcSOF {
	private int stackLength = 1;
	
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) throws Throwable {
		JavaVMStatcSOF over = new JavaVMStatcSOF();
		try {
			over.stackLeak();
		}
		catch (Throwable e) {
			log.error("栈内存不够的长度 stackLength=" + over.stackLength);
		}
	}
}
