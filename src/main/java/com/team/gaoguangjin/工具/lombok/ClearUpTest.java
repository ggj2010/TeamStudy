//package com.team.gaoguangjin.工具.lombok;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import lombok.Cleanup;
//
///**
// * @ClassName:ClearUpTest.java
// * @Description: @Cleanup测试
// * @author gaoguangjin
// * @Date 2015-4-22 下午2:03:12
// */
//public class ClearUpTest {
//	public static void main(String[] args) {
//		normal();
//		annation();
//	}
//
//	/**
//	 * @Description: 这是标准的写法
//	 */
//	private static void normal() {
//		InputStream in = null;
//		OutputStream out = null;
//		try {
//			in = new FileInputStream(new File("e:aa.txt"));
//			out = new FileOutputStream(new File("e:bb.txt"));
//			byte[] b = new byte[1000];
//			while (true) {
//				int r = in.read(b);
//				if (r == -1)
//					break;
//				out.write(b);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if (out != null)
//				try {
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//			if (in != null)
//				try {
//					in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}
//	}
//
//	/**
//	 * @Description: 这是使用lombok优化后的写法
//	 *
//	 */
//	private static void annation() {
//		try {
//			@Cleanup
//			InputStream in = new FileInputStream(new File("e:aa.txt"));
//			@Cleanup
//			OutputStream out = new FileOutputStream(new File("e:bb.txt"));
//			byte[] b = new byte[1000];
//			while (true) {
//				int r = in.read(b);
//				if (r == -1)
//					break;
//				out.write(b);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
