package com.team.gaoguangjin.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestException {
	public static void main(String[] args) {
		demo();
	}
	
	private static void demo() {
		
		try {
			demo2();
		} catch (Exception e) {
			log.error("===");
		}
		
	}
	
	private static void demo2() {
		Integer.parseInt("aa");
	}
}
