package com.team.gaoguangjin.深入java虚拟机.类加载;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

import org.springframework.core.io.ClassPathResource;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {
	
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		Class clazz = this.findLoadedClass(className);
		if (null == clazz) {
			try {
				String classFile = getClassFile(className);
				FileInputStream fis = new FileInputStream(classFile);
				FileChannel fileC = fis.getChannel();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				WritableByteChannel outC = Channels.newChannel(baos);
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
				while (true) {
					int i = fileC.read(buffer);
					if (i == 0 || i == -1) {
						break;
					}
					buffer.flip();
					outC.write(buffer);
					buffer.clear();
				}
				fis.close();
				byte[] bytes = baos.toByteArray();
				
				clazz = defineClass(className, bytes, 0, bytes.length);
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return clazz;
	}
	
	private String getClassFile(String name) {
		name = name.replace('.', File.separatorChar) + ".class";
		ClassPathResource cpr = new ClassPathResource(name);
		try {
			return cpr.getFile().getPath();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
