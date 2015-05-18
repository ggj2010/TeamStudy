package com.team.gaoguangjin.缓存.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @ClassName:RedisTest.java
 * @Description: java 中调用redis,本例子中我在123.56.118.135服务器里面部署了redis服务
 * @author gaoguangjin
 * @Date 2015-5-11 上午10:02:03
 */
@Slf4j
public class RedisTest {
	private Jedis jedis;
	
	@Before
	public void init() {
		// 连接redis linux服务器
		jedis = new Jedis("123.56.118.135", 6379);
		// 权限认证
		jedis.auth("gaoguangjin");// 密码最好越长越好，防止暴力破解
	}
	
	@Test
	public void test() {
		String str = jedis.get("foo");
		log.info("初始化demo测试：" + str);
	}
	
	@Test
	public void testString() {
		log.info("---------------测试String格式---------------");
		
		// 添加string
		jedis.set("name", "高广金");
		log.info("get:" + jedis.get("name"));
		
		// 追加String 【append】追加命令
		jedis.append("name", "+fighting！");
		log.info("append:" + jedis.get("name"));
		
		// 删除String 【del】删除命令
		jedis.del("name");
		log.info("删除之后：" + jedis.get("name"));
		
		// 测试存放相同key
		String a = jedis.set("object", "object");
		String b = jedis.set("object", "object");
		log.info("==" + (a == b));// 因为返回的是：new String(data, Protocol.CHARSET) 所以是false
		log.info("equal:" + (a.equals(b)));
		jedis.del("object");
		
	}
	
	@Test
	public void testMap() {
		log.info("---------------测试Map格式---------------");
		
		// 多个键值对 【mset】 key1 "dd" key2 "dd"
		jedis.mset("gao", "高", "guang", "广", "jin", "金");
		List<String> list = jedis.mget("gao", "guang", "jin");
		String listString = "";
		for (String string : list) {
			listString += string;
		}
		log.info("mget和mset：" + listString);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("gao", "高");
		map.put("guang", "广");
		map.put("jin", "金");
		
		// 【HMSET】 myhash field1 "Hello" field2 "World"||HGET myhash field1
		jedis.hmset("firstMap", map);
		
		// 获取key中key 【hget】
		String gao = jedis.hget("firstMap", "gao");
		log.info("获取map中某个key的值：[hget] firstMap gao:" + gao);
		
		// 获取所有 返回是Map<String, String> 【hgetAll】
		Map<String, String> mapList = jedis.hgetAll("firstMap");
		for (Iterator<String> iterator = mapList.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			log.info("hgetAll:key=" + key + ":value=" + mapList.get(key));
		}
		
	}
	
	@Test
	public void testList() {
		log.info("---------------测试List格式---------------");
		jedis.del("list");
		List<String> list = new ArrayList<String>();
		
		// 第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
		// redis> LPUSH mylist "world"|LPUSH mylist "hello"|LRANGE mylist 0 -1
		
		log.info("[lpush 存放到list里面]");
		for (int i = 5; i > 0; i--)
			jedis.lpush("list", "" + i);
		jedis.lpush("list", "" + "100");
		
		// 【lrange】命令
		list = jedis.lrange("list", 0, -1);
		
		for (String string : list) {
			log.info("[lrange 获取list里面值]：" + string);// 默认输出 和 输入的时候值相反的顺序[8/9/1/2/3] POP
		}
		
		// [sort]排序
		list = jedis.sort("list");
		for (String string : list) {
			log.info(" sort排序之后：" + string);// 从小到大
		}
		
	}
	
	@Test
	public void testSet() {
		log.info("---------------测试Set格式---------------");
		// SADD myset "Hello"|SMEMBERS myset
		jedis.sadd("set", "gao", "guang", "jin");
		
		Set<String> set = jedis.smembers("set");
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();)
			log.info("set集合：sadd添加|smember获取set集合:" + iterator.next());
		
		// 去除某个set
		jedis.srem("set", "jin");
		
		Set<String> set2 = jedis.smembers("set");
		for (Iterator<String> iterator = set2.iterator(); iterator.hasNext();)
			log.info("【去除 jin之后】set集合,sadd添加|smember获取set集合:" + iterator.next());
		
		Boolean isSet = jedis.sismember("set", "jin");
		log.info("sismemeber,判断某个key，set里面是否存在：" + isSet);
		
		long setSize = jedis.scard("set");
		log.info("获取set的大小 scard :" + setSize);
		
