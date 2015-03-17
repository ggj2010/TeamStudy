package com.team.hadoop.local.worldcount;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.team.hadoop.base.HadoopConstant;
import com.team.hadoop.util.HdfsDAO;

/**
 * @ClassName:Duplicate.java
 * @Description: 业务场景 ，有两个文件，去重每个文件里面重复的字段，值保留一个。
 * @author gaoguangjin
 * @Date 2015-3-17 下午1:46:37
 */
@Slf4j
public class Duplicate extends Configured implements Tool {
	
	public static class DuplicateMapper extends Mapper<Object, Text, Text, IntWritable> {
		
		static IntWritable initwritable = new IntWritable();
		Text word = new Text();
		
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			context.write(value, initwritable);// 将保存所有的key value为空
		}
		
	}
	
	public static class DuplicateReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		static IntWritable initwritable = new IntWritable();
		
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			context.write(key, initwritable);// 合并key就可以了
		}
		
	}
	
	public int run(String[] args) throws Exception {
		
		Job job = new Job(getConf());
		job.setJarByClass(Duplicate.class);// 运行类
		job.setJobName("Duplicate");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(DuplicateMapper.class);
		job.setReducerClass(DuplicateReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// 注意导包啊
		FileInputFormat.setInputPaths(job, new Path(HadoopConstant.HDFS_PATH + "/input/duplicate/*"));
		FileOutputFormat.setOutputPath(job, new Path(HadoopConstant.HDFS_PATH + "/out/duplicate"));
		
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
		
	}
	
	/**
	 * @Description: 主方法，输入文件需要最好是自己生成，然后再放到hdfs里面。
	 * @param args
	 * @throws Exception
	 * @return:void
	 */
	public static void main(String[] args) {
		try {
			HdfsDAO hdfs = new HdfsDAO();
			// 删除输入文件夹
			hdfs.rmr("/out/duplicate");
			int ret = ToolRunner.run(new Duplicate(), args);
			// 查看执行结果
			hdfs.cat("/out/duplicate/part-r-00000");
			System.exit(ret);
		} catch (Exception e) {
			log.info("Duplicate出现错误：" + e.getLocalizedMessage());
		}
		
	}
	
}
