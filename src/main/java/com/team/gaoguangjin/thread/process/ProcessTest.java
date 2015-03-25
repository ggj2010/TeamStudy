package com.team.gaoguangjin.thread.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ProcessTest.java
 * @Description: 执行进程测试
 * @author gaoguangjin
 * @Date 2015-3-23 上午11:12:47
 */
@Slf4j
public class ProcessTest {
	public static void main(String[] args) {
		String command = "ipconfig";// 执行进程ipconfig m命令
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
			String str = "";
			while ((str = br.readLine()) != null) {
				log.info(str.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
