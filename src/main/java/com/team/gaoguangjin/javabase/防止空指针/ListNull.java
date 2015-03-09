package com.team.gaoguangjin.javabase.防止空指针;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * @ClassName:ListNull.java
 * @Description: list空指针判断
 * @author gaoguangjin
 * @Date 2015-3-2 下午12:54:55
 */
public class ListNull {
	public static void main(String[] args) {
		normal();
		useCollection();
	}
	
	private static void normal() {
		List<String> list = new ArrayList<String>();
		// 因为有些list就算不是空，也有可能size为0
		if (null != list && list.size() > 0) {
			System.out.println(list.get(0));
		}
	}
	
	private static void useCollection() {
		List<String> list = new ArrayList<String>();
		if (CollectionUtils.isEmpty(list)) {
			System.out.println("这个list是空的");
		}
	}
}
