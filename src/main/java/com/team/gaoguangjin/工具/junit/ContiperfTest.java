package com.team.gaoguangjin.工具.junit;

import lombok.extern.slf4j.Slf4j;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @ClassName:ContiperfTest.java
 * @Description:    junit测试设计版本
 * @author gaoguangjin
 * @Date 2015-7-15 下午5:39:48
 */
@Slf4j
public class ContiperfTest {
	// @Rule的注释下生成一个ContiPerfRule的对象 这个必须的加上的，不然没有作用
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	/**
	 * @See：nvocations：方法的执行次数，例：@PerfTest(invocations = 300)重复执行300次;
	 * @See：Threads：同时执行的线程数，例：@PerfTest(invocations = 30, threads = 2)两个线程并发执行，每个线程执行15次，总共执行30次;
	 * @See：Duration：在指定时间范围内一直执行测试，例：@PerfTest(duration = 300)在300毫秒内反复执行。
	 *@see:timerParams:每执行完一次会等待20~60然后才会执行下一次调用 
	 *@see:Required:通过这个注释可以添加一些对性能期望，如果达到期望值则测试通过，如果没有达到期望值则执行失败。 
	 * @See：三个属性可以组合使用，其中Threads必须和其他两个属性组合才能生效。当Invocations和Duration都有指定时@Description:  
	 * @return:void
	 */
	@Test
	// @PerfTest(invocations = 10, threads = 10, timer = RandomTimer.class, timerParams = { 20, 60 })
	@PerfTest(invocations = 300, threads = 2, duration = 100)
	// @Required(max = 15000, average = 8000)
	public void thread() {
		System.out.println("====");
	}
	
	/**
	 * @Required(throughput = 20) ：每秒钟至少执行20次

	@Required(average = 50)：测试全部完成平均执行时间为20ms

	@Required(median = 45)：至少50%次执行的时间在45ms内

	@Required(max = 2000) ：所有执行的执行时间没有超过2s

	@Required(totalTime = 5000) ：测试完成执行时间少于5s

	@Required(percentile90 = 3000) ：所有执行中90%的执行时间少于3s

	@Required(percentile95 = 5000)：所有执行中95%的执行时间少于5s

	@Required(percentile99 = 10000)：所有执行中99%的执行时间少于10s

	@Required(percentiles = "66:200,96:500")：所有执行中66%的执行时间少于200ms，96%的执行时间少于500ms

	@PerfTest和@Required不仅可以指定某一个方法，也可以至于类的前面指定某一个类的执行参数。例，
	 */
}
