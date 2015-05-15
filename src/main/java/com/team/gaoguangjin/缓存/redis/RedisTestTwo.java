package com.team.gaoguangjin.缓存.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class RedisTestTwo {
	private Jedis jedis;
	
	@Before
	public void init() {
		// 连接redis linux服务器
		jedis = new Jedis("123.56.118.135", 6379);
		// 权限认证
		jedis.auth("gaoguangjin");// 密码最好越长越好，防止暴力破解
	}
	
	@Test
	public void testMap() {
		log.info("---------------测试Map格式---------------");
		
		// hset(key, field, value)：向名称为key的hash中添加元素field
		// hget(key, field)：返回名称为key的hash中field对应的value
		jedis.hset("singleHash", "name", "奥利奥");
		jedis.hset("singleHash", "age", "1岁");
		String singleName = jedis.hget("singleHash", "name");
		String singleAge = jedis.hget("singleHash", "age");
		log.info("hset/hget:" + singleName + ":" + singleAge);
		
		// hmget(key, (fields))：返回名称为key的hash中field i对应的value
		// hmset(key, (fields))：向名称为key的hash中添加元素field
		
		// jedis.hmset("firstMap", "maomi","奥利奥","age","猫咪1岁咯");目前jedis不支持这种方式
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("gao", "高");
		map.put("guang", "广");
		map.put("jin", "金");
		
		// jedis的封装，将获取到的map然后迭代成字符串调用hmset命令
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
	public void msetTest() {
		// 多个键值对 【mset】 key1 "dd" key2 "dd"
		jedis.mset("gao", "高", "guang", "广", "jin", "金");// key value/key value
		List<String> list = jedis.mget("gao", "guang", "jin");
		String listString = "";
		for (String string : list) {
			listString += string;
		}
		log.info("mget和mset：" + listString);
	}
	
	@Test
	public void listTest() {
		log.info("---------------测试List格式---------------");
		// 使用之前删除
		jedis.del("listdemo");
		// lpush默认是往后面追加
		jedis.lpush("listdemo", "1");
		jedis.lpush("listdemo", "2");
		// rpush是往前面添加
		jedis.rpush("listdemo", "3");
		List<String> list = jedis.lrange("listdemo", 0, -1);
		String listString = "";
		for (String string : list) {
			listString += string + "==>";
		}
		
		// llen 返回list长度
		Long length = jedis.llen("listdemo");
		
		// ltrim 截取list,从0开始 包括结尾的n 0~1就是截取2个
		String returnType = jedis.ltrim("listdemo", 0, 1);// OK
		
		// lset给某个位置重新赋值
		jedis.lset("listdemo", 0, "100");
		
		// lindex获取某个位置对应的value
		String value = jedis.lindex("listdemo", 0);
		
		log.info("[原来输入顺序是]3=>1=》2");
		log.info("[lrang输出的默认是先进先出]:" + listString);
		log.info("lln list的长度是：" + length);
		log.info("ltrim 截取之后list的长度：" + jedis.llen("listdemo") + returnType);
		log.info("lset 重新赋值之后的某个位置的值 lindex:" + value);
		
		// lrem(key, count, value)：删除count个key的list中值为value的元素
		jedis.lpush("listdemo", "100");
		log.info("lrem 删除之前list的大小:" + jedis.llen("listdemo"));
		jedis.lrem("listdemo", 2, "100");// 删除list里面 2为值为100的元素
		log.info("lrem 删除n=2个value元素之后 list的大小:" + jedis.llen("listdemo"));
		
		/*****/
		jedis.del("listdemo");
		jedis.lpush("listdemo", "1");
		jedis.lpush("listdemo", "2");
		jedis.lpush("listdemo", "3");
		jedis.lpush("listdemo", "4");
		// lpop删除list中的首位元素
		String top = jedis.lpop("listdemo");
		// rpop删除list中的尾元素
		String back = jedis.rpop("listdemo");
		
		log.info("重新赋值list 1=>2>3>4  lindex 0=4 lindex3=1 输出是倒序的");
		log.info("默认是【4】 lpop删除list首元素之后 首元素的值：" + jedis.lindex("listdemo", 0));
		log.info("默认是【1】 rpop删除list首元素之后 首元素的值：" + jedis.lindex("listdemo", 1));
		
		log.info(jedis.info());
	}
	
	//
	// 1）连接操作命令
	// quit：关闭连接（connection）
	// auth：简单密码认证
	// help cmd： 查看cmd帮助，例如：help quit
	//
	// 2）持久化
	// save：将数据同步保存到磁盘
	// bgsave：将数据异步保存到磁盘
	// lastsave：返回上次成功将数据保存到磁盘的Unix时戳
	// shundown：将数据同步保存到磁盘，然后关闭服务
	//
	// 3）远程服务控制
	// info：提供服务器的信息和统计
	// monitor：实时转储收到的请求
	// slaveof：改变复制策略设置
	// config：在运行时配置Redis服务器
	//
	// 4）对value操作的命令
	// exists(key)：确认一个key是否存在
	// del(key)：删除一个key
	// type(key)：返回值的类型
	// keys(pattern)：返回满足给定pattern的所有key
	// randomkey：随机返回key空间的一个
	// keyrename(oldname, newname)：重命名key
	// dbsize：返回当前数据库中key的数目
	// expire：设定一个key的活动时间（s）
	// ttl：获得一个key的活动时间
	// select(index)：按索引查询
	// move(key, dbindex)：移动当前数据库中的key到dbindex数据库
	// flushdb：删除当前选择数据库中的所有key
	// flushall：删除所有数据库中的所有key
	//
	// 5）String
	// set(key, value)：给数据库中名称为key的string赋予值value
	// get(key)：返回数据库中名称为key的string的value
	// getset(key, value)：给名称为key的string赋予上一次的value
	// mget(key1, key2,…, key N)：返回库中多个string的value
	// setnx(key, value)：添加string，名称为key，值为value
	// setex(key, time, value)：向库中添加string，设定过期时间time
	// mset(key N, value N)：批量设置多个string的值
	// msetnx(key N, value N)：如果所有名称为key i的string都不存在
	// incr(key)：名称为key的string增1操作
	// incrby(key, integer)：名称为key的string增加integer
	// decr(key)：名称为key的string减1操作
	// decrby(key, integer)：名称为key的string减少integer
	// append(key, value)：名称为key的string的值附加value
	// substr(key, start, end)：返回名称为key的string的value的子串
	//
	// 6）List
	// rpush(key, value)：在名称为key的list尾添加一个值为value的元素
	// lpush(key, value)：在名称为key的list头添加一个值为value的 元素
	// llen(key)：返回名称为key的list的长度
	// lrange(key, start, end)：返回名称为key的list中start至end之间的元素
	// ltrim(key, start, end)：截取名称为key的list
	// lindex(key, index)：返回名称为key的list中index位置的元素
	// lset(key, index, value)：给名称为key的list中index位置的元素赋值
	//
	// lrem(key, count, value)：删除count个key的list中值为value的元素
	// lpop(key)：返回并删除名称为key的list中的首元素
	// rpop(key)：返回并删除名称为key的list中的尾元素
	// blpop(key1, key2,… key N, timeout)：lpop命令的block版本。
	// brpop(key1, key2,… key N, timeout)：rpop的block版本。
	// rpoplpush(srckey, dstkey)：返回并删除名称为srckey的list的尾元素，
	// 　　　　　　　　　　　　　　并将该元素添加到名称为dstkey的list的头部
	//
	// 7）Set
	// sadd(key, member)：向名称为key的set中添加元素member
	// srem(key, member) ：删除名称为key的set中的元素member
	// spop(key) ：随机返回并删除名称为key的set中一个元素
	// smove(srckey, dstkey, member) ：移到集合元素
	// scard(key) ：返回名称为key的set的基数
	// sismember(key, member) ：member是否是名称为key的set的元素
	// sinter(key1, key2,…key N) ：求交集
	// sinterstore(dstkey, (keys)) ：求交集并将交集保存到dstkey的集合
	// sunion(key1, (keys)) ：求并集
	// sunionstore(dstkey, (keys)) ：求并集并将并集保存到dstkey的集合
	// sdiff(key1, (keys)) ：求差集
	// sdiffstore(dstkey, (keys)) ：求差集并将差集保存到dstkey的集合
	// smembers(key) ：返回名称为key的set的所有元素
	// srandmember(key) ：随机返回名称为key的set的一个元素
	//
	// 8）Hash
	// hset(key, field, value)：向名称为key的hash中添加元素field
	// hget(key, field)：返回名称为key的hash中field对应的value
	// hmget(key, (fields))：返回名称为key的hash中field i对应的value
	// hmset(key, (fields))：向名称为key的hash中添加元素field
	// hincrby(key, field, integer)：将名称为key的hash中field的value增加integer
	// hexists(key, field)：名称为key的hash中是否存在键为field的域
	//
	// hdel(key, field)：删除名称为key的hash中键为field的域
	// hlen(key)：返回名称为key的hash中元素个数
	// hkeys(key)：返回名称为key的hash中所有键
	// hvals(key)：返回名称为key的hash中所有键对应的value
	//
	// hgetall(key)：返回名称为key的hash中所有的键（field）及其对应的value
	
}
