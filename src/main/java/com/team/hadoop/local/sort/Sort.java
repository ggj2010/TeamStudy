package com.team.hadoop.local.sort;

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
 * @ClassName:Sort.java
 * @Description:业务场景，学生成绩评比，数据简历索引
 * @author gaoguangjin
 * @Date 2015-3-17 下午6:17:18
 */
@Slf4j
public class Sort extends Configured implements Tool {
	
	// Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>输出的参数类型
	public static class SortMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
		public static IntWritable iw = new IntWritable();// 一般是整形
		public IntWritable iw2 = new IntWritable(1);// 一般是整形
		
		protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			Integer sortKey = Integer.parseInt(value.toString());
			iw.set(sortKey);
			context.write(iw, iw2);
		}
	}
	
	// IntWritable接收参数都是IntWritable类型的
	public static class SortReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
		private IntWritable linenum = new IntWritable(1);
		int i = 1;
		
		public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			// for循环是为了让重复的数字也排序也显示例如20
			for (IntWritable intWritables : values) {
				context.write(linenum, key);
				i = i + 1;
				linenum.set(i);
			}
		}
	}
	
	public int run(String[] args) throws Exception {
		Job job = new Job(getConf());
		job.setJarByClass(Sort.class);// 运行类
		job.setJobName("Sort");
		
		job.setOutputKeyClass(IntWritable.class);// 输入输出类型
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(SortMapper.class);
		job.setReducerClass(SortReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// 注意导包啊
		FileInputFormat.setInputPaths(job, new Path(HadoopConstant.HDFS_PATH + "/input/sort/*"));
		FileOutputFormat.setOutputPath(job, new Path(HadoopConstant.HDFS_PATH + "/out/sort"));
		
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
			hdfs.rmr("/out/sort");
			int ret = ToolRunner.run(new Sort(), args);
			// 查看执行结果
			hdfs.cat("/out/sort/part-r-00000");
			System.exit(ret);
		} catch (Exception e) {
			log.info("sort出现错误：" + e.getLocalizedMessage());
		}
		
	}
	
}
