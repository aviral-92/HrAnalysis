package com.hr.analysis.kpi1;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper1 extends Mapper<Object, Text, Text, FloatWritable> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split(",");
		// Float satisfactionLevel = Float.parseFloat(tokens[1].toString());
		String dept = tokens[8].toString();
		Float satisfaction = Float.parseFloat(tokens[0]);
		context.write(new Text(dept), new FloatWritable(satisfaction));
	}
}
