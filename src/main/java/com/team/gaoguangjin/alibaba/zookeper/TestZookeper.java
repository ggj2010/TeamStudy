package com.team.gaoguangjin.alibaba.zookeper;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @ClassName: TestZookeper.java
 * @Description:用来测试zookeper
 * @author gaoguangjin
 * @Date 2015-2-9 上午9:22:49
 */
@Slf4j
public class TestZookeper {
	// zookeper监听的地址和端口号
	public static String URL = "localhost";
	public static String CLIENT_POORT = "2181";
	
	public static void main(String[] args) {
		try {
			demo1();
		}
		catch (Exception e) {
			log.info("zookeper测试失败！:" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description:用来测试zookeper
	 * @return: void
	 * @throws IOException
	 */
	private static void demo1() throws Exception {
		ZooKeeper zk = new ZooKeeper(URL + ":" + CLIENT_POORT, 60000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("EVENT:" + event.getType());
			}
		});
		// // 创建一个目录节点
		// zk.create("/zookeper", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// zk.create("/zookeper/testChildPathOne", "testChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		// 创建一个目录节点
		zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// 创建一个子目录节点
		zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath", false, null)));
		// 取出子目录节点列表
		System.out.println(zk.getChildren("/testRootPath", true));
		// 修改子目录节点数据
		zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
		System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");
		// 创建另外一个子目录节点
		zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));
		// 删除子目录节点
		zk.delete("/testRootPath/testChildPathTwo", -1);
		zk.delete("/testRootPath/testChildPathOne", -1);
		// 删除父目录节点
		zk.delete("/testRootPath", -1);
		// 关闭连接
		zk.close();
	}
}
