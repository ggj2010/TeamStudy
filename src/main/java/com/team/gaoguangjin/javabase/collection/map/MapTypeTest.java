package com.team.gaoguangjin.javabase.collection.map;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.map.LinkedMap;


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
		LinkedHashMap();
		// treeMap();
		
	}
	
	public static void test() {
		// 1
		Map<String, String> hashMap = new HashMap<String, String>();
		
		// Map<String, String> linkedMap = new LinkedMap<String, String>();这样写报错，因为方法不支持
		Map<String, String> linkedMap = new LinkedMap();
		
		Map<String, String> treeMap = new TreeMap<String, String>();
		
		SortedMap<String, String> sortMap = new TreeMap<String, String>();
		
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
		//
		// 按照修改时间的map排序
		
		try {
			Map<String, String> linkedMapByUpdateTime = new LinkedHashMap(16, 0.75f, true);
			linkedMapByUpdateTime.put("a", "1");
			linkedMapByUpdateTime.put("b", "2");
			linkedMapByUpdateTime.put("c", "3");
			linkedMapByUpdateTime.put("d", "4");
			for (Iterator<String> itreator = linkedMapByUpdateTime.keySet().iterator(); itreator.hasNext();)
				log.info("new LinkedHashMap(16, 0.75f, true)" + linkedMapByUpdateTime.get(itreator.next()));
		} catch (ConcurrentModificationException e) {
			// java.util.ConcurrentModificationException
			log.info("new LinkedHashMap(16, 0.75f, true) 按照最后访问时间排序，不能再迭代里面get：" + e.getMessage());
		}
		
	}
	
	private static void treeMap() {
		
		Map<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("e", "1");
		treeMap.put("c", "3");
		treeMap.put("b", "4");
		treeMap.put("a", "5");
		treeMap.put("d", "2");
		
		for (Iterator<String> itreator = treeMap.keySet().iterator(); itreator.hasNext();)
			log.info("TreeMap<String, String>:" + treeMap.get(itreator.next()));
		// 按照key元素的固有顺序排序
		
		// 是有一个实现了comparable接口的实体类
		
		log.info("=======================");
		
		Map<TreeValue, String> treeMapComparable = new TreeMap<TreeValue, String>();
		treeMapComparable.put(new TreeValue(3), "c");
		treeMapComparable.put(new TreeValue(2), "b");
		treeMapComparable.put(new TreeValue(5), "d");
		treeMapComparable.put(new TreeValue(1), "a");
		for (Iterator<TreeValue> itreator = treeMapComparable.keySet().iterator(); itreator.hasNext();)
			log.info("treeMapComparable<TreeValue, Integer>:" + (treeMapComparable.get(itreator.next())));
		// a,b,c,d 按照 key的从小到大排序
		
		log.info("treeMapSort=======================treeMapSort");
		
		TreeMap treeMapSort = new TreeMap();// 默认按照key的值 从小到大排序
		treeMapSort.put(7, "d");
		treeMapSort.put(3, "b");
		treeMapSort.put(5, "c");
		treeMapSort.put(1, "a");
		for (Iterator itreator = treeMapSort.keySet().iterator(); itreator.hasNext();)
			log.info("treeMapSort:" + (treeMapSort.get(itreator.next())));
		
		log.info("sortBetween=======================treeMapSort");
		SortedMap sortBetween = treeMapSort.subMap(1, 3);// key值在1到3 包括1不包扣3 >= <
		for (Iterator itreator = sortBetween.keySet().iterator(); itreator.hasNext();)
			log.info("treeMapSort:在 key 1和3直接的有：" + (sortBetween.get(itreator.next())));
		
		log.info("sortHead=======================treeMapSort");
		SortedMap sortHead = treeMapSort.headMap(4);// key值低于4的有 不包括4的值 <
		for (Iterator itreator = sortHead.keySet().iterator(); itreator.hasNext();)
			log.info("sortHead:key值低于4的有：" + (sortHead.get(itreator.next())));
		
		log.info("sortTail=======================sortTail");
		SortedMap sortTail = treeMapSort.tailMap(4);// key值低于4的有 包括4的值》=
		for (Iterator itreator = sortTail.keySet().iterator(); itreator.hasNext();)
			log.info("sortTail:值大于4的有：" + (sortTail.get(itreator.next())));
		
	}
	
	public static class TreeValue implements Comparable<TreeValue> {
		int score;
		
		public TreeValue(int score) {
			this.score = score;
		}
		
		public int compareTo(TreeValue o) {
			if (o.score < this.score)
				return 1;
			else if (o.score > this.score)
				return -1;
			else
				return 0;
		}
		
	}
	
}
