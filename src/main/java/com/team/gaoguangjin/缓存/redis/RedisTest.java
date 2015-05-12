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
		List<String> list = new ArrayList<String>();
		
		// 第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
		// redis> LPUSH mylist "world"|LPUSH mylist "hello"|LRANGE mylist 0 -1
		
		log.info("[lpush 存放到list里面]");
		for (int i = 5; i > 0; i--)
			jedis.lpush("list", "" + i);
		jedis.lpush("list", "" + 9);
		jedis.lpush("list", "" + 8);
		
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
		
		jedis.del("list");
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
	
	public static void main(String[] args) {
		// sortScore();
		// autoincrease();
		// 获取所有key
		getAllKey();
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
		
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();)
			System.out.print(iterator.next() + ":");
		
		System.out.println("");
		Long sequence = jedis.zrank("scorSort", "gao1");// 获取某个用户的全球排名
		
		log.info("[zrank] gao1在11个人当中排序：" + sequence);
		
		// 返回连接池
		RedisPool.returnResource(jedis);
		
	}
}
