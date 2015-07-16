package com.team.gaoguangjin.javabase.io;

import java.io.File;
import java.io.FileInputStream;

public class TestByte {
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		try {
			
			String filePath = "d:duanlian.txt";
			
			FileInputStream fis = new FileInputStream(new File(filePath));
			
			byte[] by = new byte[1024];
			
			while (fis.read(by) > -1) {
				System.out.print(new String(by, "utf-8"));
			}
		} catch (Exception e) {
			
		}
		
	}
}
