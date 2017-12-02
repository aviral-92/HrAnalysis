package com.hr.analysis.kpi1;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer1 extends Reducer<Text, FloatWritable, Text, FloatWritable> {

	private FloatWritable result = new FloatWritable();

	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException {

		float avg = 0f, sum = 0, average = 0f, satisfaction = 0f;
		for (FloatWritable val : values) {
			satisfaction = val.get();
			sum += satisfaction;
			System.out.println("reducer " + val.get());
			avg++;
		}
		average = sum / avg;
		result.set(average);
		context.write(key, result);
	}

}
