package com.team.gaoguangjin.acm.chapter07;

import com.team.gaoguangjin.acm.chapter02.BubbleSorter;
import com.team.gaoguangjin.acm.chapter02.InsertionSorter;
import com.team.gaoguangjin.acm.chapter02.MergeSorter;
import com.team.gaoguangjin.acm.chapter06.HeapSorter;
import com.team.gaoguangjin.acm.chapter08.LinearTimeSorter;

import java.util.Arrays;
import java.util.Random;


/**
 * 这里的测试并不准确，首先它仅针对整数数组进行测试，另外测试的数据是平均分布的，
 * 这可能使得某些算法相当高效，而另外一些算法相当低效。对于基于比较的排序(除了RadixSorter
 * 和BucketSorter之外其它都是基于比较的排序），它仅仅是反映平均情况。
 * 
 * RadixSorter和BucketSorter都是线性时间的排序，它们都利用了数据的某些特征，RadixSorter
 * 只能对整数进行排序（或许还可以对其它数据进行排序），BucketSorter只有在数据是平均分布时才
 * 有较高的效率，因此这里的测试数据非常适合RadixSorter和BucketSorter，但是是线性时间并不意味
 * 着它们就有着比其它算法更高的效率，原因有些复杂（^_^)。
 * 
 * 下面是在我的机器上测试的效果，时间单位为ms:
 * 
 * <pre>
 *           Bench Name         10        100       1000       5000      10000      20000
 *         BubbleSorter          0          0          2         64        263       1071
 *      InsertionSorter          0          1          0         10         35        152
 *          MergeSorter          0          0          0          1          3          8
 *           HeapSorter          0          1          0          1          2          4
 *          QuickSorter          0          0          0          1          1          3
 *           ArraysSort          0          0          0          1          1          3
 *          RadixSorter          0          0          0          1          2          3
 *         BucketSorter          0          0          1          1          1          5
 * </pre>
 * 
 * 可以发现冒泡排序是所有排序算法中最慢的，而插入排序在数据量较小时有很好的效率。在O(nlgn)效率的算法中
 * 快速排序名副其实，其速度是最快的(ArraySort实际上也是用的是快速排序)，堆排序次之，合并排序最慢，但快
 * 速排序和堆排序都不稳定，只有合并排序是稳定的。在O(n)的排序算法中，基数排序要比桶排序要快一些，从上面
 * 的结果可以看出，即使当数据量达到20000时，基数排序也并不比快速排序占优势。
 * 
 * @author marlon
 */
public class SorterBenchmark {
	private static final boolean DEBUG = true;
	// 是否验证排序的结果的正确性
	private static final boolean CHECK_RESULT = true;
	
	public static void debug(String msg) {
		if (DEBUG) System.out.println(msg);
	}
	
	public static void main(String[] args) {
		SorterBench[] benches = new SorterBench[] {
				new BubbleSorterBench(),
				new InsertionSorterBench(),
				new MergeSorterBench(),
				new HeapSorterBench(),
				new QuickSorterBench(),
				new ArraysSortBench(),
				new RadixSorterBench(),
				new BucketSorterBench(),
		};
		
		// 首先对Sorter进行预热
		debug("prehot ...");
		for (int j = 0; j < 3; j++) {
			int[] a = createArray(100);
			for (int i = 0; i < benches.length; i++) {
				int[] copya = copyArray(a);
				benches[i].sort(copya);
			}
		}
		
		int[] arraySizes = new int[] {
				10, 100, 1000, 5000, 10000, 20000
		};
		int repeatCount = 20;
		
		long[][][] timeResult = new long[benches.length][arraySizes.length][repeatCount];
		
		debug("start benchmark...");
		for (int i = 0; i < arraySizes.length; i++) {
			int size = arraySizes[i];
			int[] a = createArray(size);
			for (int j = 0; j < benches.length; j++) {
				debug("for sorter bench: " + getBenchName(benches[j]) + " - size(" + arraySizes[i] + ")");
				
				SorterBench bench = benches[j];
				if (CHECK_RESULT) {
					debug("checking result ...");
					int[] copya = copyArray(a);
					bench.sort(copya);
					int[] copya2 = copyArray(a);
					Arrays.sort(copya2);
					if (!Arrays.equals(copya, copya2)) {
						throw new RuntimeException(getBenchName(bench) + " algorithm are not correct!");
					}
				}
				
				for (int c = 0; c < repeatCount; c++) {
					debug("\t count " + (c+1));
					int[] copya = copyArray(a);
					long startTime = System.currentTimeMillis();
					bench.sort(copya);
					long endTime = System.currentTimeMillis();
					
					debug("\t consume time " + (endTime - startTime));
					timeResult[j][i][c] = endTime - startTime;
				}
			}
		}
		
		debug("report ...");
		report(benches, arraySizes, timeResult);
	}
	
	/**
	 * ---------------------------------------------------------------
	 * bench name  | size 1  | size 2  | size 3  | size 4  | size 5  |
	 * ---------------------------------------------------------------
	 * 
	 */
	private static void report(SorterBench[] benches, int[] arraySizes, long[][][] timeResult) {
		// print table header
		System.out.printf("%20s", "Bench Name");
		for (int i = 0; i < arraySizes.length; i++) {
			System.out.printf(" %10s", arraySizes[i]);
		}
		System.out.println();
		
		for (int i = 0; i < timeResult.length; i++) {
			System.out.printf("%20s", getBenchName(benches[i]));
			
			for (int j = 0; j < timeResult[i].length; j++) {
				int totalTime = 0;
				for (int k = 0; k < timeResult[i][j].length; k++) {
					totalTime += timeResult[i][j][k];
				}
				int avgTime = totalTime / timeResult[i][j].length;
				
				System.out.printf(" %10s", avgTime);
			}
			System.out.println();
		}
	}

	private static String getBenchName(SorterBench bench) {
		String name = bench.getClass().getSimpleName();
		return name.substring(0, name.length() - "Bench".length());
	}
	
	private static Random random = new Random();
	private static int[] createArray(int num) {
		int[] a = new int[num];
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(num << 1);
		}
		return a;
	}
	
	private static int[] copyArray(int[] a) {
		int[] result = new int[a.length];
		System.arraycopy(a, 0, result, 0, a.length);
		return result;		
	}
	
	public interface SorterBench {
		void sort(int[] a);
	}
	
	public static class BubbleSorterBench implements SorterBench {
		public void sort(int[] a) {
			BubbleSorter.sort(a);
		}
	}
	
	public static class InsertionSorterBench implements SorterBench {
		public void sort(int[] a) {
			InsertionSorter.sort(a);
		}
	}
	
	public static class MergeSorterBench implements SorterBench {
		public void sort(int[] a) {
			MergeSorter.sort(a);
		}
	}
	
	public static class HeapSorterBench implements SorterBench {
		public void sort(int[] a) {
			HeapSorter.sort(a);
		}
	}
	
	public static class QuickSorterBench implements SorterBench {
		public void sort(int[] a) {
			QuickSorter.sort(a);
		}
	}
	
	public static class ArraysSortBench implements SorterBench {
		public void sort(int[] a) {
			Arrays.sort(a);
		}
	}
	
	public static class RadixSorterBench implements SorterBench {
		public void sort(int[] a) {
			LinearTimeSorter.radixSort(a);
		}
	}
	
	public static class BucketSorterBench implements SorterBench {
		public void sort(int[] a) {
			LinearTimeSorter.bucketSort(a, 0, (a.length << 1) - 1);
		}
	}
}
