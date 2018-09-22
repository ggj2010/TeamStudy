package com.team.gaoguangjin.acm.chapter16;

import java.util.Arrays;
import java.util.LinkedList;

public class ActivitySelection {
	private int[] start;	// 开始时间
	private int[] finish;	// 结束时间
	
	public ActivitySelection(int[] s, int[] f) {
		assert s.length == f.length;
		
		start = s;
		finish = f;
	}
	
	public ActivitySelection(int[] s, int[] f, boolean sort) {
		assert s.length == f.length;
		if (!sort) {
			// 按结束时间进行插入排序
			for (int i = 1; i < s.length; i++) {
				for (int j = i; j > 0 && f[j] < f[j-1]; j--) {
					swap(s, j, j-1);
					swap(f, j, j-1);
				}
			}
		}
		
		start = s;
		finish = f;
	}
	
	private void swap(int[] a, int i, int j) {
		int temp = a[i]; a[i] = a[j]; a[j] = temp;
	}
	
	public int[] recurisveSelect() {
		LinkedList<Integer> activities = new LinkedList<Integer>();
		recursiveSelect(activities, -1, start.length);
		
		return toIntArray(activities);
	}

	private int[] toIntArray(LinkedList<Integer> activities) {
		int[] result = new int[activities.size()];
		int i = 0;
		for (int activity : activities) {
			result[i++] = activity;
		}
		return result;
	}
	
	private void recursiveSelect(LinkedList<Integer> activities, int i, int j) {
		int m = i + 1;
		while ((i >= 0 && m < j && start[m] < finish[i]) || i >= start.length) {
			m++;
		}
		if (m < j) {
			activities.add(m);
			recursiveSelect(activities, m, j);
		}
	}
	
	public int[] select() {
		LinkedList<Integer> activities = new LinkedList<Integer>();
		activities.add(0); // add first activity

		int i = 0;
		for (int m = 1; m < start.length; m++) {
			if (start[m] >= finish[i]) {
				activities.add(m);
				i = m;
			}
		}
		return toIntArray(activities);
	}
	
	public static void main(String[] args) {
		int[] s = new int[] {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
		int[] f = new int[] {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
		
		ActivitySelection as = new ActivitySelection(s, f);
		int[] acts = as.recurisveSelect();
		System.out.println("recursively select activites: " + Arrays.toString(acts));
		
		acts = as.select();
		System.out.println("\nselect activites: " + Arrays.toString(acts));
		
		s = new int[] {5, 1, 3,  8, 0, 3, 5,  6,  8,  2, 12};
		f = new int[] {7, 4, 5, 11, 6, 8, 9, 10, 12, 13, 14};
		as = new ActivitySelection(s, f, false);
		acts = as.recurisveSelect();
		System.out.println("\nrecursively select activites: " + Arrays.toString(acts));
		
		acts = as.select();
		System.out.println("\n");
		System.out.println("select activites: " + Arrays.toString(acts));
	}
}
