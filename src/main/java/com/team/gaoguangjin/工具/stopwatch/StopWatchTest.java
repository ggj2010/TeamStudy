package com.team.gaoguangjin.工具.stopwatch;

import org.springframework.util.StopWatch;

/**
 * @author:gaoguangjin
 * @date 2017/3/30 9:19
 */
public class StopWatchTest {
	/**
	 *
	 StopWatch 'My Stop Watch': running time (millis) = 10001
	 -----------------------------------------
	 ms     %     Task name
	 -----------------------------------------
	 02000  020%  initializing
	 05000  050%  processing
	 03001  030%  finalizing
	 * @param args
	 * @throws InterruptedException
     */
	public static void main(String[] args) throws InterruptedException {
		StopWatch stopWatch = new StopWatch("My Stop Watch");
		stopWatch.start("initializing");
		Thread.sleep(2000); // simulated work
		stopWatch.stop();
		stopWatch.start("processing");
		Thread.sleep(5000); // simulated work
		stopWatch.stop();
		stopWatch.start("finalizing");
		Thread.sleep(3000); // simulated work
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}
}
