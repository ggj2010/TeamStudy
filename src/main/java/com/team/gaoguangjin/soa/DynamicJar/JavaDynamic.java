package com.team.gaoguangjin.soa.DynamicJar;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @ClassName:JavaDynamic.java
 * @Description: java动态编译
 * @author gaoguangjin
 * @Date 2015-6-1 下午4:27:11
 */
public class JavaDynamic {
	public static void main(String[] args) throws Exception {
		
		// 拷贝java文件
		
		// 1.取得当前系统的编译器
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		// 2.获取一个文件管理器
		StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null, null, null);
		
		// 3.文件管理器根与文件连接起来，生成多个.
		// 注意架包路径
		// 依赖包
		File[] fileList = getFile();
		Iterable its = javaFileManager.getJavaFileObjects(getFile());
		// Iterable its = javaFileManager.getJavaFileObjects(new File("e:NoteBook.java"), new File("e:Note.java"));
		// 编译目的地设置
		Iterable options = Arrays.asList("-d", "e:java");
		StringWriter sw = new StringWriter();
		// 4.创建编译任务
		CompilationTask task = javaCompiler.getTask(sw, javaFileManager, null, options, null, its);
		// 5.执行编译
		// 编译错误的
		if (!task.call()) {
			String failedMsg = sw.toString();
			System.out.println("错误" + failedMsg);
		}
		javaFileManager.close();
		
		// 压缩com.team.gaoguangjin.soa.DynamicJar
		
		// makeFileDirety();
		
		// jar();
		// com.team.gaoguangjin.soa.DynamicJar
		// jar cf bean.jar com/team/gaoguangjin/soa/DynamicJar/Bean.class
		// com/team/gaoguangjin/soa/DynamicJar/Beans.class
		
		// // load
		// load();
		//
		// // 传统java加载
		// Class<?> bean =
		// Thread.currentThread().getContextClassLoader().loadClass("com.team.gaoguangjin.soa.DynamicJar.Bean");
		// Object obj = bean.newInstance();
		// Method method = obj.getClass().getDeclaredMethod("getName");
		// Object beanObj = method.invoke(obj);
		// System.out.println(beanObj);
	}
	
	private static File[] getFile() {
		List<File> list = new ArrayList<File>();
		list.add(new File("E:/cn/msec/core/redis/annation/RedisCache.java"));
		list.add(new File("E:/cn/msec/core/redis/annation/RedisFieldNotCache.java"));
		list.add(new File("E:/cn/msec/core/redis/annation/RedisQuery.java"));
		list.add(new File("E:/cn/msec/core/redis/util/RedisCacheManagers.java"));
		list.add(new File("E:Note.java"));
		list.add(new File("E:NoteService.java"));
		list.add(new File("E:NoteServiceImp.java"));
		return list.toArray(new File[list.size()]);
	}
	
	private static void jar() throws IOException {
		Runtime run = Runtime.getRuntime();
		
		Process process = run
				.exec("jar cf e:/bean.jar e:com/team/gaoguangjin/soa/DynamicJar/Bean.class e:com/team/gaoguangjin/soa/DynamicJar/Beans.class");
	}
	
	private static void makeFileDirety() {
		String packageName = "com.team.gaoguangjin.soa.DynamicJar";
		
		packageName = packageName.replace(".", "/");
		String path = "e:/" + packageName;
		
		File fi = new File(path);
		if (!fi.exists()) {
			fi.mkdirs();
		}
		
	}
	
	private static void load() throws Exception {
		// file:/F:/IdeaProjects/TeamStudy/target/classes/com/team/gaoguangjin/soa/DynamicJar/dynamic.jar
		// file:/e:/bean.jar
		URL urls = new URL("file:/e:/bean.jar");
		URLClassLoader urlLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		Class<URLClassLoader> sysclass = URLClassLoader.class;
		Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
		method.setAccessible(true);
		method.invoke(urlLoader, urls);
	}
}
