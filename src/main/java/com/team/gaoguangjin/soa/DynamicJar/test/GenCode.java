package com.team.gaoguangjin.soa.DynamicJar.test;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;

public class GenCode {
	public static void main(String[] args) throws IOException {
		// System.out.println(System.getProperty("user.dir"));
		// File file = new DefaultResourceLoader().getResource("").getFile();
		// System.out.println(file.getPath());
		System.out.println(getProjectPath());
	}
	
	/**
	 * 获取工程路径
	 * @return
	 */
	public static String getProjectPath() {
		String projectPath = "";
		// 如果配置了工程路径，则直接返回，否则自动获取。
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null) {
				while (true) {
					if (file.getParentFile() != null) {
						file = file.getParentFile();
					} else {
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
	}
	
}
