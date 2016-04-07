package com.team.hbase.hbasetest;

import lombok.extern.slf4j.Slf4j;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @ClassName:HbaseTest.java
 * @see：hbase架包必须要和服务器一致
 * @Description: hbaseTest
 * @author gaoguangjin
 * @Date 2015-7-6 下午1:38:42
 */
@Slf4j
public class HbaseTest {
	private static Configuration configuration;
	static HTablePool pool;
	static {
		configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
		configuration.set("hbase.zookeeper.quorum", "123.56.118.135");
		configuration.set("hbase.master", "123.56.118.135:60000");
		pool = new HTablePool(configuration, 10);
	}
	
	public static void main(String[] args) {
		String tableName = "student";
		String key1 = "1";
		String key2 = "2";
		
		String deleteALLFamilyName = "studentSex";
		String updateFamilyName = "studentAge";
		String updateColumn = "age";
		
		String deleteFamilyName = "studentName";
		String deleteColumn = "name";
		// 建立表
		createTable(tableName);
		// 插入数据
		insertData(tableName, key1);
		insertData(tableName, key2);
		
		// 更新操作,更新key1 的age
		updateTable(tableName, key1, updateFamilyName, updateColumn);
		
		// 扫描表
		scanTable(tableName);
		
		// 删除所有列的列族
		deleteAllFamily(tableName, deleteALLFamilyName);
		scanTable(tableName);
		
		// 删除整行操作
		deleteRowByKey(tableName, key1);
		scanTable(tableName);
		
		// 删除某一列族下面的列
		deleteColumnByKey(tableName, key2, deleteFamilyName, deleteColumn);
		scanTable(tableName);
		
		// 删除某一列族
		deleteFamilyByKey(tableName, key2, deleteFamilyName);
		scanTable(tableName);
		
		// 根据key 查询
		findByRow(tableName, key2);
		
		// 删除表
		deleteTable(tableName);
	}
	
