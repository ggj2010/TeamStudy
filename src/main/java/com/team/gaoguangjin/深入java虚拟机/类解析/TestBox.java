package com.team.gaoguangjin.深入java虚拟机.类解析;


import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:TestBox.java
 * @Description: 装箱拆箱,测试包装类
 * @author gaoguangjin
 * @Date 2015-2-15 下午3:32:34
 */
@Slf4j
public class TestBox {
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 128;
		Integer f = 128;
		Integer h = 127;
		Integer i = 127;
		
		Long g = 3l;
		
		
		System.out.println(h.toString());
		
		System.out.println(c == d);
		// 优先使用整形池。对于包装类的Integer.valueOf( )方法，如果在－128和127之间，优先调用的是整形池里面的。
		System.out.println(e == f);
		// 127就可以
		log.info("" + (h == i));
		
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
	}
	
	
	/**
	 * toString 默认调用的是包装类的valueOf方法。会优先从常量池里面调用值
	 *  public static Integer valueOf(int i) {
        if(i >= -128 && i <= IntegerCache.high)
            return IntegerCache.cache[i + 128];
        else
            return new Integer(i);
    }
	 */
	
}
