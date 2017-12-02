package com.hr.analysis.kpi11;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper11 extends Mapper<Object, Text, Text, Text> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split(",");
		String exp = tokens[4].toString();
		context.write(new Text(exp), new Text(value));
	}
}
