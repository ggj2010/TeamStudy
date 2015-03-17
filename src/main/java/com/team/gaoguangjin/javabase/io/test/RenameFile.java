package com.team.gaoguangjin.javabase.io.test;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:RenameFile.java
 * @Description:修改图片名称,把网上下载的图片重名下哈
 * @author gaoguangjin
 * @Date 2015-3-13 下午3:35:23
 */
@Slf4j
public class RenameFile {
	public static void main(String[] args) {
		rename();
	}
	
	private static void rename() {
		File file = new File("F:/老高手机/520");
		File[] fileList = file.listFiles();
		log.info("" + fileList.length);// 295，
		int i = 0;
		for (File fi : fileList) {
			i++;
			log.info(i + ".jpg");
			boolean fs = fi.renameTo(new File("F:/老高手机/520/" + i + ".jpg"));
			log.info("" + fs);
		}
	}
}
