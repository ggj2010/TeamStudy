package com.team.gaoguangjin.设计模式.ioc与事件驱动;

import org.junit.Test;

public class TestOrder {
	/**
	 * @Description:  
	 *  // 打个比喻，某个工厂缺少某个部件，通过采购快递将部件送到厂里，这是依赖注射
		// 而有的工厂则相反，委托别的工厂生产好部件后，不拉进自己厂里，而是将自己产品拉出去和那个部件进行组装。
	 * @return:void
	 */
	@Test
	public void Test() {
		Order order = new Order();
		// //User的方法diplsy()中需要依赖Order的getDetail()方法，我们就用Ioc框架将Order实例通过User的构造器或者Setter方法注入，注意，其实我们没有发现这里有一个很严重的设计问题，依赖是方法行为的依赖，
		// 因为方法有依赖，造成两个整类发生依赖，是不是有点影响面扩大呢？
		new User(order).diplsy();
		// 我们不能因为两个类的方法有依赖，就将整个类发生关系，这实际是一种结构偏执思维。
	}
}
