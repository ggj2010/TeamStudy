package com.team.gaoguangjin.test.lookbytecode;

/**
 * 
 * @author gaoguangjin 功能：查看java编译之后的字节码
 * 
 * 可以通过命令 E:/JavaExe>javap -c HelloWorld>HelloWorld.bytecode 会在目录下面生成字节码文件
 * 
 * 编译之前去掉引用的package，不对编译不了
 */
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("Hellow world");
	}
}
