package com.team.gaoguangjin.深入java虚拟机.类解析;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:CodeBlock.java
 * @Description: 代码块
 * @author gaoguangjin
 * @Date 2015-2-15 下午2:34:31
 */
@Slf4j
public class CodeBlock extends Parent {
	static {
		log.info("我是子类的static代码块");
	}
	// 构造代码块
	{
		log.info("我是子类的构造函数代码块");
	}
	// 构造代码块
	{
		log.info("【2】");
	}
	
	public static void main(String[] args) {
		// 构造代码块
		Parent parent = new CodeBlock();
	}
	
}
