package com.hr.analysis.kpi3;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer3 extends Reducer<Text, IntWritable, Text, FloatWritable> {

	private FloatWritable result = new FloatWritable();

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		float average = 0f;
		int avg = 0, sum = 0, workingHrs = 0;
		for (IntWritable val : values) {
			workingHrs = val.get();
			sum += workingHrs;
			avg++;
		}
		average = sum / avg;
		result.set(average);
		context.write(key, result);
	}

}
