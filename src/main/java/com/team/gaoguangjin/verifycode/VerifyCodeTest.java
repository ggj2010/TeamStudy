package com.team.gaoguangjin.verifycode;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

/**
 * 功能：识别网站验证码
 * @author gaoguangjin
 * @describe 1、首先下载图片2、然后截取图片，3、对比图片获取值
 * 
 */
@Slf4j
public class VerifyCodeTest {
	// 验证码生成地址
	private static String DOWNLOAD_URL = "http://115.29.162.30/tms/getImage.do";
	// 保存下载图片的地址
	private static String SAVE_IMAGE_URL = "src/main/resources/jietu/tiantian/download";
	// 处理后图片地址。图片命名和图片的值一样。
	private static String SAVE_IMAGE_AFTER_SPLIT_URL = "src/main/resources/jietu/tiantian/split/";
	
	// 处理后图片地址。图片命名和图片的值一样。
	private static String SAVE_RESULT_IMAGE_URL = "src/main/resources/jietu/tiantian/result";
	
	private static int splitImageCount = 0;
	
	// 处理后图片保存到map里面
	private static Map<BufferedImage, String> trainMap = null;
	
	public static void main(String[] args) throws Exception {
		// 下载图片
		// downloadImage();
		
		// 截取图片
		// splitImage();
		
		// 测试本地
		// test();
		
		testInternet();
		
	}
	
	/**
	 * 测试网络
	 * @throws Exception
	 */
	private static void testInternet() throws Exception {
		
		// 加载已经处理了的图片，文件命名和文件内容值一样
		Map<BufferedImage, String> map = loadTrainData();
		
		BufferedImage img = ImageIO.read(new URL("http://115.29.162.30/tms/getImage.do?t=1421642843805"));
		
		Desktop desk = Desktop.getDesktop();
		File file = new File("c://a.jpg");
		ImageIO.write(img, "jpg", file);
		
		desk.open(file);
		
		log.info("开始验证图片");
		String resultStr = checkImageCode(file, map);
		log.info("匹配图片返回的结果：" + resultStr);
	}
	
