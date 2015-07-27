package com.team.gaoguangjin.javabase.exception;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @ClassName:ExceptionType.java
 * @Description: 介绍异常有那些种类
 * @author gaoguangjin
 * @Date 2015-3-11 下午12:25:20
 */
@Slf4j
public class ExceptionType {
	@Test
	public void test() {
		// 运行时异常，RuntimeException
		unCheckedException();
		
		// 检查式异常 需要try catch
		// checkedException();
		
		// 测试finally
		// log.info("返回值：" + testFinally());
		
	}
	
	/**
	 * @Description: 运行时异常，常见种类有以下几种
	 * @see: NullPointerException,
	 * @see: IndexOutOfBoundsException
	 * @see:NumberFormatException
	 * @see:ClassCastException 类型转换错误
	 * @see:ArrayStoreException 类型转换错误
	 * @return:void
	 */
	private void unCheckedException() {
		// List list = null;list.get(0);//NullPointerException
		// new ArrayList().get(1);//IndexOutOfBoundsException
		// Integer.parseInt("dd");//NumberFormatException
		// List aa=(List)new ExceptionType();//ClassCastException
		/**
		 * Object[] in = { 1, 2 }; in = new Integer[5]; in[1] = "123";//ArrayStoreException
		 **/
	}
	
	/**
	 * @Description:
	 * @see 2015-03-11 13:45:29,170 INFO [main] (ExceptionType.java:41) - try语句块中
	 * @see 2015-03-11 13:45:29,175 INFO [main] (ExceptionType.java:47) - finally语句块中
	 * @see 2015-03-11 13:45:29,175 INFO [main] (ExceptionType.java:24) - 返回值：finally里面返回值
	 * @return:String
	 */
	private String testFinally() {
		try {
			log.info("try语句块中");
			return "try里面返回值";
		} catch (Exception e) {
			return "catch里面返回值";
		}
		finally {
			log.info("finally语句块中");
			return "finally里面返回值";
			
		}
	}
}
