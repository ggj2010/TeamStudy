package com.team.gaoguangjin.javabase.collection.map;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * 采用链表数组实现
 * 如果遇到hash冲突，采用链地址方法
 *
 * @author:gaoguangjin
 * @date 2017/6/27 10:13
 */
@Slf4j
public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
	
	// 初始化容量16
	// static final int DEFAULT_INITIAL_CAPACITY=16;
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
	
	// 2 30次方
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	// 负载因子
	static final float DEFAULT_LOAD_FACTORY = 0.75F;
	
	final Entry<?, ?>[] EMPTY_TABLE = {};
	
	final float loadFactor;
	
	// 这样写法不行
	// transient Entry<K,V>[] table=(Entry<K,V>[]){};
	transient Entry<K, V>[] table = (Entry<K, V>[])EMPTY_TABLE;
	
	transient int size;
	
	// 总容量，为了扩容用的
	int threshold;
	
	int modCount;
	
	public MyHashMap(int capacity, float loadFactor) {
		this.threshold = capacity;
		this.loadFactor = loadFactor;
	}
	
	public MyHashMap() {
		this.threshold = DEFAULT_INITIAL_CAPACITY;
		this.loadFactor = DEFAULT_LOAD_FACTORY;
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new MyHashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.put("d", "4");
		map.put("e", "5");
		map.put(null, "空的");
		log.info(map.get("a"));
		log.info(map.get("b"));
		log.info(map.get("c"));
		log.info(map.get("d"));
		log.info(map.get("e"));
		log.info(map.get(null));
	}
	
	public V get(Object key) {
		if (size == 0) {
			return null;
		}
		int hash = (key == null) ? 0 : hash(key);
		// log.info(" key{} hash=>{},index->{}",key,hash,indexFor(hash, table.length));
		for(Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
			Object k = e.getKey();
			if (e.hash == hash && (k == key || (k != null && key.equals(k)))) {
				return (V)e.getValue();
			}
		}
		return null;
	}
	
	public V put(K key, V value) {
		// 初始化
		if (table == EMPTY_TABLE) {
			initTable();
		}
		// key 等于null 永远放到第一位
		if (key == null) {
			return putForNullKey(value);
		}
		// key 取hash
		int hash = hash(key);
		// 确定key分配到数据的哪个列
		int i = indexFor(hash, table.length);
		// log.info(" key{} hash=>{},index->{}",key,hash,i);
		for(Entry<K, V> e = table[i]; e != null; e = e.next) {
			// 判断是否相等e.getKey().equals(key)&&e.getKey()==key
			// 首先hash相等
			Object k = e.getKey();
			// 如果是string就可以直接判断key.equals(k)
			if (e.hash == hash && (k == key || key.equals(k))) {
				V oldValue = value;
				e.value = value;
				return oldValue;
			}
		}
		// add
		modCount++;
		size++;
		addEntrty(hash, key, value, i);
		return value;
	}
	
	private void addEntrty(int hash, K key, V value, int bucketIndex) {
		// 首先判断是否需要扩容
		if (size >= threshold && (null != table[bucketIndex])) {
			// 每次扩容2倍
			resize(2 * threshold);
		}
		Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
		// 或者下面这种写法
		// if (e == null) {
		// e = new Entry<K, V>(hash, key, value, null);
		// table[bucketIndex]=e;
		// } else {
		// e.next = new Entry<K, V>(hash, key, value, null);
		// }
	}

	/**
	 * resize会导致环形链表问题
	 *
	 * @param newCapacity
     */
	private void resize(int newCapacity) {
		Entry[] oldTable = table;
		int oldCapacity = oldTable.length;
		if (oldCapacity == MAXIMUM_CAPACITY) {
			threshold = Integer.MAX_VALUE;
			return;
		}
		Entry[] newTable = new Entry[newCapacity];
		// 循环老的table
		for(Entry e : table) {
			// 第一个节点不为空
			while(null != e) {
				Entry next = e.next;
				// 因为扩容所以原来的index 需要重新获取,原来entry需要移动位置
				int i = indexFor(e.hash, newCapacity);
				// e下一个节点变换成table[i]
				e.next = newTable[i];
				// table[i]的值就是
				newTable[i] = e;
				e = next;
			}
		}

	}
	
	/**
	 * 让key 散列的更均匀，length必须要是2的倍数
	 * length-1  因为length的长度为偶数
	 * & 与运算只有两个位都是1，结果才是1，
	 * 如果1的个数越多，那么出现1的次数才最多，那么hash 散列的就更均匀
	 * 比如4的二进制是0100
	 * 3的二进制是   4-1=3= 0101
	 * 0001 0010 0011 0101
	 * 1&4，2&4，3&4，4&4 结果值区间范围在{0 、4}
	 * 1&3，2&3，3&3，4&3 结果值区间范围在{0 、1、2、3}
	 * 可以明显看出来如果length如果是2的倍数，则indexFor散列的就跟均匀一点
	 *
	 * @param hash
	 * @param length
	 * @return
	 */
	private int indexFor(int hash, int length) {
		return hash & (length - 1);
	}
	
	private int hash(Object key) {
		// A randomizing value associated with this instance that is applied to
		// * hash code of keys to make hash collisions harder to find. If 0 then
		// int h = hashSeed;
		// 这个数是随机的
		int h = 1000;
		if (key instanceof String) {
			return sun.misc.Hashing.stringHash32((String)key);
		}
		// 异或 两个操作数的位中，相同则结果为0，不同则结果为1
		h ^= key.hashCode();
		// This function ensures that hashCodes that differ only by
		// constant multiples at each bit position have a bounded
		// number of collisions (approximately 8 at default load factor).
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	private V putForNullKey(V value) {
		Entry<K, V> entrty = table[0];
		while(true) {
			if (entrty != null) {
				if (entrty.key == null) {
					V oldValue = entrty.value;
					entrty.value = value;
					return oldValue;
				} else {
					entrty = entrty.next;
					continue;
				}
			} else {
				break;
			}
		}
		addEntrty(0, null, value, 0);
		return null;
	}
	
	// 初始化table
	private void initTable() {
		//
		int capacity = roundUpToPowerOf2(threshold);
		// 容量 默认的是16*0.75=12
		threshold = (int)Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
		table = new Entry[threshold];
	}
	
	// 容量转换成2的倍数，这样在key取hash 然后位移运算可以得到更多的1，这样可以让key散列的更均匀
	private int roundUpToPowerOf2(int number) {
		return number >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY
				: (number > 1) ? Integer.highestOneBit( (number - 1) << 1) : 1;
	}
	
	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return null;
	}
	
	static class Entry<K, V> implements Map.Entry<K, V> {
		
		final K key;
		
		V value;
		
		Entry<K, V> next;
		
		int hash;
		
		Entry(int h, K k, V v, Entry<K, V> n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}
		
		public final K getKey() {
			return key;
		}
		
		public final V getValue() {
			return value;
		}
		
		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
		
		public final boolean equals(Object o) {
			if (! (o instanceof Map.Entry))
				return false;
			Map.Entry e = (Map.Entry)o;
			Object k1 = getKey();
			Object k2 = e.getKey();
			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || (v1 != null && v1.equals(v2)))
					return true;
			}
			return false;
		}
		
		public final int hashCode() {
			return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
		}
		
		public final String toString() {
			return getKey() + "=" + getValue();
		}
	}
}
