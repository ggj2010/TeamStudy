package com.team.gaoguangjin.acm.chapter02;

import java.util.Comparator;

public class BubbleSorter {
    public static void sort(int[] a) {
        sort(a, 0, a.length);
    }

    public static void sort(int[] a, int fromIndex, int toIndex) {
        // i 为内层循环比较次数
        for (int i = a.length - 1; i >= 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Object[] a) {
        sort(a, 0, a.length);
    }

    @SuppressWarnings("unchecked")
    public static void sort(Object[] a, int fromIndex, int toIndex) {
        // i 为内层循环比较次数
        for (int i = a.length - 1; i >= 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                if (((Comparable) a[j]).compareTo(a[j + 1]) > 0) {
                    swap(a, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }


    public static <T> void sort(T[] a, Comparator<? super T> c) {
        sort(a, 0, a.length, c);
    }

    public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
        // i 为内层循环比较次数
        for (int i = a.length - 1; i >= 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                if (c.compare(a[j], a[j + 1]) > 0) {
                    swap(a, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }
}
