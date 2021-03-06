package com.hr.analysis.kpi9;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper9 extends Mapper<Object, Text, Text, Text> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split(",");
		String dept = tokens[8].toString();
		context.write(new Text(dept), new Text(value));
	}

}
