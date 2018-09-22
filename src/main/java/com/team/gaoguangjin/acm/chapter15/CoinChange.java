package com.team.gaoguangjin.acm.chapter15;

/**
 * 找零钱。见第16章的思考题1：16-1的d)问题
 */
public class CoinChange {
	private int[] coins;
	private int cents;
	
	// 辅助变量 
	private int[][] s;
	private int[][] m;
	
	public CoinChange(int[] coins, int cents) {
//		if (coins[0] != 1) {
//			throw new IllegalArgumentException("first coins must be 1");
//		}
		this.coins = coins;
		this.cents = cents;
	}
	
	private void reset() {
		s = new int[coins.length][cents + 1];
		m = new int[coins.length][cents + 1];
	}
	
	/**
	 * <pre>
	 * s[i,j] = 0				if j==0
	 * 			j				if i==0
	 * 			min{s[i-1,j], s[i,j-coins[i]] + 1}	if i,j != 0
	 * </pre>
	 */
	public int execute() {
		reset();
		
		for (int j = 0; j < s[0].length; j++) {
			if (j%coins[0] == 0) {
				s[0][j] = j/coins[0];
			} else {
				s[0][j] = -1; // 不能表示
			}
		}
		for (int i = 0; i < s.length; i++) {
			s[i][0] = 0;
		}
		for (int i = 1; i < s.length; i++) {
			for (int j = 1; j < s[i].length; j++) {
				int v1 = s[i-1][j];
				int v2 = -1;
				
				if (j -coins[i] >= 0 && s[i][j-coins[i]] >= 0) {
					v2 = s[i][j-coins[i]] + 1;
				}
				
				if (v1 < 0 && v2 < 0) {
					s[i][j] = -1;
					m[i][j] = -1;
				} else if (v1 >= 0 && (v2 < 0 || v1 < v2)) {
					s[i][j] = v1;
					m[i][j] = 0;
				} else {
					s[i][j] = v2;
					m[i][j] = coins[i];
				}
			}
		}
		
		if (s[coins.length-1][cents] < 0) {
			throw new RuntimeException("Cannot change coins");
		}
		return s[coins.length-1][cents];
	}
	
	public String printCoins() {
		return printCoins(coins.length - 1, cents);
	}
	
	private String printCoins(int i, int j) {
		if (i == 0) {
			String result = String.valueOf(coins[0]);
			for (int k = 1; k < j / coins[0]; k++) {
				result += " + " + coins[0];
			}
			return result;
		}
		if (m[i][j] == 0) {
			return printCoins(i-1, j);
		} else {
			return j-coins[i] == 0 ? String.valueOf(coins[i]) : printCoins(i, j-coins[i]) + " + " + coins[i];
		}
	}
	
	public static void main(String[] args) {
		CoinChange cc = new CoinChange(new int[] {5, 8, 1}, 10);
		System.out.println("min coins: " + cc.execute());
		System.out.println(cc.cents + " = " + cc.printCoins());
		

		cc = new CoinChange(new int[] {13, 1, 10, 7}, 152);
		System.out.println("min coins: " + cc.execute());
		System.out.println(cc.cents + " = " + cc.printCoins());
		
		cc = new CoinChange(new int[] {1, 25, 10, 5}, 92);
		System.out.println("min coins: " + cc.execute());
		System.out.println(cc.cents + " = " + cc.printCoins());
		

		cc = new CoinChange(new int[] {3, 5}, 22); // try to change 22 to 7
		System.out.println("min coins: " + cc.execute());
		System.out.println(cc.cents + " = " + cc.printCoins());
	}
}
