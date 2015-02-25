package com.team.gaoguangjin.javabase.list.quene;

import java.util.LinkedList;
import java.util.Queue;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: TestQuene.java
 * @Description:
 * @author gaoguangjin
 * @Date 2015-2-9 上午10:54:54
 */
@Slf4j
public class TestQueue {
	public static void main(String[] args) {
		demo();
	}
	
	private static void demo() {
		Queue<String> queue = new LinkedList<String>();
		queue.add("1");
		queue.add("2");
		queue.add("3");
		log.info("queue.size():" + queue.size());
		String str = "";
		while ((str = queue.poll()) != null) {
			log.info(str);
		}
		
		log.info("queue.size():" + queue.size());
	}
}
