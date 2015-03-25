package com.team.hadoop.local.worldcount;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import com.team.hadoop.base.HadoopConstant;
import com.team.hadoop.util.HdfsDAO;

/**
 * @ClassName:WordCountOld.java
 * @Description:最基础的world统计
 * @see：0.20.2版本以前的不推荐使用
 * @author gaoguangjin
 * @Date 2015-3-12 下午5:50:06
 */
public class WordCountOld {
	
	public static class WordCountMapper extends MapReduceBase implements Mapper<Object, Text, Text, IntWritable> {
		// 默认出现一次
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		
		/**
		 * @seeObject key：每行文件偏移量
		 * @seeText value:每行文件内容||
		 */
		public void map(Object key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
			
			System.out.println(value + "=========");// 每次输入txt里面一行数据
			StringTokenizer itr = new StringTokenizer(value.toString()); // 这个是以空格截取，StringTokenizer比split的效率高
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				// 传给reduce
				output.collect(word, one);
			}
			
		}
	}
	
	public static class WordCountReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();
		
		/**
		 * @see key:为Map端输出的Key值
		 * @see values：为Map段输出的value集合(相同key值) <key,(List of value)>
		 * @seeoutput:上下文配置项
		 */
		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
			int sum = 0;
			
			while (values.hasNext()) {
				sum += values.next().get();
			}
			System.out.println(values + "====reduce===" + key + "======" + sum);
			result.set(sum);
			output.collect(key, result);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 存在hadf里面待处理的文件
		String input = HadoopConstant.HDFS_PATH + "";
		// 分析之后的文件结果
		String output = HadoopConstant.HDFS_PATH + "";
		
		JobConf conf = new JobConf(WordCountOld.class);
		conf.setJobName("WordCountOld");
		
		conf.setJarByClass(WordCountOld.class);
		conf.setOutputKeyClass(Text.class);// 作业输出数据关键类
		conf.setOutputValueClass(IntWritable.class);// 作业输出值类
		
		conf.setMapperClass(WordCountMapper.class);// Mapper
		conf.setCombinerClass(WordCountReducer.class);// 作业合成类
		conf.setReducerClass(WordCountReducer.class);// Reducer
		
		conf.setInputFormat(TextInputFormat.class);//
		conf.setOutputFormat(TextOutputFormat.class);//
		
		FileInputFormat.setInputPaths(conf, new Path(HadoopConstant.HDFS_PATH + "/input/wordcountnew/wordcountnew.txt"));// 文件输入
		FileOutputFormat.setOutputPath(conf, new Path(HadoopConstant.HDFS_PATH + "/out/wordcountold"));// 文件输出
		
		HdfsDAO hdfs = new HdfsDAO();
		// 删除输入文件夹
		hdfs.rmr("/out/wordcountold");
		JobClient.runJob(conf);
		// 查看执行结果
		hdfs.cat("/out/wordcountold/part-00000");
		System.exit(0);
	}
}
