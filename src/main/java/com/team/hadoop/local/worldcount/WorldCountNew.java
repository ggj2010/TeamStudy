package com.team.hadoop.local.worldcount;

import java.io.IOException;
import java.util.StringTokenizer;

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
 * @ClassName:WorldCount.java
 * @Description:hadoop第一个应用用程序,最简单的 worldCount
 * @see：0.20.2版本后的
 * @author gaoguangjin
 * @Date 2015-3-11 下午4:58:43
 */
@Slf4j
public class WorldCountNew extends Configured implements Tool {
	
	// 【1】map阶段，利用匿名类方式
	public static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
		// map阶段的collect方法需要这两个参数collect(K key, V value)
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		
		/**
		 * @throws InterruptedException
		 * @throws IOException
		 * @see:key 每行文件偏移量
		 * @see:value 读入文本的每一行内容
		 */
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			// StringTokenizer比split效率高，当数据量大的情况可以看出优势
			StringTokenizer st = new StringTokenizer(value.toString());
			while (st.hasMoreTokens()) {
				word.set(st.nextToken());// 截取得到的值
				context.write(word, one);// one 为默认的1
			}
		}
	}
	
	// 【2】reduce阶段
	public static class WorlCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();//
		
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			// values是一个set循环
			for (IntWritable value : values) {
				sum += value.get();// 将相同key值的 value相加
			}
			result.set(sum);
			context.write(key, result);// 将key list<>转换成 key value
		}
	}
	
	public int run(String[] args) throws Exception {
		Job job = new Job(getConf());
		job.setJarByClass(WorldCountNew.class);// 运行类
		job.setJobName("wordcountnew");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WorlCountReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// 注意导包啊
		FileInputFormat.setInputPaths(job, new Path(HadoopConstant.HDFS_PATH + "/input/wordcountnew/wordcountnew.txt"));
		FileOutputFormat.setOutputPath(job, new Path(HadoopConstant.HDFS_PATH + "/out/wordcountnew"));
		
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
			hdfs.rmr("/out/wordcountnew");
			int ret = ToolRunner.run(new WorldCountNew(), args);
			// 查看执行结果
			hdfs.cat("/out/wordcountnew/part-r-00000");
			System.exit(ret);
		} catch (Exception e) {
			log.info("wordcountnew出现错误：" + e.getLocalizedMessage());
		}
		
	}
}
