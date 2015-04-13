package com.team.gaoguangjin.javabase.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName:StreamAndBuffere.java
 * @Description: 字节流和字符流区别,只不过处理的单位不同而已。后缀是Stream是字节流，而后缀是Reader，Writer是字符流。
 * @author gaoguangjin
 * @Date 2015-3-2 下午1:53:13
 */
public class StreamAndBuffere {
	public static void main(String[] args) {
		// 字节流使用时候不会用上缓冲区，而字符流可以，这就是为什么我们常用都是把字节流转成字符流
		// 字符流使用的时候 一般都需要调用 flush()方法，而字节流不需要。因为字符流用到了缓冲区
		byteStream();
		buffereReader();
	}
	
	private static void byteStream() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @Description:字符流默认输出的缓冲区是8*1024 如果写的内容超过8M 就自动刷新缓冲区调研弄flush方法
	 * @return:void
	 */
	private static void buffereReader() {
		try {
			FileWriter fw1 = new FileWriter(new File("d:file1.txt"));
			fw1.write("i");
			
			// 如果写入的文件小于8m 而且没用调用flus（）方法，文件内容是写入不进去的噢！
			// fw1.flush();
			// fw1.close();//会强制刷新缓冲区
			
			FileWriter fw2 = new FileWriter(new File("d:file2.txt"));
			// private static final int DEFAULT_BYTE_BUFFER_SIZE = 8192;
			for (int i = 0; i < 8192 + 1; i++) {
				fw2.write("i");
			}
			
			// 因为写入的文件内容大于8m，所以可以写到文件里面去，调用flush()方法，将内容从缓冲区写入文件
			// fw2.flush();
			// fw2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