	private static void findByRow(String tableName, String key2) {
		try {
			HTableInterface table = pool.getTable(tableName);
			Get get = new Get(key2.getBytes());
			Result result = table.get(get);
			
			for (KeyValue keyValue : result.raw()) {
				log.info("查询单条记录==》family:" + byteToString(keyValue.getFamily()) + "====cloumn:" + byteToString(keyValue.getQualifier())
						+ "=====value:" + byteToString(keyValue.getValue()));
			}
			table.close();
		} catch (Exception e) {
			log.error("hbsae查询失败！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 删除表
	 * @param tableName
	 * @return:void
	 */
	private static void deleteTable(String tableName) {
		try {
			HBaseAdmin hbaseAdmin = new HBaseAdmin(configuration);
			boolean isExists = hbaseAdmin.tableExists(tableName);
			// 存在删除
			if (isExists) {
				hbaseAdmin.disableTable(tableName);
				hbaseAdmin.deleteTable(tableName);
			}
		} catch (Exception e) {
			log.error("hbase 删除表失败！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 删除指定的行列族下面的列
	 * @param tableName
	 * @param key1
	 * @return:void
	 */
	private static void deleteColumnByKey(String tableName, String key1, String falilyName, String columnName) {
		try {
			HTableInterface table = pool.getTable(tableName);
			Delete deleteColumn = new Delete(Bytes.toBytes(key1));
			
			// 列族，列
			deleteColumn.deleteColumns(Bytes.toBytes(falilyName), Bytes.toBytes(columnName));
			table.delete(deleteColumn);
			table.close();
		} catch (Exception e) {
			log.error("hbase  删除指定的列失败！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 删除指定的行列族
	 * @param tableName
	 * @param key1
	 * @return:void
	 */
	private static void deleteFamilyByKey(String tableName, String key1, String falilyName) {
		try {
			HTableInterface table = pool.getTable(tableName);
			Delete deleteColumn = new Delete(Bytes.toBytes(key1));
			
			// 列族
			deleteColumn.deleteFamily((Bytes.toBytes(falilyName)));
			table.delete(deleteColumn);
			table.close();
		} catch (Exception e) {
			log.error("hbase  删除指定的列失败！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 删除指定的列族
	 * @param tableName
	 * @param key1
	 * @return:void
	 */
	private static void deleteRowByKey(String tableName, String key1) {
		try {
			HTableInterface table = pool.getTable(tableName);
			Delete deleteAll = new Delete(Bytes.toBytes(key1));
			// 删除列族
			table.delete(deleteAll);
			table.close();
		} catch (Exception e) {
			log.error("hbase 删除指定的列族！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description:删除所有列的列族
	 * @param tableName
	 * @param deleteFamilyName
	 * @return:void
	 */
	private static void deleteAllFamily(String tableName, String deleteFamilyName) {
		try {
			HBaseAdmin hbaseAdmin = new HBaseAdmin(configuration);
			// 删除所有列的列族
			boolean isExists = hbaseAdmin.tableExists(tableName);
			// 存在删除
			if (isExists) {
				// disable
				hbaseAdmin.disableTable(tableName);
				hbaseAdmin.deleteColumn(tableName, deleteFamilyName);
				// 启用
				hbaseAdmin.enableTable(tableName);
				hbaseAdmin.flush(tableName);
			}
		} catch (Exception e) {
			log.error("hbase 删除所有行列族失败！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 更新数据
	 * @param tableName
	 * @param key
	 * @param familyName
	 * @param column
	 * @return:void
	 */
	private static void updateTable(String tableName, String key, String familyName, String column) {
		try {
			HTableInterface table = pool.getTable(tableName);
			Put put = new Put(key.getBytes());
			put.add(familyName.getBytes(), column.getBytes(), "100".getBytes());// 本行数据的第二列
			
			table.put(put);
			table.flushCommits();
			table.close();
		} catch (Exception e) {
			log.error("hbase 更新数据失败！" + e.getLocalizedMessage());
		}
		
	}
	
	private static void scanTable(String tableName) {
		try {
			HTableInterface table = pool.getTable(tableName);
			// Create a Scan operation across all rows
			// Interface for client-side scanning
			ResultScanner rs = table.getScanner(new Scan());
			
			// Single row result of a {@link Get} or {@link Scan} query
			for (Result result : rs) {
				log.info("rowkey:" + byteToString(result.getRow()));
				KeyValue[] kv = result.raw();
				for (KeyValue keyValue : kv) {
					byte[] a = keyValue.getValue();
					log.info("family:" + byteToString(keyValue.getFamily()) + "====cloumn:" + byteToString(keyValue.getQualifier()) + "=====value:"
							+ byteToString(keyValue.getValue()));
				}
			}
			table.close();
		} catch (Exception e) {
			log.error("hbase 输出数据失败！" + e.getLocalizedMessage());
		}
	}
	
	private static void insertData(String tableName, String key) {
		try {
			HTableInterface table = pool.getTable(tableName);
			// row key
			byte[] rowkey = key.getBytes();
			// Used to perform Put operations for a single row
			Put put = new Put(rowkey);
			
			put.add("studentName".getBytes(), "name".getBytes(), "gaoguangjin".getBytes());// 本行数据的第一列
			put.add("studentName".getBytes(), "name2".getBytes(), "gaoguangjin".getBytes());// 本行数据的第一列
			
			put.add("studentAge".getBytes(), "age".getBytes(), "24".getBytes());// 本行数据的第二列
			
			put.add("studentSex".getBytes(), "sex".getBytes(), "boy".getBytes());// 本行数据的第三列
			put.add("studentClass".getBytes(), "class".getBytes(), "计算机科学与技术一班".getBytes());// 本行数据的第四列
			
			table.put(put);
			table.flushCommits();
			table.close();
		} catch (Exception e) {
			log.error("hbase插入数据失败！" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: HBaseAdmin类是用来 Table相关的各种操作
	 * @see:如createTable的各种不同参数的函数、查看一个table是否存在的tableExists函数、查看所有table的listTables、根据table
	 * name可以到对应描述符的getTableDescriptior、删除一个表的deleteTable、disable或者enable一个表的disableTable/enableTable/isTableEnabled/
	 * isTableDisabled等函数
	 * @return:void
	 */
	private static void createTable(String tableName) {
		try {
			HBaseAdmin hbaseAdmin = new HBaseAdmin(configuration);
			boolean isExists = hbaseAdmin.tableExists(tableName);
			// 存在删除
			if (isExists) {
				hbaseAdmin.disableTable(tableName);
				hbaseAdmin.deleteTable(tableName);
			}
			// HTableDescriptor contains the details about an HBase table
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			
			tableDescriptor.addFamily(new HColumnDescriptor("studentName"));
			tableDescriptor.addFamily(new HColumnDescriptor("studentAge"));
			tableDescriptor.addFamily(new HColumnDescriptor("studentSex"));
			tableDescriptor.addFamily(new HColumnDescriptor("studentClass"));
			hbaseAdmin.createTable(tableDescriptor);
		} catch (Exception e) {
			log.error("hbase创建表失败！" + e.getLocalizedMessage());
		}
	}
	
	public static String byteToString(byte[] bytes) {
		return new String(bytes);
	}
}
