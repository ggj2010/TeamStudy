package com.team.gaoguangjin.javabase.string;

import java.util.StringTokenizer;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: StringTokenizerTest.java
 * @Description: 测试和学习StringTokenizer
 * @author gaoguangjin
 * @Date 2015-2-10 下午12:21:57
 */
@Slf4j
public class StringTokenizerTest {
	public static void main(String[] args) {
		// demo1();
		testSplit();
	}
	
	/**
	 * @Description: 测试截取效率.
	 * 
	 * 如果循环次数多 一直 new StringTokenizer()会导致性能降低的
	 */
	private static void testSplit() {
		
		String needSplitStr = getSplitStr();
		System.out.println(needSplitStr);
		long begintime1 = System.currentTimeMillis();
		// for (int i = 0; i < 20; i++) {
		String[] st = needSplitStr.split(",");
		for (String str : st) {
			System.out.print(str + "A");
		}
		// }
		long endtime1 = System.currentTimeMillis();
		
		long begintime2 = System.currentTimeMillis();
		// for (int i = 0; i < 20; i++) {
		StringTokenizer stz = new StringTokenizer(needSplitStr, ",");
		
		while (stz.hasMoreTokens()) {
			System.out.print(stz.nextToken() + "A");
		}
		
		// }
		
		long endtime2 = System.currentTimeMillis();
		
		log.info("split()耗费时间：" + (endtime1 - begintime1) + "ms");
		log.info("StringTokenizer()耗费时间：" + (endtime2 - begintime2) + "ms");
		
	}
	
	private static String getSplitStr() {
		String str = "";
		for (int i = 0; i < 1000; i++) {
			str = str + "," + i;
		}
		return str;
	}
	
	/**
	 * @Description: 1.构造函数 public StringTokenizer(String str)
	 * 
	 * public StringTokenizer(String str, String delim)
	 * 
	 * public StringTokenizer(String str, String delim, boolean returnDelims)
	 * 
	 * 第一个参数就是要分隔的String，第二个是分隔字符集合， 第三个参数表示分隔符号是否作为标记返回，如果不指定分隔字符，默认的是：”\t\n\r\f”
	 */
	private static void demo1() {
		String str = "1,2,3,4,5" + "\r\t" + "1,2,3 4 5";
		// 默认以,换行,空格,分割
		StringTokenizer stz = new StringTokenizer(str);
		while (stz.hasMoreTokens()) {
			log.info(stz.nextToken());
		}
		
		log.info("===============分割线==============");
		String splitStr = "a,b,c";
		StringTokenizer stzSplit = new StringTokenizer(splitStr, ",");
		// while (stzSplit.hasMoreTokens()) {
		// log.info(stzSplit.nextToken());
		// }
		
	}
}
