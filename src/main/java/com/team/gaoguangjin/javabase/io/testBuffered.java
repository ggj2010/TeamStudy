package com.team.gaoguangjin.javabase.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:testBuffered.java
 * @Description:测试字符缓冲区的好处.字符流使用了缓冲区，而字节流没有使用缓冲区
 * @author gaoguangjin
 * @Date 2015-3-2 下午2:11:36
 */
@Slf4j
public class testBuffered {
	public final static String File_PATH_1 = "e:file1.txt";
	public final static String File_PATH_2 = "e:file2.txt";
	
	public static void main(String[] args) {
		//
		// deleteFile();
		// // 生成测试的文件
		// createFile();
		// 读取文件
		readFile();
	}
	
	/**
	 * @Description:文件若存在先删除
	 * @return:void
	 */
	private static void deleteFile() {
		try {
			File file1 = null;
			File file2 = null;
			if ((file1 = new File(File_PATH_1)).exists())
				file1.delete();
			if ((file2 = new File(File_PATH_2)).exists())
				file2.delete();
		} catch (Exception e) {
			log.error("删除文件失败：" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description:比较字符缓冲区和普通字符创建文件的区别，字节用不到缓冲区
	 * @see:2015-03-02 15:17:26,174 INFO [main] (testBuffered.java:60) - 简单字符流写文件耗费时间：595ms
	 * @see:2015-03-02 15:17:26,176 INFO [main] (testBuffered.java:61) - 使用缓冲区字符流写文件耗费时间：37ms
	 * @return:void
	 */
	private static void createFile() {
		try {
			long beingTime1 = System.currentTimeMillis();
			createFileByWriter();
			long endTime1 = System.currentTimeMillis();
			
			long beingTime2 = System.currentTimeMillis();
			createFileByBuffereWriter();
			long endTime2 = System.currentTimeMillis();
			
			log.info("简单字符流写文件耗费时间：" + (endTime1 - beingTime1) + "ms");
			log.info("使用缓冲区字符流写文件耗费时间：" + (endTime2 - beingTime2) + "ms");
		} catch (Exception e) {
			log.error("写入文件失败：" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description:简单字符流写文件
	 * @return:void
	 */
	private static void createFileByWriter() {
		try {
			FileWriter fw = new FileWriter(new File(File_PATH_1));
			for (int i = 0; i < 1200000; i++) {
				fw.write("[" + i + "]这是ceshi" + "\r\n");
			}
			fw.flush();
			fw.close();
		} catch (Exception e) {
			log.error("字符流写入文件失败：" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: 简单缓冲字符流写文件
	 * @return:void
	 */
	private static void createFileByBuffereWriter() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(File_PATH_2)));
			for (int i = 0; i < 1200000; i++) {
				bw.write("[" + i + "]这是ceshi" + "\r\n");
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			log.error("缓冲字符流写入文件失败：" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description:readFileByReader/reateFileByBuffereReader没有可比性
	 * 某些情况下，如果一个程序频繁地操作一个资源（如文件或数据库），则性能会很低，此时为了提升性能，就可以将一部分数据暂时读入到内存的一块区域之中，以后直接从此区域中读取数据即可，因为读取内存速度会比较快，这样可以提升程序的性能
	 * @see:2015-03-02 16:35:43,076 INFO [main] (testBuffered.java:128) - InputStreamReader使用缓冲区读入字符流耗费时间365ms
	 * @see:2015-03-02 16:35:43,076 INFO [main] (testBuffered.java:129) - BufferedReader简单字符流读入文件耗费时间：504ms
	 * @return:void
	 */
	private static void readFile() {
		try {
			long beingTime1 = System.currentTimeMillis();
			readFileByReader();
			long endTime1 = System.currentTimeMillis();
			
			long beingTime2 = System.currentTimeMillis();
			reateFileByBuffereReader();
			long endTime2 = System.currentTimeMillis();
			
			// long beingTime3 = System.currentTimeMillis();
			// readFileByByte();
			// long endTime3 = System.currentTimeMillis();
			
			log.info("InputStreamReader使用缓冲区读入字符流耗费时间" + (endTime1 - beingTime1) + "ms");
			log.info("BufferedReader简单字符流读入文件耗费时间：" + (endTime2 - beingTime2) + "ms");
			// log.info("FileInputStream byte读入字符流耗费时间：" + (endTime3 - beingTime3) + "ms");
		} catch (Exception e) {
			log.error("写入文件失败：" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 例如字符缓冲区去读文件
	 * 
	 * @return:void
	 */
	private static void reateFileByBuffereReader() {
		try {
			BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(File_PATH_2))));
			String str = "";
			StringBuilder sb = new StringBuilder(1024);
			while ((str = bw.readLine()) != null)
				sb.append(str);
			bw.close();
		} catch (Exception e) {
			log.error("缓冲字符流写入文件失败：" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 普通reader读入文件
	 * 
	 * @return:void
	 */
	private static void readFileByReader() {
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(new File(File_PATH_2)));
			StringBuilder sb = new StringBuilder(1024);
			char[] ch = new char[500];
			while (is.read(ch) != -1)
				sb.append(ch);
			is.close();
		} catch (Exception e) {
			log.error("缓冲字符流写入文件失败：" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: 普通byte读入文件
	 * 
	 * @return:void
	 */
	private static void readFileByByte() {
		try {
			FileInputStream is = new FileInputStream(new File(File_PATH_2));
			byte[] by = new byte[1024];
			StringBuilder sb = new StringBuilder(1024);
			while (is.read(by) != -1) {
				sb.append(by.toString());
				System.out.write(by);
			}
			is.close();
		} catch (Exception e) {
			log.error("缓冲字符流写入文件失败：" + e.getLocalizedMessage());
		}
	}
	
}
