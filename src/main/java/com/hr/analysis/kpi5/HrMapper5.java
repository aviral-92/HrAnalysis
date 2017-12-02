package com.hr.analysis.kpi5;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper5 extends Mapper<Object, Text, Text, Text> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split(",");
		String dept = tokens[8].toString();
		context.write(new Text(dept), new Text(value));
	}
}
