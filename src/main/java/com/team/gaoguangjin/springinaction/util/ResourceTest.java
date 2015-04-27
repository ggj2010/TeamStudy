package com.team.gaoguangjin.springinaction.util;

import java.io.FileReader;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

/**
 * @ClassName:ResourceTest.java
 * @Description: spring Resource类使用，资源抽象接口
 * @author gaoguangjin
 * @Date 2015-4-23 下午12:02:23
 */
@Slf4j
public class ResourceTest {
	public static void main(String[] args) throws IOException {
		demo();
	}
	
	private static void demo() throws IOException {
		String classPath = "日志框架说明.txt";
		Resource classPathResource = new ClassPathResource(classPath);
		// 如果不确定文件格式，准换成utf-8
		EncodedResource enc = new EncodedResource(classPathResource, "utf-8");
		// Fileutil类
		String str = FileCopyUtils.copyToString(enc.getReader());
		log.info(str);
		
		String filePath = "src/main/resources/日志框架说明.txt";
		Resource fileSystemResource = new FileSystemResource(filePath);
		String str2 = FileCopyUtils.copyToString(new FileReader(fileSystemResource.getFile()));
		log.info(str2);
		
		ResourcePatternResolver rp = new PathMatchingResourcePatternResolver();
		Resource resource = rp.getResource("classpath:日志框架说明.txt");
		String str3 = FileCopyUtils.copyToString(new FileReader(resource.getFile()));
		log.info(str3);
		
	}
}
