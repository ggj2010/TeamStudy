package com.team.gaoguangjin.acm.chapter14;

public class Interval implements Comparable<Interval> {
	private int low;
	private int high;
	
	public Interval() {}
	
	public Interval(int low, int high) {
		if (low > high) { throw new IllegalArgumentException("low cannot be larger than high"); }
		this.low = low;
		this.high = high;
	}
	
	public int getLow() { return low; }
	public int getHigh() { return high; }
	
	public boolean overlap(Interval i) {
		return high >= i.low && low <= i.high;
	}
	
	public String toString() {
		return String.format("[%d, %d]", low, high);
	}

	public int compareTo(Interval o) {
		return low - o.low;
	}
}