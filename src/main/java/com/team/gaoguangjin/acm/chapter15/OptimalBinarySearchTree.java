package com.team.gaoguangjin.acm.chapter15;

public class OptimalBinarySearchTree {
	private double[] p;	//关键字出现的概率
	private double[] q; //关键字不现出的概率
	
	// 辅助变量
	private double[][] e;
	private double[][] w;
	private int[][] root;
	
	public OptimalBinarySearchTree(double[] p, double[] q) {
		assert p.length + 1 == q.length;
		double total = 0;
		for (int i = 0; i < p.length; i++) {
			total += p[i] + q[i];
		}
		total += q[q.length - 1];
		if (Math.abs(total - 1) >= 1e-5) {
			throw new IllegalArgumentException("total should be equals to 1");
		}
		
		this.p = p;
		this.q = q;
	}
	
	private void reset() {
		e = new double[p.length + 1][p.length + 1];
		w = new double[p.length + 1][p.length + 1];
		root = new int[p.length + 1][p.length + 1];
	}
	
	/**
	 * <cite>一棵包含ki, ..., kj的二叉树，必定也包含虚拟键di, ..., dj+1作为叶子。</cite>
	 * 
	 * e[i,j] 表示由关键字ki,...,kj-1（不包括kj)构成的树的最小搜索代价。
	 * 
	 * 递推式：
	 * <pre>
	 * e[i,j] = q[i]								if i == j
	 * 			min{e[i,r] + e[r+1,j] + w(i, j) 	if i <= j, 其中i<=r<j
	 * 
	 * 其中w(i,j) = sum[i<=l<j]p[l] + sum[i<=l<=j]q[l]
	 * </pre>
	 * 
	 * 注意：这里的定义与教材上的有区别，e[i,j],w[i,j]并不包括关键字kj。
	 */
	public double execute() {
		reset();
		
		for (int i = 0; i < e.length; i++) {
			e[i][i] = q[i];
			w[i][i] = q[i];
		}
		
		for (int d = 1; d <= e.length; d++) {
			for (int i = 0; i + d < e.length; i++) {
				int j = i + d;
				
				w[i][j] = w[i][j-1] + p[j-1] + q[j]; 
				double min = e[i][i] + e[i+1][j] + w[i][j];
				root[i][j] = i;
				for (int r = i + 1; r < j; r++) {
					double v = e[i][r] + e[r+1][j] + w[i][j];
					if (min > v) {
						min = v;
						root[i][j] = r;
					}
				}
				e[i][j] = min;
			}
		}
		
		return e[0][e.length - 1];
	}
	
	public void printTree() {
		System.out.println("k" + root[0][e.length - 1] + " is root");
		printTree(0, e.length - 1);
	}
	
	private void printTree(int i, int j) {
		if (i == j) {
			return;
		}
		int r = root[i][j];
		if (r > i) {
			int left = root[i][r];
			System.out.println("k"+ left + " is the left child of k" + r);
		} else { // r == i
			System.out.println("d" + i + " is the left child of k" + r);
		}
		
		if (j > r + 1) {
			int right = root[r+1][j];
			System.out.println("k"+ right + " is the right child of k" + r);
		} else { // j == r + 1
			System.out.println("d" + j + " is the right child of k" + r);
		}
		printTree(r+1, j);
		printTree(i, r);
	}
	
	public static void main(String[] args) {
		OptimalBinarySearchTree obs = new OptimalBinarySearchTree(
				new double[]{0.15, 0.10, 0.05, 0.10, 0.20}, 
				new double[]{0.05, 0.10, 0.05, 0.05, 0.05, 0.10});
		System.out.println("minimum cost: " + obs.execute());		
		obs.printTree();
		
		System.out.println("\n\n");
		obs = new OptimalBinarySearchTree(
				new double[]{0.04, 0.06, 0.08, 0.02, 0.10, 0.12, 0.14},
				new double[]{0.06, 0.06, 0.06, 0.06, 0.05, 0.05, 0.05, 0.05});
		System.out.println("minimum cost: " + obs.execute());		
		obs.printTree();
	}
}
