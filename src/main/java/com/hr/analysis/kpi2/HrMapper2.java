package com.hr.analysis.kpi2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper2 extends Mapper<Object, Text, Text, IntWritable> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split(",");
		String dept = tokens[8].toString();
		int employeesLeft = Integer.parseInt(tokens[6]);
		context.write(new Text(dept), new IntWritable(employeesLeft));
	}

}
