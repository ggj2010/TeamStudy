package com.team.gaoguangjin.acm.chapter15;

public class AssemblyLineScheduler {
	private int e1;			// 进入装配线1的时间
	private int e2;			// 进入装配线2的时间
	private int x1;			// 离开装配线1的时间
	private int x2;			// 离开装配线2的时间
	private int[] a1;		// 装配线1上每个站点用的时间
	private int[] a2;		// 装配线2上每个站点用的时间
	private int[] t1;		// 从装配线1站点移到装配线2下一个站点用的时间，其长度为a.length-1
	private int[] t2;		// 从装配线2站点移到装配线1下一个站点用的时间
	
	// 计算最快路线过程中所用的辅助变量
	private int[] f1;		// 从入站到装配线1站点所用的最短时间		
	private int[] f2;		// 从入站到装配线1站点所用的最短时间
	private int[] l1;		// 记录从入站到装配线1站点的最快路线，其值为1或2,分别代表装配线1或装配线2
	private int[] l2;		// 记录从入站到装配线2站点的最快路线，其值为1或2,分别代表装配线1或装配线2
	private int l;			// 最后一个装配线是线1还是线2？
	private int f;			// 总共用的最短时间
	
	public AssemblyLineScheduler(int e1, int e2, int x1, int x2, int[] a1, int[] a2, int[] t1, int[] t2) {
		assert a1.length == a2.length && a1.length > 0;
		assert t1.length == t2.length && t1.length == a1.length - 1;
		
		this.e1 = e1;
		this.e2 = e2;
		this.x1 = x1;
		this.x2 = x2;
		this.a1 = a1;
		this.a2 = a2;
		this.t1 = t1;
		this.t2 = t2;
	}
	
	private void reset() {
		int len = a1.length;
		f1 = new int[len];
		f2 = new int[len];
		l1 = new int[len-1];
		l2 = new int[len-1];
		f = -1;
		l = -1;
	}
	
	/**
	 * 使用动态规划方法来计算最快路线。
	 * 
	 * <pre>
	 * f1[j] = 		e1 + a1[0]		if j == 0
	 * 				min(f1[j-1] + a1[j], f2[j-1] + t2[j-1] + a1[j]) if j > 0
	 * 
	 * f2[j] = 		e2 + a2[0]		if j == 0
	 * 				min(f2[j-1] + a2[j], f1[j-1] + t1[j-1] + a2[j]) if j > 0
	 * </pre>
	 * NOTE: 上面的索引都是从0开始
	 */
	public int schedule() {
		reset(); // 重新设置辅助变量
		
		// 计算f1, f2, t1, t2的值
		f1[0] = e1 + a1[0];
		f2[0] = e2 + a2[0];
		
		for (int j = 1; j < a1.length; j++) {
			int v1 = f1[j-1] + a1[j];
			int v2 = f2[j-1] + t2[j-1] + a1[j];
			if (v1 <= v2) {
				l1[j-1] = 1;
				f1[j] = v1;
			} else {
				l1[j-1] = 2;
				f1[j] = v2;
			}
			
			v1 = f2[j-1] + a2[j];
			v2 = f1[j-1] + t1[j-1] + a2[j];
			if (v1 <= v2) {
				l2[j-1] = 2;
				f2[j] = v1;
			} else {
				l2[j-1] = 1;
				f2[j] = v2;
			}
		}
		
		int v1 = f1[a1.length-1] + x1;
		int v2 = f2[a1.length-1] + x2;
		if (v1 <= v2) {
			l = 1;
			f = v1;
		} else {
			l = 2;
			f = v2;
		}
		
		return f;
	}
	
	public void printDecreasingStations() {
		System.out.println("line " + l + ", station " + l1.length);
		int s = l;
		for (int i = l1.length - 1; i >= 0; i--) {
			s = (s == 1) ? l1[i] : l2[i];
			System.out.println("line " + s + ", station " + i);
		}
	}
	
	public void printStations() {
		printStations(l1.length - 1, l);
		System.out.println("line " + l + ", station " + l1.length);
	}
	
	private void printStations(int i, int s) {
		if (i == -1) return;
		
		int s_1 = s == 1 ? l1[i] : l2[i];
		printStations(i - 1, s_1);
		System.out.println("line " + s_1 + ", station " + i);
	}
	
	public static void main(String[] args) {
		AssemblyLineScheduler s = new AssemblyLineScheduler(
				2, 4,
				3, 2,
				new int[] {7, 9, 3, 4, 8, 4},
				new int[] {8, 5, 6, 4, 5, 7},
				new int[] {2, 3, 1, 3, 4},
				new int[] {2, 1, 2, 2, 1});
		System.out.println("fast time: " + s.schedule());
		
		System.out.println("descending stations...");
		s.printDecreasingStations();
		
		
		System.out.println("\ndescending stations...");
		s.printStations();
	}
}
