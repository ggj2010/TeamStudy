package com.team.gaoguangjin.acm.chapter11;

public class OpenAddressHashtable<K, V> {
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	private static final int DEFAULT_INIT_CAP = 8;
	
	private static final boolean CHECK_INVARIANT = true;
	
	@SuppressWarnings("unchecked")
	private static final Entry DELETED = new Entry();
	
	private double loadFactor;
	
	private Entry<K, V>[] table;
	private int size;
	
	public OpenAddressHashtable() {
		this(DEFAULT_LOAD_FACTOR);
	}
	
	public OpenAddressHashtable(double loadFactor) {
		if (loadFactor <= 0 || loadFactor >= 1) {
			throw new IllegalArgumentException("load factor must be between (0, 1)");
		}
		@SuppressWarnings("unchecked")
		Entry<K, V>[] entries = (Entry<K, V>[])new Entry[DEFAULT_INIT_CAP];
		table = entries;
		this.loadFactor = loadFactor;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean put(K key, V value) {
		int freeSlot = findSlot(key);
		boolean inserted = (table[freeSlot] == null || table[freeSlot] == DELETED);
		if (inserted) {
			table[freeSlot] = new Entry<K, V>(key, value);
			size++;
			if (table.length * loadFactor < size) {
				rehash();
			}
		} else {
			table[freeSlot].value = value;
		}
		
		if (CHECK_INVARIANT) checkInvariant();
		return inserted;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		int newLen = table.length << 1;
		while (newLen * loadFactor < size) {
			newLen <<= 1;
		}
		Entry<K, V>[] oldTable = this.table;
		this.table = new Entry[newLen];
		this.size = 0; // clear size
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null && oldTable[i] != DELETED) {
				put(oldTable[i].key, oldTable[i].value);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean delete(K key) {
		int freeSlot = findSlot(key);
		boolean deleted = table[freeSlot] != null && table[freeSlot] != DELETED;
		if (deleted) {
			table[freeSlot] = DELETED;
			size--;
		}
		
		if (CHECK_INVARIANT) checkInvariant();
		return deleted;
	}
	
	public boolean contains(K key) {
		int freeSlot = findSlot(key);
		return table[freeSlot] != null && table[freeSlot] != DELETED;
	}

	public V get(K key) {
		int freeSlot = findSlot(key);
		if (table[freeSlot] == null || table[freeSlot] == DELETED) {
			return null;
		} else {
			return table[freeSlot].value;
		}
	}
	
	/**
	 * 找到一个关键字为key的槽，如果找不到，则返回下一个插入key的槽，这个槽的值要么是null，要么是DELETED。
	 */
	private int findSlot(K key) {
		int freeSlot = -1;
		for (int i = 0; i < table.length; i++) {
			int h = hash(key, i);
			if (table[h] == null || equals(table[h].key, key)) {
				return h;
			} else if (table[h] == DELETED && freeSlot == -1) {
				freeSlot = h;
			}
		}
		// it should never return -1
		return freeSlot;
	}
	
	/*
	 * h(k,i)函数，h(k,0),h(k,1)..h(k,m-1)必须是0,1..m-1的一个排列，其中m
	 * 是table的长度，即table.length。
	 */
	private int hash(K k, int i) {
		// 线性探查，最简单的函数
		 return (k.hashCode() + 3 * i) % table.length;
		
		// 二次探查，但是该如何选择c1,c2呢？这里选择c1=1，c2=3，但是它似乎并不能
		// 使h(k,0), h(k,1)..h(k,m-1)是0,1..m-1的一个排列。
		// return (k.hashCode() + i + 3 * i * i) % table.length;
	}
	
	private void checkInvariant() {
		int len = table.length;
		while (len > 1) {
			if (len % 2 != 0) {
				throw new IllegalStateException(String.format("table.length(%d) is not like 2^n", table.length));
			}
			len = len / 2;
		}
		
		if (loadFactor * table.length < size) {
			throw new IllegalStateException(String.format("loadFactor(%0.2f) * table.length(%d) < size(%d)", loadFactor, table.length, size));
		}
		
		int acutalSize = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && table[i] != DELETED) {
				Entry<K, V> e = table[i];
				boolean found = false;
				for (int j = 0; j < table.length; j++) {
					int h = hash(e.key, j);
					if (table[h] == null) {
						break;
					}
					else if (table[h] != DELETED && equals(table[h].key, e.key)) {
						found = true;
					}
				}
				if (!found)
					throw new IllegalStateException(String.format("Cannot find key \"%s\"", e.key));

				acutalSize++;
			}
		}
		if (size != acutalSize) {
			throw new IllegalStateException(String.format("actual size(%d) not equal size(%d)", acutalSize, size));
		}
		
	}
	
	private static boolean equals(Object o1, Object o2) {
		return o1 == o2 || (o1 != null && o1.equals(o2));
	}
	
	private static class Entry<K, V> {
		K key;
		V value;
		public Entry() {}
		public Entry(K k, V v) { key = k; value = v; }
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Entry)) return false;
			return OpenAddressHashtable.equals(key, ((Entry)o).key);
		}
		public int hashCode() { return key == null ? 0 : key.hashCode(); }
	}
	
	public static void main(String[] args) {
		OpenAddressHashtable<Integer, String> hashtable = new OpenAddressHashtable<Integer, String>();
		hashtable.put(1, "one");
		System.out.println(hashtable.get(1));
		
		hashtable.put(1, "oneone");
		System.out.println(hashtable.get(1));
		
		for (int i = 0; i < 20; i++) {
			hashtable.put(i, "value " + i);
		}
		System.out.println(hashtable.size);
		System.out.println(hashtable.get(1));
		
		hashtable.delete(1);
		System.out.println(hashtable.get(1));
		System.out.println(hashtable.size);
		
	}
}
