package com.team.gaoguangjin.javabase.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

@Slf4j
public class RandomAccessFileTest {
	public final static int _1KB = 1024 * 1024;
	public final static int _20M = 20 * _1KB;
	public final static String sqlName = "d:/ftp/" + "${number}" + "_csdn.sql";
	
	public static void main(String[] args) throws Exception {
		long beginTime = System.currentTimeMillis();
		test();
		long endTime = System.currentTimeMillis();
		log.info("耗费时间：" + (endTime - beginTime) + "ms");
	}
	
	private static void test2() throws FileNotFoundException, IOException {
		BufferedReader a = new BufferedReader(new FileReader(new File("d:/ftp/csdn-0.txt")));
		a.readLine();
	}
	
	private static void test() {
		try {
			File file = new File("d:/ftp/csdn.sql");
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			long length = raf.length();
			// 每个文件大小为20*1024byte
			int sum = (int) (length / _20M);
			for (int i = 0; i <= sum; i++) {
				// 读20m
				readByLength(i, raf);
			}
			
			readyByThread(sum);
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
		
	}
	
	private static void readyByThread(int sum) {
		ExecutorService pool = Executors.newFixedThreadPool(sum);
		for (int i = 0; i < sum; i++) {
			pool.execute(new ThreadRead(sum));
		}
		pool.shutdown();
		System.out.println("=======");
	}
	
	private static void readByLength(int i, RandomAccessFile raf) throws IOException {
		raf.seek(i * _20M);
		byte[] by = new byte[_20M];
		raf.read(by);
		IOUtils.write(by, new FileOutputStream("d:/ftp/" + i + "_csdn.sql"));
	}
	
	public static class ThreadRead implements Runnable {
		int sum;
		
		public ThreadRead(int sum) {
			this.sum = sum;
		}
		
		public void run() {
			String fileName = sqlName.replace("${number}", sum + "");
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
				String str = "";
				while ((str = br.readLine()) != null) {
					System.out.println(str);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
