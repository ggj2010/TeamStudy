package com.team.gaoguangjin.工具.junit.runwith;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @ClassName:RunWithSuit.java
 * @Description: 使用runwith注解之suit功能  ,来执行分布在多个类中的测试用例比如 junitestOne,junittestTwo
 * @author gaoguangjin
 * @Date 2015-7-15 下午5:55:50
 */
@RunWith(Suite.class)
@SuiteClasses({ JunitTestOne.class, JunitTestTwo.class })
public class RunWithSuit {
	// 点击运行之后 会把 JunitTestOne和JunitTestTwo 都执行完毕
}
