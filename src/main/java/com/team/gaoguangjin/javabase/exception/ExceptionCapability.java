package com.team.gaoguangjin.javabase.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author gaoguangjin 为什么不提倡catch(Exception) try..catch 在出现异常的时候影响性能; 应该捕获更具体得异常，比如IOExeception,OutOfMemoryException等
 */
@Slf4j
public class ExceptionCapability {
	
	public static void main(String[] args) {
		exceptionByListTest();
		// exectionCapabilityTest();
		
	}
	
	/**
	 * 测试循环里面添加异常捕获
	 */
	private static void exceptionByListTest() {
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			catchExceptionTest();
		}
		long endTime = System.currentTimeMillis();
		
		long time2 = System.currentTimeMillis();
		
		try {
			for (int i = 0; i < 100; i++) {
				catchExceptionNormal();
			}
		} catch (Exception e) {
			log.error("捕获Exception：" + e.getLocalizedMessage());
		}
		long endTime2 = System.currentTimeMillis();
		
		log.info("catchExceptionTest花费时间：" + (endTime - time));
		log.info("catchExceptionNormal花费时间：" + (endTime2 - time2));
		
	}
	
	private static void catchExceptionNormal() throws IOException {
		File file = new File("aa");
		FileInputStream fis = new FileInputStream(file);
		fis.close();
	}
	
	/**
	 * 比较catch具体的异常类和直接异常的性能 catch(Excepiton e)和 catch(DetailExcepiton e)比较
	 * 
	 */
	private static void exectionCapabilityTest() {
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			catchExceptionTest();
		}
		long endTime = System.currentTimeMillis();
		
		long time2 = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			catchDetailException();
		}
		long endTime2 = System.currentTimeMillis();
		
		log.info("catchExceptionTestList花费时间：" + (endTime - time));
		log.info("catchDetailException花费时间：" + (endTime2 - time2));
		
	}
	
	private static void catchDetailException() {
		try {
			
			File file = new File("aa");
			FileInputStream fis = new FileInputStream(file);
			fis.close();
		} catch (FileNotFoundException e) {
			log.error("捕获FileNotFoundException：" + e.getLocalizedMessage());
		} catch (IOException e) {
			log.error("捕获IOException：" + e.getLocalizedMessage());
		}
		
	}
	
	private static void catchExceptionTest() {
		try {
			File file = new File("aa");
			FileInputStream fis = new FileInputStream(file);
			fis.close();
		} catch (Exception e) {
			log.error("捕获Exception：" + e.getLocalizedMessage());
		}
		
	}
	
}
