package com.team.gaoguangjin.acm.chapter15;

public class MatrixChainMultiplication {
	private int[] p; // 第i个矩阵的大小为p[i]*p[i+1], 其中 0 <= i < p.length
	
	// 用于计算的辅助空间
	private int[][] m;
	private int[][] s;
	
	public MatrixChainMultiplication(int[] p) {
		this.p = p;
	}
	
	private void reset() {
		m = new int[p.length - 1][p.length - 1];
		s = new int[p.length - 1][p.length - 1];
	}
	
	/**
	 * <pre>
	 * m[i,j] = 0				if i == j
	 * 			min{m[i,k] + m[k+1,j] + p[i]p[k+1]p[j+1]} if i != j, 其中i<=k<j
	 * </pre>
	 */
	public int execute() {
		reset();
		// 在java中，下列语句不是必须的，因为数组会被自动初始化为0
		for (int i = 0; i < m.length; i++) {
			m[i][i] = 0;
		}
		for (int d = 1; d < m.length; d++) {
			for (int i = 0; i + d < m.length; i++) {
				int j = i + d;
				int min = m[i][i] + m[i+1][j] + p[i]*p[i+1]*p[j+1];
				s[i][j] = i;
				for (int k = i + 1; k < j; k++) {
					int v = m[i][k] + m[k+1][j] + p[i]*p[k+1]*p[j+1];
					if (min > v) {
						min = v;
						s[i][j] = k;
					}
				}
				m[i][j] = min;
			}
		}
		return m[0][m.length - 1];
	}
	
	public String printOptimalParents() {
		return printOptimalParents(0, m.length - 1);
	}
	
	private String printOptimalParents(int i, int j) {
		if (i == j) return "A" + i;
		int k = s[i][j];
		return "(" + printOptimalParents(i, k) + printOptimalParents(k + 1, j) + ")"; 
	}
	
	public static void main(String[] args) {
		MatrixChainMultiplication mcm = new MatrixChainMultiplication(
				new int[] {30, 35, 15, 5, 10, 20, 25});
		System.out.println("min multiplication count: " + mcm.execute());		
		System.out.println(mcm.printOptimalParents());
		
		System.out.println("\n\n");
		mcm = new MatrixChainMultiplication(
				new int[] {5, 10, 3, 13, 5, 50, 6});
		System.out.println("min multiplication count: " + mcm.execute());		
		System.out.println(mcm.printOptimalParents());
	}
}
