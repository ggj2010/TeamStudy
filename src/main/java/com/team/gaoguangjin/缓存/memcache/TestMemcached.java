package com.team.gaoguangjin.缓存.memcache;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * @ClassName:TestMemcached.java
 * @Description: memcached 基本学习
 * @author gaoguangjin
 * @Date 2015-7-2 下午4:26:08
 */
public class TestMemcached {
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		init();
		MemCachedClient memCachedClient = new MemCachedClient();
		testSet(memCachedClient);
	}
	
	/**
	 * @Description: 测试set
	 * @param memCachedClient
	 * @return:void
	 */
	private static void testSet(MemCachedClient memCachedClient) {
		memCachedClient.set("gao1", "test");
		for (int i = 0; i < 10; i++) {
			/**
			 * 将对象加入到memcached缓存
			 * */
			boolean success = memCachedClient.set("" + i, "Hello!");
			/**
			 * 从memcached缓存中按key值取对象
			 * */
			String result = (String) memCachedClient.get("" + i);
			System.out.println(String.format("set( %d ): %s", i, success));
			System.out.println(String.format("get( %d ): %s", i, result));
		}
	}
	
	private static void init() {
		/**
		 * 初始化SockIOPool，管理memcached的连接池
		 * */
		String[] servers = { "123.56.118.135:11111" };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(servers);
		pool.setFailover(true);
		pool.setInitConn(10);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setAliveCheck(true);
		pool.initialize();
	}
}