		String randomSet = jedis.srandmember("set");
		log.info("获取一个随机的set srandmember: " + randomSet);
		
	}
	
	@Test
	public void normal() {
		log.info("---------------常见key的操作---------------");
		// 给定key设置生存时间。当key过期时，它会被自动删除
		jedis.set("autodel", "autodel");
		log.info("休眠之前[expire]:" + jedis.get("autodel"));
		jedis.expire("autodel", 1);// 1秒
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("休眠之后 [expire]:" + jedis.get("autodel"));
	}
	
	/**
	 * @Description: 测试redis连接池。
	 * @return:void
	 */
	@Test
	public void testPool() {
		log.info("---------------测试redis连接池格式---------------");
		Jedis jedis = RedisPool.getJedis();
		jedis.set("foo", "hellow world!");
		log.info("redis连接池:获取到的值：" + jedis.get("foo"));
		// 返回连接池
		RedisPool.returnResource(jedis);
	}
	
	// redis 排行榜测试
	
	public static void main(String[] args) throws InterruptedException {
		sortScore();
		// autoincrease();
		// 获取所有key
		// getAllKey();
		// 查询某个key的类型，大小，使用实际
		// checkKey();
		// publish和subsrcibe 订阅者和发布者
		// jms();
		// 测试不同数据库select()
		// selectDataBase();
		
		// redis事物
		// notUsetransation();
		
		// useTransation();
	}
	
	// multi 开启事物， exec提交事物。redis只能保证一个client发起的事务中的命令可以连续的执行，而中间不会插入其他client的命令
	// 开启事物之后所有命令都在queue里面，等到执行exec就会提交所有的命令，如果当中某个命令错误不会回滚所有的命令，只有错误的命令执行不过去
	private static void useTransation() {
		// TODO Auto-generated method stub
		
	}
	
	private static void notUsetransation() throws InterruptedException {
		final Jedis jedis1 = RedisPool.getJedis();
		final Jedis jedis2 = RedisPool.getJedis();
		final Jedis jedis3 = RedisPool.getJedis();
		
		jedis1.set("transation", "1");
		
		log.info("使用之前transation值：" + jedis1.get("transation"));
		Thread thread1 = new Thread() {
			public void run() {
				String transation = jedis1.get("transation");
				try {
					log.info("初始化jedis1 transation值：" + transation);
					Thread.sleep(1000);
					jedis1.set("transation", "" + (Integer.parseInt(transation) + 1));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread thread2 = new Thread() {
			public void run() {
				String transation = jedis2.get("transation");
				try {
					log.info("初始化jedis2 transation值：" + transation);
					Thread.sleep(1000);
					jedis2.set("transation", "" + (Integer.parseInt(transation) + 1));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
		String transation = jedis3.get("transation");
		log.info("没使用事物，我想要的是效果是，让transtion由两个客户端操作，每次都是在原来基础上加一。如果是三，那就是正确");
		log.info("结束之后transation值：" + transation);
		
	}
	
	private static void selectDataBase() {
		final Jedis jedis = RedisPool.getJedis();
		
		jedis.select(0);
		
		jedis.set("select", "123");
		
		final Jedis jedis2 = RedisPool.getJedis();
		
		jedis2.select(1);
		jedis2.set("select", "345");
		
		log.info(jedis.get("select"));
		log.info(jedis2.get("select"));
		
	}
	
	/**
	 * @Description:服务提供者和消费者都以多线程方式，这样才不会阻塞到。也可以自己受到去命令行添加publish key value
	 * @return:void
	 */
	private static void jms() {
		// 每一个连接对应的消息接收端
		final Jedis jedis = RedisPool.getJedis();
		final Jedis jedis2 = RedisPool.getJedis();
		
		final JedisPubSub jedisPubSub = new NotifySub("A");
		
		final JedisPubSub jedisPubSub2 = new NotifySub("B");
		final JedisPubSub jedisPubSub3 = new NotifySub("C");
		
		// 发布消息的
		
		// 调用消息的消费单，每一个监听启动一个线程，不然会阻塞
		// 每个监听的key,如果客户端发布了，只能一对一获取，如果A先获取到了，B就获取不到
		// new Thread() {
		// public void run() {
		// jedis.subscribe(jedisPubSub, "subscribeMessage1");
		// };
		// }.start();
		
		new Thread() {
			public void run() {
				jedis.subscribe(jedisPubSub2, "gao");
			};
		}.start();
		
		new Thread() {
			public void run() {
				jedis2.subscribe(jedisPubSub3, "gao");
			};
		}.start();
		
		// /* 服务提供者 */
		// new Thread() {
		// public void run() {
		// try {
		// Thread.sleep(4000);
		// jedis.psubscribe(jedisPubSub2, "subscribeMessage2", "高广金是帅哥2");// 需要自定义一个类去继承抽象类
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// };
		// }.start();
		// new Thread() {
		// public void run() {
		// try {
		// Thread.sleep(2000);
		// jedis.psubscribe(jedisPubSub, "subscribeMessage1", "高广金是帅哥1");// 需要自定义一个类去继承抽象类
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// };
		// }.start();
		
		// jedis.subscribe(jedisPubSub, "subscribeMessage");
		// jedis.subscribe(jedisPubSub2, "subscribeMessage");
		// RedisPool.returnResource(jedis);// 返回连接池
	}
	
	// redis里面类型
	// 1、int 2、embstr（字符串）3、ziplist或者linklist(list、map) 4、hashtable（set）5、
	private static void checkKey() {
		Jedis jedis = RedisPool.getJedis();
		
		Set<String> keyNameList = jedis.keys("*");
		for (Iterator<String> iterator = keyNameList.iterator(); iterator.hasNext();) {
			String keyName = iterator.next();
			String type = jedis.objectEncoding(keyName);// 回给定 key 锁储存的值所使用的内部表示(representation)
			Long time = jedis.objectIdletime(keyName);// 最近一次呗调用时间//给定 key 自储存以来的空闲时间
			Long count = jedis.objectRefcount(keyName);// 返回给定 key 引用所储存的值的次数。
			log.info("keyName=" + keyName + "  ||  " + "keyType=" + type + "  || " + "Idletime=" + time + "  || " + "Refcount=" + count);
		}
		
		// jedis.del("refreshcount");
		// jedis.set("refreshcount", "100000");//100000值只被引用一次
		
		RedisPool.returnResource(jedis);// 返回连接池
	}
	
	private static void getAllKey() {
		Jedis jedis = RedisPool.getJedis();
		
		Set<String> keyName = jedis.keys("*");
		for (Iterator<String> iterator = keyName.iterator(); iterator.hasNext();)
			log.info("keys 获取指定的key:" + iterator.next() + ":");
		
		RedisPool.returnResource(jedis);// 返回连接池
	}
	
	private static void autoincrease() {
		Jedis jedis = RedisPool.getJedis();
		
		// jedis.set("number", "99");测试是否开启持久化，存入磁盘了
		jedis.incr("number");
		log.info("自动递增incr:" + jedis.get("number"));
		
		RedisPool.returnResource(jedis);// 返回连接池
	}
	
	private static void sortScore() {
		Jedis jedis = RedisPool.getJedis();
		jedis.del("scorSort");
		Random rd = new Random();
		// 随机存放10个分数
		for (int i = 0; i < 11; i++) {
			int m = rd.nextInt(100);
			System.out.println(m + ":" + "gao" + i);
			jedis.zadd("scorSort", m, "gao" + i);// zadd
		}
		
		System.out.println("=[zadd]添加===[zrevrange]排序之后的值====");
		Set<String> set = jedis.zrevrange("scorSort", 0, 9);// 获取top10的用户,按照分数从大到小排序
		// zrank 是从小到大的顺序 + WITHSCORES 会打印value
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();)
			System.out.print(iterator.next() + ":");
		
		System.out.println("");
		Long sequence = jedis.zrank("scorSort", "gao1");// 获取某个用户的全球排名
		
		log.info("[zrank] gao1在11个人当中排序：" + sequence);
		
		// 返回连接池
		RedisPool.returnResource(jedis);
		
	}
	
	public static class NotifySub extends JedisPubSub {
		
		private final String name;
		
		public NotifySub(String name) {
			this.name = name;
		}
		
		@Override
		public void onMessage(String channel, String message) {
			log.info("1onMessage消费端【" + name + "】获取到值：" + channel + "=" + message);
			
		}
		
		@Override
		public void onPMessage(String pattern, String channel, String message) {
			log.info("2onPMessage 【" + name + "】返回值" + pattern + "=" + message);
			
		}
		
		@Override
		public void onSubscribe(String channel, int subscribedChannels) {
			log.info("3onSubscribe:消费端【" + name + "】开始监听key=" + channel);
			
		}
		
		@Override
		public void onUnsubscribe(String channel, int subscribedChannels) {
			log.info("4onUnsubscribe");
			
		}
		
		@Override
		public void onPUnsubscribe(String pattern, int subscribedChannels) {
			log.info("5onPUnsubscribe");
			
		}
		
		// 取得按表达式的方式订阅的消息后的处理
		public void onPSubscribe(String pattern, int subscribedChannels) {
			log.info("6 onPSubscribe【" + name + "】pattern=" + pattern + ":subscribedChannels=" + subscribedChannels);
		}
	}
	
}
