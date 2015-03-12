package com.team.gaoguangjin.javabase.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.map.LinkedMap;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/**
 * @ClassName:MapTypeTest.java
 * @Description:测试map种类
 * @author gaoguangjin
 * @Date 2015-3-11 下午5:12:02
 */
@Slf4j
public class MapTypeTest {
	public static void main(String[] args) {
		// test();
		// hashMap();
		// hashTable();
		// LinkedHashMap();
		treeMap();
	}
	
	public static void test() {
		// 1
		Map<String, String> hashMap = new HashMap<String, String>();
		
		// Map<String, String> linkedMap = new LinkedMap<String, String>();这样写报错，因为方法不支持
		Map<String, String> linkedMap = new LinkedMap();
		
		Map<String, String> treeMap = new TreeMap<String, String>();
		
		SortedMap<String, String> sortMap = new TreeMap<String, String>();
		
		Hashtable hashTable = new Hashtable();
		
		hashMap();
		
	}
	
	/**
	 * @Description: map不能存放重复的数据,元素可以为空，不是想线程安全
	 */
	private static void hashMap() {
		Map<String, String> hashMap = new HashMap<String, String>();
		
		String null1 = hashMap.put(null, "空值");
		String null2 = hashMap.put(null, "空值d");
		
		System.out.println(null1);
		System.out.println(null2);
		
		String add = hashMap.put("a", "dd");
		String addd = hashMap.put("a", "dd");
		String adddd = hashMap.put("a", "dddd");
		String ddddd = hashMap.put("d", "d重复");
		
		System.out.println(add);
		System.out.println(addd);
		System.out.println(adddd);
		System.out.println(ddddd);
		// /System.out.println(hashMap.get(null));
	}
	
	/**
	 * @Description:主键key不可以为空，线程安全的
	 */
	private static void hashTable() {
		try {
			Hashtable hashTable = new Hashtable();
			hashTable.put(null, 123);// 会报错
		} catch (Exception e) {
			log.error("hashTable不能put 空值Key:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description:linkedMap 有序
	 */
	private static void LinkedHashMap() {
		Map<String, String> linkedMap = new LinkedHashMap();
		linkedMap.put("a", "1");
		linkedMap.put("b", "2");
		linkedMap.put("c", "3");
		linkedMap.put("d", "4");
		for (Iterator<String> itreator = linkedMap.keySet().iterator(); itreator.hasNext();)
			log.info("linkedMap:有序" + linkedMap.get(itreator.next()));
		
		Map<String, String> hashMap = new HashMap<String, String>();
		
		hashMap.put("a", "1");
		hashMap.put("b", "2");
		hashMap.put("c", "3");
		hashMap.put("d", "4");
		for (Iterator<String> itreator = hashMap.keySet().iterator(); itreator.hasNext();)
			log.info("hashMap无序:" + hashMap.get(itreator.next()));
	}
	
	private static void treeMap() {
		
	}
}
