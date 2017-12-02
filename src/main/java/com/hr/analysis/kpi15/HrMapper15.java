package com.hr.analysis.kpi15;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper15 extends Mapper<Object, Text, NullWritable, Text> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		context.write(NullWritable.get(), new Text(value));
	}

}
