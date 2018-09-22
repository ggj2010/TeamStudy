package com.team.gaoguangjin.acm.chapter11;


public class ChainingHashtable<K, V> {
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	private static final int DEFAULT_INIT_CAP = 8;
	
	private static final boolean CHECK_INVARIANT = true;
	
	private Entry<K, V>[] table;
	private double loadFactor;
	private int size;
	
	
	public ChainingHashtable() {
		this(DEFAULT_LOAD_FACTOR);
	}
	
	public ChainingHashtable(double loadFactor) {
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
	
	private int hash(K key) {
		return key.hashCode() % table.length; // 由于这里table的长度是2^n，因此直接返回key.hashCode不是一个好的做法，
							   				  // 凡是hashCode的低n位相同就会发生冲突，可以见Java中的HashMap的实现
	}
	
	public boolean put(K key, V value) {
		Entry<K, V> entry = findEntry(key);
		if (entry == null) {
			table[hash(key)] = new Entry<K, V>(key, value);
			size++;
			if (table.length * loadFactor < size) {
				rehash();
			}
			
			if (CHECK_INVARIANT) checkInvariant();
			return true;
		} else {
			entry.value = value;
			return false;
		}
	}

	private Entry<K, V> findEntry(K key) {
		int h = hash(key);
		Entry<K, V> entry = table[h];
		while (entry != null) {
			if (equals(key, entry.key)) return entry;
			entry = entry.next;
		}
		return null;
	}

	private static boolean equals(Object o1, Object o2) {
		return o1 == o2 || (o1 != null && o1.equals(o2));
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
			Entry<K, V> entry = oldTable[i];
			while (entry != null) {
				put(entry.key, entry.value);
				entry = entry.next;
			}
		}
	}

	public boolean delete(K key) {
		int h = hash(key);
		Entry<K, V> prev = null;
		Entry<K, V> entry = table[h];
		while (entry != null) {
			if (equals(key, entry.key)) break;
			prev = entry;
			entry = entry.next;
		}
		
		if (entry == null) {
			return false;
		} else {
			if (prev == null) {
				table[h] = null;
			} else {
				prev.next = entry.next;
			}
			size--;

			if (CHECK_INVARIANT) checkInvariant();
			return true;
		}
	}
	
	private void checkInvariant() {		
		if (loadFactor * table.length < size) {
			throw new IllegalStateException(String.format("loadFactor(%0.2f) * table.length(%d) < size(%d)", loadFactor, table.length, size));
		}
		
		int acutalSize = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				Entry<K, V> e = table[i];
				while (e != null) {
					if (hash(e.key) != i) {
						throw new IllegalStateException(String.format("key(%s) is not in correct slot, hash is (%d) but position is (%d)", e.key, hash(e.key), i));
					}
					e = e.next;
					acutalSize++;
				}

			}
		}
		if (size != acutalSize) {
			throw new IllegalStateException(String.format("actual size(%d) not equal size(%d)", acutalSize, size));
		}
		
	}
	
	public boolean contains(K key) {
		return findEntry(key) != null;
	}

	public V get(K key) {
		Entry<K, V> entry = findEntry(key);
		return entry == null ? null : entry.value;
	}
	
	private static class Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry(K k, V v) { this.key = k; this.value = v;}
		public Entry(K k, V v, Entry<K, V> n) {
			this(k, v);
			this.next = n;
		}
	}
	
	public static void main(String[] args) {
		ChainingHashtable<Integer, String> hashtable = new ChainingHashtable<Integer, String>();
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
