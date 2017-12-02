package com.hr.analysis.kpi4;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper4 extends Mapper<Object, Text, Text, IntWritable> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split(",");
		String dept = tokens[8].toString();
		int projects = Integer.parseInt(tokens[2]);
		context.write(new Text(dept), new IntWritable(projects));
	}
}
