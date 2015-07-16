package com.team.gaoguangjin.缓存.模拟jedis;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

/**
 * @ClassName:MySelfJedis.java
 * @Description: 比如redis有对应的客户端jedis,memcache有Java 客户端 memCachedClient
 * @see：利用Java去调用Linux的命令。socket方式
 * @author gaoguangjin
 * @Date 2015-7-2 下午5:17:17
 */
@Slf4j
public class MySelfJedis {
	public static void main(String[] args) {
		// test();
		testJedis();
	}
	
	private static void testJedis() {
		// 连接redis linux服务器
		Jedis jedis = new Jedis("123.56.118.135", 6379);
		// 权限认证
		jedis.auth("gaoguangjin");// 密码最好越长越好，防止暴力破解
		List<String> aa = jedis.lrange("list", 0, -1);
		jedis.disconnect();
	}
	
	/**
	 * @Description: redis支持的协议为
	 * @see:<参数的个数> CR LF
	 * @see:$<参数1字节数> CR LF
	 * @see:<参数1> CR LF
	 * @see:...
	 * @see:$<参数n字节数> CR LF
	 * @see:<参数n> CR LF
	 */
	private static void test() {
		try {
			Socket socket = new Socket("123.56.118.135", 6379);
			socket.setSoTimeout(3000);
			OutputStream outputStream = socket.getOutputStream();
			InputStream input = socket.getInputStream();
			byte[] by = new byte[1024];
			
			// 登陆密码
			outputStream.write("*2\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$4\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("AUTH\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$11\rn".getBytes(Protocol.CHARSET));
			outputStream.write("gaoguangjin\r\n".getBytes(Protocol.CHARSET));
			outputStream.flush();
			input.read(by);
			System.out.println("输出验证结果：" + new String(by));
			
			// 赋值 set gao gaoguangjin
			outputStream.write("*3\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$3\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("set\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$3\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("gao\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$11\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("gaoguangjin\r\n".getBytes(Protocol.CHARSET));
			
			outputStream.flush();
			input.read(by);
			System.out.println("输出set结果：" + new String(by));
			
			// 获取值get gao
			outputStream.write("*2\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$3\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("get\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$3\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("gao\r\n".getBytes(Protocol.CHARSET));
			outputStream.flush();
			input.read(by);
			System.out.println("输出get结果：" + new String(by));
			
			// lrange list 0 -1
			by = new byte[1024];
			outputStream.write("*4\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$6\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("lrange\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$4\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("list\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$1\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("0\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("$2\r\n".getBytes(Protocol.CHARSET));
			outputStream.write("-1\r\n".getBytes(Protocol.CHARSET));
			
			outputStream.flush();
			input.read(by);
			System.out.println("输出lrange结果：" + new String(by));
		} catch (Exception e) {
			log.error("连接失败！" + e.getLocalizedMessage());
		}
	}
}
