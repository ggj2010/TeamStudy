package com.team.gaoguangjin.acm.chapter15;

/**
 * 最长公共子序列问题(Longest common subsequence)。
 */
public class LCS {
	private static final int DIRECTION_XY = 0;
	private static final int DIRECTION_X = 1;
	private static final int DIRECTION_Y = 2;
	
		
	private char[] x; // 输入字符串1
	private char[] y; // 输入字符串2
	
	//计算时的辅助变量
	private int[][] c; // x[0..i]和y[0..j]中最长公共子序列的长度为c[i+1,j+1]
	private int[][] b; //
	
	public LCS(String x, String y) {
		this.x = x.toCharArray();
		this.y = y.toCharArray();
	}
	
	private void reset() {
		c = new int[x.length + 1][y.length + 1];
		b = new int[x.length + 1][y.length + 1];
	}
	
	/**
	 * <pre>
	 * c[i,j] = 0				if i == 0 || j == 0
	 * 		  	c[i-1, j-1]		if i,j > 0 && x[i-1] == y[j-1]
	 * 			max{c[i,j-1], c[i-1,j]}	if i,j > 0 && x[i-1] != y[j-1]
	 * </pre>
	 */
	public int execute() {
		reset();
		// 将数组初始化为0的动作在Java中不是必须的
		for (int j = 0; j < y.length; j++) {
			c[0][j] = 0;
		}
		for (int i = 0; i < x.length; i++) {
			c[i][0] = 0;
		}
		
		for (int i = 1; i <= x.length; i++) {
			for (int j = 1; j <= y.length; j++) {
				if (x[i-1] == y[j-1]) {
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = DIRECTION_XY;
				} else {
					if (c[i][j-1] > c[i-1][j]) {
						c[i][j] = c[i][j-1];
						b[i][j] = DIRECTION_Y;
					} else {
						c[i][j] = c[i-1][j];
						b[i][j] = DIRECTION_X;
					}
				}
			}
		}
		
		return c[x.length][y.length];
	}
	
	private String printLCS() {
		return printLCS(x.length, y.length);
	}
	
	private String printLCS(int i, int j) {
		if (i == 0 || j == 0) return "";
		
		switch (b[i][j]) {
		case DIRECTION_XY:
			return printLCS(i-1, j-1) + x[i-1];
		case DIRECTION_X:
			return printLCS(i-1, j);
		case DIRECTION_Y:
			return printLCS(i, j-1);
		default:
			throw new IllegalStateException("Impossible");
		}
	}
	
	public static void main(String[] args) {
		LCS lcs = new LCS("ABCBDAB", "BDCABA");
		System.out.println("max length: " + lcs.execute());		
		System.out.println("LCS:" + lcs.printLCS());
		
		System.out.println("\n\n");
		lcs = new LCS("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGTAAA");
		System.out.println("max length: " + lcs.execute());		
		System.out.println("LCS:" + lcs.printLCS());
	}
}
