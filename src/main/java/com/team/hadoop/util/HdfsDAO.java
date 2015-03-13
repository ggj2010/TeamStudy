package com.team.hadoop.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import lombok.extern.slf4j.Slf4j;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.mapred.JobConf;

import com.team.hadoop.base.HadoopConstant;

@Slf4j
public class HdfsDAO {
	
	private String hdfsPath;
	private Configuration conf;
	
	public HdfsDAO() {
		this.hdfsPath = HadoopConstant.HDFS_PATH;
		this.conf = config();
	}
	
	public static void main(String[] args) throws IOException {
		
		HdfsDAO hdfs = new HdfsDAO();
		
		hdfs.mkdirs("/user/hdfs/log_kpi");
		
		// hdfs.mkdirs("/user/hdfs/log_kpi");
		// hdfs.mkdirs("/user/hdfs/log_kpi");
		// hdfs.mkdirs("/user/hdfs/recommend");
		// hdfs.copyFile("datafile/item.csv", "/tmp/new");
		// hdfs.ls("/tmp/new");
		// hdfs.rename("/user/hdfs/pagerank/tmp3", "/user/hdfs/pagerank/tmp4");
		
		// hdfs.cat("/c/part-00000");
		
		// hdfs.download("/user/part-00000", "e:");
		// hdfs.copyFile("e:/access.log.10", "/user/hdfs/log_kpi");
		// hdfs.ls("/user");
		
		// hdfs.cat("/user/hdfs/log_kpi/time/part-00000");
		// hdfs.cat("/user/hdfs/log_kpi/pv/part-00000");
		// hdfs.cat("/user/hdfs/log_kpi/ip/part-00000");
		// hdfs.cat("/user/hdfs/log_kpi/browser/part-00000");
	}
	
	public static JobConf config() {
		JobConf conf = new JobConf(HdfsDAO.class);
		conf.setJobName("HdfsDAO");
		return conf;
	}
	
	/**
	 * @Description: 在hdf目录里面创建文件夹
	 * @param folder
	 * @throws IOException
	 * @return:void
	 */
	public void mkdirs(String folder) throws IOException {
		Path path = new Path(folder);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		if (!fs.exists(path)) {
			fs.mkdirs(path);
			log.info("Create: " + folder);
		}
		fs.close();
	}
	
	/**
	 * @Description: 删除hdf指定目录下面的所有文件
	 * @param folder
	 * @throws IOException
	 * @return:void
	 */
	public void rmr(String folder) throws IOException {
		Path path = new Path(folder);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		fs.deleteOnExit(path);
		log.info("Delete: " + folder);
		fs.close();
	}
	
	/**
	 * @Description: 重命名
	 * @param src
	 * @param dst
	 * @throws IOException
	 * @return:void
	 */
	public void rename(String src, String dst) throws IOException {
		Path name1 = new Path(src);
		Path name2 = new Path(dst);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		fs.rename(name1, name2);
		log.info("Rename: from " + src + " to " + dst);
		fs.close();
	}
	
	/**
	 * @Description: 查看hdf目录里面的文件
	 * @param folder
	 * @throws IOException
	 * @return:void
	 */
	public void ls(String folder) throws IOException {
		Path path = new Path(folder);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		FileStatus[] list = fs.listStatus(path);
		log.info("ls: " + folder);
		log.info("==========================================================");
		for (FileStatus f : list) {
			System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDir(), f.getLen());
		}
		log.info("==========================================================");
		fs.close();
	}
	
	public void createFile(String file, String content) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		byte[] buff = content.getBytes();
		FSDataOutputStream os = null;
		try {
			os = fs.create(new Path(file));
			os.write(buff, 0, buff.length);
			log.info("Create: " + file);
		}
		finally {
			if (os != null)
				os.close();
		}
		fs.close();
	}
	
	/**
	 * @Description: 复制本地文件系统到HDFS
	 * @param local
	 * @param remote
	 * @throws IOException
	 * @return:void
	 */
	public void copyFile(String local, String remote) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		fs.copyFromLocalFile(new Path(local), new Path(remote));
		log.info("copy from: " + local + " to " + remote);
		fs.close();
	}
	
	/**
	 * @Description: 从linux服务器下载内容
	 * @param remote
	 * @param local
	 * @throws IOException
	 * @return:void
	 */
	public void download(String remote, String local) throws IOException {
		Path path = new Path(remote);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		fs.copyToLocalFile(path, new Path(local));
		log.info("download: from" + remote + " to " + local);
		fs.close();
	}
	
	/**
	 * @Description: 查询hdf里面指定的文件内容
	 * @param remoteFile
	 * @return
	 * @throws IOException
	 * @return:String
	 */
	public String cat(String remoteFile) throws IOException {
		Path path = new Path(remoteFile);
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		FSDataInputStream fsdis = null;
		log.info("cat: " + remoteFile);
		
		OutputStream baos = new ByteArrayOutputStream();
		String str = null;
		
		try {
			fsdis = fs.open(path);
			IOUtils.copyBytes(fsdis, baos, 4096, false);
			str = baos.toString();
		}
		finally {
			IOUtils.closeStream(fsdis);
			fs.close();
		}
		System.out.println(str);
		return str;
	}
	
	public void location() throws IOException {
		// String folder = hdfsPath + "create/";
		// String file = "t2.txt";
		// FileSystem fs = FileSystem.get(URI.create(hdfsPath), new
		// Configuration());
		// FileStatus f = fs.getFileStatus(new Path(folder + file));
		// BlockLocation[] list = fs.getFileBlockLocations(f, 0, f.getLen());
		//
		// log.info("File Location: " + folder + file);
		// for (BlockLocation bl : list) {
		// String[] hosts = bl.getHosts();
		// for (String host : hosts) {
		// log.info("host:" + host);
		// }
		// }
		// fs.close();
	}
	
}
