package com.team.gaoguangjin.工具.lombok;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:NonNull.java
 * @Description: 空指针 异常提示错误位置的具体字段
 * @author gaoguangjin
 * @Date 2015-4-22 上午10:52:14
 */
@Slf4j
public class NonNullTest {
	public static void main(String[] args) {
		String name = null;
		try {
			// testNullNormal(name);// 2015-04-22 11:03:30,887 ERROR [main] (NonNullTest.java:20) - null
			
			testNullByAnnation(name);// 2015-04-22 11:03:48,036 ERROR [main] (NonNullTest.java:21) - name
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	private static void testNullByAnnation(@NonNull String name) {
		Integer.parseInt(name);
	}
	
	private static void testNullNormal(String name) {
		Integer.parseInt(name);
		
	}
}