	/**
	 * 测试验证码
	 */
	private static void test() {
		try {
			
			// 加载已经处理了的图片，文件命名和文件内容值一样
			Map<BufferedImage, String> map = loadTrainData();
			
			File[] listFile = new File(SAVE_IMAGE_URL).listFiles();
			
			for (int i = 0; i < 30; i++) {
				log.info("开始验证图片,图片名称【" + listFile[i].getName() + "】");
				String resultStr = checkImageCode(listFile[i], map);
				log.info("匹配图片返回的结果：" + resultStr);
			}
			
		}
		catch (Exception e) {
			log.error("验证图片失败！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * 循环开始验证图片验证码
	 * @param file
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private static String checkImageCode(File file, Map<BufferedImage, String> map) throws Exception {
		// 去除背景色，
		BufferedImage bs = removeBackgroud(file);
		
		// 截图
		List<BufferedImage> listSplit = getSplitImage(bs);
		
		String result = "";
		for (BufferedImage bi : listSplit) {
			result += getSingleCharOcr(bi, map);
			
		}
		
		return result;
	}
	
	/*
	 * 根据规则截取图片
	 */
	private static void splitImage() {
		try {
			// 1、获取所有下载过的图片
			File file = new File(SAVE_IMAGE_URL);
			File[] listFile = file.listFiles();
			// 循环切割
			for (File singleFile : listFile) {
				splitImage(singleFile);
			}
		}
		catch (Exception e) {
			log.error("截取图片失败！" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 对单个文件进行分割
	 * @param singleFile
	 * @throws Exception
	 */
	private static void splitImage(File singleFile) throws Exception {
		// 去除背景色，
		BufferedImage bs = removeBackgroud(singleFile);
		
		// 获取已经截取的图片流
		List<BufferedImage> imageList = getSplitImage(bs);
		
		// 保存截取后的图片
		for (BufferedImage bufferedImage : imageList) {
			ImageIO.write(bufferedImage, "jpg", new File(SAVE_IMAGE_AFTER_SPLIT_URL + splitImageCount + ".jpg"));
			splitImageCount++;
		}
	}
	
	/**
	 * 下载图片
	 */
	
	public static void downloadImage() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(DOWNLOAD_URL);
		for (int i = 0; i < 1000; i++) {
			try {
				// 执行getMethod
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + getMethod.getStatusLine());
				}
				// 读取内容
				String picName = SAVE_IMAGE_URL + i + ".jpg";
				
				InputStream inputStream = getMethod.getResponseBodyAsStream();
				OutputStream outStream = new FileOutputStream(picName);
				IOUtils.copy(inputStream, outStream);
				outStream.close();
				System.out.println(i + "OK!");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				// 释放连接
				getMethod.releaseConnection();
			}
		}
	}
	
	/**
	 * 去除背景色
	 */
	public static BufferedImage removeBackgroud(File file) throws Exception {
		BufferedImage img = ImageIO.read(file);
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (isWhite(img.getRGB(x, y)) == 1) {
					img.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					img.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return img;
	}
	
	/**
	 * 判断背景色是否是白色
	 * @param colorInt
	 * @return
	 */
	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 100) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 将大图片进行截图操作
	 * @param img
	 * @return
	 * @throws Exception
	 */
	public static List<BufferedImage> getSplitImage(BufferedImage img) throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		// subImgs.add(img.getSubimage(6, 3, 12, 14));
		// subImgs.add(img.getSubimage(19, 3, 12, 14));
		// subImgs.add(img.getSubimage(32, 3, 12, 14));
		// subImgs.add(img.getSubimage(45, 3, 12, 14));
		int width = img.getWidth();
		int height = img.getHeight();
		List<Integer> weightlist = new ArrayList<Integer>();
		for (int x = 0; x < width; ++x) {
			int count = 0;
			for (int y = 0; y < height; ++y) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			weightlist.add(count);
		}
		for (int i = 0; i < weightlist.size(); i++) {
			int length = 0;
			while (i < weightlist.size() && weightlist.get(i) > 0) {
				i++;
				length++;
			}
			if (length > 18) {
				subImgs.add(removeBlank(img.getSubimage(i - length, 0, length / 2, height)));
				subImgs.add(removeBlank(img.getSubimage(i - length / 2, 0, length / 2, height)));
			} else if (length > 5) {
				subImgs.add(removeBlank(img.getSubimage(i - length, 0, length, height)));
			}
		}
		
		return subImgs;
	}
	
	public static BufferedImage removeBlank(BufferedImage img) throws Exception {
		int width = img.getWidth();
		int height = img.getHeight();
		int start = 0;
		int end = 0;
		Label1: for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					start = y;
					break Label1;
				}
			}
		}
		Label2: for (int y = height - 1; y >= 0; --y) {
			for (int x = 0; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					end = y;
					break Label2;
				}
			}
		}
		return img.getSubimage(0, start, width, end - start + 1);
	}
	
	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {
			return 1;
		}
		return 0;
	}
	
	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
			File dir = new File(SAVE_RESULT_IMAGE_URL);
			File[] files = dir.listFiles();
			for (File file : files) {
				map.put(ImageIO.read(file), file.getName().charAt(0) + "");
			}
			trainMap = map;
		}
		return trainMap;
	}
	
	/**
	 * 直接拿分割的图片跟这个十个图片一个像素一个像素的比， 相同的点最多的就是结果。比如如果跟5.jpg最相似，那么识别的结果就是5。
	 * @param img
	 * @param map
	 * @return
	 */
	
	public static String getSingleCharOcr(BufferedImage img, Map<BufferedImage, String> map) {
		String result = "";
		int width = 7;
		int height = img.getHeight();
		int min = width * height;
		for (BufferedImage bi : map.keySet()) {
			int count = 0;
			Label1: for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height; ++y) {
					if (isWhite(img.getRGB(x, y)) != isWhite(bi.getRGB(x, y))) {
						count++;
						if (count >= min)
							break Label1;
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
		return result;
	}
}
