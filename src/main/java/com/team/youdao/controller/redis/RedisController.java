package com.team.youdao.controller.redis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

/**
 * @ClassName:RedisController.java
 * @Description: redis消息pub/sbu
 * @author gaoguangjin
 * @Date 2015-5-14 下午12:50:09
 */
@Controller
@Slf4j
@RequestMapping("/youdao/redis")
public class RedisController {
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	private final String MESSAGE_TITLE = "channel";
	
	@RequestMapping(value = "/publish", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void publishMessage(String content, String userName, HttpServletRequest request) {
		Jedis jedis = null;
		RedisCachePool redisPool = null;
		try {
			redisPool = redisCacheManager.getRedisPoolMap().get(RedisDataBase.subpub.toString());
			// String Content = DateUtil.getDateTime("HH:mm:ss ") + "：" + content;
			String Content = (request.getRemoteAddr().equals("0:0:0:0:0:0:0:1") ? "服务器" : request.getRemoteAddr()) + "：" + content;
			jedis = redisPool.getResource();
			jedis.publish(MESSAGE_TITLE, Content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			redisPool.returnResource(jedis);
		}
	}
	
	@RequestMapping(value = "/subscribe", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void subscribeMessage(HttpServletResponse response) {
		
		Jedis jedis = null;
		RedisCachePool redisPool = null;
		try {
			redisPool = redisCacheManager.getRedisPoolMap().get(RedisDataBase.subpub.toString());
			JedisPubSubDemo jedisPubSub = new JedisPubSubDemo();
			// 将response对象赋值过去
			jedisPubSub.setResponse(response);
			// 客户端调用
			jedis = redisPool.getResource();
			
			jedis.subscribe(jedisPubSub, MESSAGE_TITLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			redisPool.returnResource(jedis);
		}
	}
	
	/**
	 * @Description: 从redis内存下载数据和直接下载数据对比
	 * @param response
	 * @return:void
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloaddemo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void downLoad(HttpServletResponse response) throws IOException {
		File file = new File("f:/Genuitec.rar");
		FileInputStream inputStream = new FileInputStream(file);
		
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		//
		// byte[] buffer = new byte[bis.available()];
		// bis.read(buffer);
		
		response.reset();
		// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(file.getName().replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
		response.addHeader("Content-Length", "" + file.length());
		BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		
		// os.write(buffer);// 输出文件
		
		int readLength = 0;
		byte[] readUnit = new byte[1024 * 1024];
		long time = System.currentTimeMillis();
		while ((readLength = bis.read(readUnit)) != -1) {
			// readUnit = new byte[1024 * 1024];//打开再写入耗时64658，如果放到redis里面就可以立即写入了
			os.write(readUnit, 0, readLength);
			os.flush();
		}
		long endtime = System.currentTimeMillis();
		
		System.out.println(endtime - time);// 34837
		
		System.out.println("end");
		
	}
	
}
