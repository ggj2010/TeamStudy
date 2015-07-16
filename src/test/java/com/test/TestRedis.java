//package com.test;
//
//import java.util.Set;
//
//import redis.clients.jedis.Jedis;
//
//import com.msds.redis.dao.RedisDao;
//import com.msds.redis.util.RedisCacheManagers;
//import com.msds.redis.util.RedisDataBaseType;
//
//public class TestRedis {
//	public static void main(String[] args) {
//		Jedis jedis = RedisCacheManagers.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString()).getResource();
//		
//		RedisDao rd = new RedisDao(jedis);
//		Set<String> aa = jedis.keys("*");
//		for (String string : aa) {
//			System.out.println(string);
//		}
//		
//	}
// }
