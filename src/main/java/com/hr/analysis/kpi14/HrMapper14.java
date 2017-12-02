package com.hr.analysis.kpi14;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HrMapper14 extends Mapper<Object, Text, Text, Text> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split(",");
		String salaryDistribution = tokens[9].toString();
		context.write(new Text(salaryDistribution), new Text(value));
	}

}
